
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class GetClassInfo {
    public static void premain(String args, Instrumentation inst){
        Class[] classes = inst.getAllLoadedClasses();
        for(Class c : classes){
            System.out.println(c.getName());
            System.out.println(c.getSuperclass());
            System.out.println(Arrays.toString(c.getInterfaces()));
            System.out.println(Modifier.toString(c.getModifiers()));
        }
    }
}