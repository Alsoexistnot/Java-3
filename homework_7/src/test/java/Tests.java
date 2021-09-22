public class Tests {
    MethodsForTest methods;
    Class c = MethodsForTest.class;
    int a = 1;
    int b = 2;
    int q = 3;

    @Test(priority = 3)
    public boolean test2(){
        boolean bool;
        methods = new MethodsForTest();
        int result = 3;
        int test = methods.method2(a, b);
        bool = result == test;
        System.out.println(bool);
        System.out.println("Сработал @Test, priority() = 3");
        return bool;
    }
    @Test(priority = 1)
    public boolean test3(){
        boolean bool;
        methods = new MethodsForTest();
        int result = 1;
        int test = methods.method3(a, b);
        bool = result == test;
        System.out.println(bool);
        System.out.println("Сработал @Test, priority() = 1");
        return bool;
    }
    @Test(priority = 2)
    public boolean test4(){
        boolean bool;
        methods = new MethodsForTest();
        int result = 5;
        int test = methods.method4(a, b, q);
        bool = result == test;
        System.out.println(bool);
        System.out.println("Сработал @Test, priority() = 2");
        return bool;
    }
    @AfterSuite
    public boolean test5(){
        boolean bool;
        methods = new MethodsForTest();
        int result = 6;
        int test = methods.method5(a, b, q);
        bool = result == test;
        System.out.println(bool);
        System.out.println("Сработал @AfterSuite");
        return bool;
    }
    @BeforeSuite
    public boolean test1(){
        boolean bool;
        methods = new MethodsForTest();
        int result = 0;
        int test = methods.method1(a, b, q);
        bool = result == test;
        System.out.println(bool);
        System.out.println("Сработал @BeforeSuite");
        return bool;
    }

}
