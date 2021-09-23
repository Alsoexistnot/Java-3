import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class StartTest {
    private static int beforeSuite = 0;
    private static int afterSuite = 0;

    public static void start(Class classForTest) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Method[] methods = classForTest.getDeclaredMethods();
        ArrayList<Method> array = new ArrayList<>();
        ArrayList<Method> sortedArray = new ArrayList<>();

        for (Method m : methods) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                beforeSuite++;
            } else if (m.isAnnotationPresent(AfterSuite.class)) {
                afterSuite++;
            } else if (m.isAnnotationPresent(Test.class)) {
                array.add(m);
            }
        }
        if (beforeSuite > 1 | afterSuite > 1) {
            throw new RuntimeException("Аннотации @BeforeSuite и @AfterSuite должны быть в одном экземпляре");
        }
        for (Method m : methods) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                m.invoke(null);
            }
        }

        for (int i = 1; i < 11; i++) {
            for (int j = 0; j < array.size(); j++) {
                if (array.get(j).getAnnotation(Test.class).priority() == i){
                    sortedArray.add(array.get(j));
                }
            }
        }


        for (int i = 0; i < sortedArray.size(); i++) {
            sortedArray.get(i).invoke(null);
        }

        for (Method m : methods) {
            if (m.isAnnotationPresent(AfterSuite.class)) {
                m.invoke(null);
            }
        }

        System.out.println("\nПрограмма сработала без ошибок");
    }
}
