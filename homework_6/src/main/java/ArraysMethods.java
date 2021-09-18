import java.util.ArrayList;

public class ArraysMethods {

//    1. Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив.
//    Метод должен вернуть новый массив, который получен путем вытаскивания из исходного массива элементов,
//    идущих после последней четверки. Входной массив должен содержать хотя бы одну четверку, иначе в методе
//    необходимо выбросить RuntimeException.

    public int [] arrayChange(int[] arr){
        int numbersFourInArray = howManyNumbersFourInArray(arr);
        if (numbersFourInArray == 0){
            throw new RuntimeException("В массиве отсутствует цифра 4");
        }
        ArrayList<Integer> arrList = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 4){
                count++;
            }
            if (numbersFourInArray == count) {
                if (arr[i] != 4) {
                    arrList.add(arr[i]);
                }
            }
        }
        int [] newArray = new int[arrList.size()];
        for (int i = 0; i < arrList.size(); i++) {
            newArray[i] = arrList.get(i);
        }
        return newArray;
    }

    public int howManyNumbersFourInArray (int[] arr){
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 4){
                count++;
            }
        }
        return count;
    }


//    2. Написать метод, который проверяет состав массива из чисел 1 и 4.
//    Если в нем нет хоть одной четверки или единицы, то метод вернет false

    public boolean trueOrFalse (int[] arr) {
        boolean check = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1 || arr[i] == 4){
                check = true;
                break;
            }
        }
        return check;
    }

}
