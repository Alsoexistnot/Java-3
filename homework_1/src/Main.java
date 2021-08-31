import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        1. Написать метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа)

        System.out.println("Написать метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа):");
        System.out.println();

        Integer [] intArray = {1, 2, 3, 4, 5};
        String [] strArray = {"Один", "Два", "Три", "Четыре", "Пять"};
        System.out.println(Arrays.toString(intArray));
        System.out.println(Arrays.toString(strArray));
        System.out.println();

        replace(intArray, 0, 4);
        replace(strArray, 0, 4);

        System.out.println(Arrays.toString(intArray));
        System.out.println(Arrays.toString(strArray));
        System.out.println();

//        2. Написать метод, который преобразует массив в ArrayList

        System.out.println("Написать метод, который преобразует массив в ArrayList:");
        System.out.println();

        ArrayList <Integer> intArrayList = convert(intArray);
        System.out.println(Arrays.toString(intArrayList.toArray()));
        System.out.println();

//        3. Большая задача

        System.out.println("Большая задача:");
        System.out.println();


        Box <Apple> firstAppleBox = new Box<>();
        Box <Orange> firstOrangeBox = new Box<>();
        Box <Apple> secondAppleBox = new Box<>();
        Box <Orange> secondOrangeBox = new Box<>();


        for (int i = 0; i < 10; i++) {                                 // добавили 10 яблок в первый ящик для яблок
            addApple(firstAppleBox);
        }
        System.out.print("Вес первого ящика с яблоками: ");
        System.out.println(firstAppleBox.getWeight());

        for (int i = 0; i < 8; i++) {                                 // добавили 8 апельсинов в первый ящик для апельсинов
            addOrange(firstOrangeBox);
        }
        System.out.print("Вес первого ящика с апельсинами: ");
        System.out.println(firstOrangeBox.getWeight());

        for (int i = 0; i < 6; i++) {                                 // добавили 6 яблок во второй ящик для яблок
            addApple(secondAppleBox);
        }
        System.out.print("Вес второго ящика с яблоками: ");
        System.out.println(secondAppleBox.getWeight());

        for (int i = 0; i < 4; i++) {                                 // добавили 4 апельсина во второй ящик для апельсинов
            addOrange(secondOrangeBox);
        }
        System.out.print("Вес второго ящика с апельсинами: ");
        System.out.println(secondOrangeBox.getWeight());
        System.out.println();

       // addApple(firstOrangeBox);  Пробуем добавить в ящик для яблок апельсины - ошибка.

        System.out.print("Сравниваем второй ящик яблок(вес 6) и второй ящик апельсинов(вес 6): ");
        System.out.println(secondAppleBox.compare(secondOrangeBox));
        System.out.print("Сравниваем первый ящик яблок(вес 10) и первый ящик апельсинов(вес 12): ");
        System.out.println(firstAppleBox.compare(firstOrangeBox));
        System.out.println();

        System.out.println("Перекладываем яблоки из первого ящика(вес - " + firstAppleBox.getWeight() + "), во второй(вес - " + secondAppleBox.getWeight() + ")");
        firstAppleBox.transfer(secondAppleBox);
        System.out.println("Теперь вес во втором ящике составляет: " + secondAppleBox.getWeight());
        System.out.println("Информация по первому ящику: ");
        System.out.println(firstAppleBox.getWeight());

    }


    public static <R> void replace(R[] arr, int x, int y){
        if (x < 0 || y < 0 || x > arr.length || y > arr.length || x == y) {
            System.out.println("Ячейки массива указаны неккоректно.");
        } else {
            R temp = arr[x];
            arr[x] = arr[y];
            arr[y] = temp;
        }
    }

    public static <C> ArrayList convert(C[] array){
        ArrayList<C> al = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            al.add(array[i]);
        }
        return al;
    }

    public static void addApple (Box <Apple> box){
        box.getFruitsBox().add(new Apple());
    }

    public static void addOrange (Box <Orange> box){
        box.getFruitsBox().add(new Orange());
    }

}
