import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;


public class ArraysTests {
    ArraysMethods arraysMethods;

//    Тесты для первого задания:

    int [] task1 = {4, 6, 6, 8, 3, 4, 7, 8, 2};
    int [] task2 = {1, 4, 5, 4, 3, 1, 4, 8, 8};
    int [] task3 = {3, 3, 6, 9, 4, 2, 7, 1, 2};
    int [] answer1 = {7, 8, 2};
    int [] answer2 = {8, 8};
    int [] answer3 = {2, 7, 1, 2};

//    Тесты для второго задания:

    int [] task21 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    int [] task22 = {0, 1, 2, 3, 1, 5, 6, 7, 8};
    int [] task23 = {0, 2, 3, 4, 5, 6, 7, 8, 9};
    int [] task24 = {0, 2, 3, 0, 5, 6, 7, 8, 9};

//      1. Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив.
//      Метод должен вернуть новый массив, который получен путем вытаскивания из исходного массива элементов,
//      идущих после последней четверки. Входной массив должен содержать хотя бы одну четверку, иначе в методе
//      необходимо выбросить RuntimeException.


    @Before
    public void init() {
        arraysMethods = new ArraysMethods();
    }


    @Test
    public void test1(){
        Assert.assertArrayEquals(answer1, arraysMethods.arrayChange(task1));
    }

    @Test
    public void test2(){
        Assert.assertArrayEquals(answer2, arraysMethods.arrayChange(task2));
    }

    @Test
    public void test3(){
        Assert.assertArrayEquals(answer3, arraysMethods.arrayChange(task3));
    }

//    2. Написать метод, который проверяет состав массива из чисел 1 и 4.
//    Если в нем нет хоть одной четверки или единицы, то метод вернет false

    @Test
    public void test21(){
        Assert.assertTrue(arraysMethods.trueOrFalse(task21));
    }

    @Test
    public void test22(){
        Assert.assertTrue(arraysMethods.trueOrFalse(task22));
    }

    @Test
    public void test23(){
        Assert.assertTrue(arraysMethods.trueOrFalse(task23));
    }

    @Test
    public void test24(){
        Assert.assertFalse(arraysMethods.trueOrFalse(task24));
    }

}
