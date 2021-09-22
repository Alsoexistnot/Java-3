import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class StartTest {

    public static void start(Class classForTest) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {

        Method[] methods = classForTest.getDeclaredMethods();
        for (Method m : methods) {
            if (m.isAnnotationPresent(BeforeSuite.class)){
                m.invoke(methods);
            }
        }

    }
}





//        Создать класс, который может выполнять «тесты», в качестве тестов выступают классы с наборами методов
//        с аннотациями @Test. Для этого у него должен быть статический метод start(), которому в качестве параметра
//        передается или объект типа Class, или имя класса. Из «класса-теста» вначале должен быть запущен метод
//        с аннотацией @BeforeSuite, если такой имеется, далее запущены методы с аннотациями @Test, а по завершению
//        всех тестов – метод с аннотацией @AfterSuite. К каждому тесту необходимо также добавить
//        приоритеты (int числа от 1 до 10), в соответствии с которыми будет выбираться порядок их выполнения,
//        если приоритет одинаковый, то порядок не имеет значения. Методы с аннотациями @BeforeSuite и @AfterSuite
//        должны присутствовать в единственном экземпляре, иначе необходимо бросить RuntimeException
//        при запуске «тестирования».Это домашнее задание никак не связано с темой тестирования через JUnit и использованием
//        этой библиотеки, то есть проект пишется с нуля.