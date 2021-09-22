import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        Class c = MethodsForTest.class;
        try {
            StartTest.start(c);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
}
