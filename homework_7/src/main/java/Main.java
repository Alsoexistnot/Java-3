import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) {
        Class c = MethodsForTest.class;
        try {
            StartTest.start(c);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

    }
}
