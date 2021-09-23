public class MethodsForTest {

    @Test(priority = 5)
    public static void testMethod1(){
        System.out.println("Сработал @Test. Метод 1. Приоритет - 5.");
    }
    @Test(priority = 7)
    public static void testMethod2(){
        System.out.println("Сработал @Test. Метод 2. Приоритет - 7.");
    }
    @Test(priority = 7)
    public static void testMethod3(){
        System.out.println("Сработал @Test. Метод 3. Приоритет - 7.");
    }
    @Test(priority = 1)
    public static void testMethod4(){
        System.out.println("Сработал @Test. Метод 4. Приоритет - 1.");
    }
    @Test(priority = 10)
    public static void testMethod5(){
        System.out.println("Сработал @Test. Метод 5. Приоритет - 10.");
    }
    @BeforeSuite
    public static void testMethod6(){
        System.out.println("Сработал @BeforeSuite");
    }
    @AfterSuite
    public static void testMethod7(){
        System.out.println("Сработал @AfterSuite");
    }


//    public int method1(int a, int b, int q) {
//        return a + b - q;
//    }
//
//    public int method2(int a, int b) {
//        return a + b;
//    }
//
//    public int method3(int a, int b) {
//        return b - a;
//    }
//
//    public int method4(int a, int b, int q) {
//        return a * b + q;
//    }
//
//    public int method5(int a, int b, int q) {
//        return a + b + q;
//    }

}
