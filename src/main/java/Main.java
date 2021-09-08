import javax.swing.*;
import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;

public class Main {
    public static void main(String[] args) {

//        1. Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;

        textToByteArray();

//        2. Последовательно сшить 5 файлов в один (файлы примерно 100 байт). Может пригодиться следующая конструкция:
//        ArrayList<InputStream> al = new ArrayList<>();... Enumeration<InputStream> e = Collections.enumeration(al);

        fiveFilesToOne();

//      2.1 Читаем файл, в который записали остальные пять:

        readFromFile();

//        3. Написать консольное приложение, которое умеет постранично читать текстовые файлы (размером > 10 mb).
//           Вводим страницу (за страницу можно принять 1800 символов), программа выводит ее в консоль.
//           Контролируем время выполнения: программа не должна загружаться дольше 10 секунд, а чтение – занимать свыше 5 секунд.

        readBigFile();

    }


    public static void textToByteArray() {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("test.txt"));
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int x;
            while ((x = in.read()) != -1) {
                out.write(x);
            }
            byte[] arr = out.toByteArray();
            in.close();
            out.close();
            System.out.println(Arrays.toString(arr));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fiveFilesToOne() {
        ArrayList<InputStream> al = new ArrayList<>();
        try (FileOutputStream out = new FileOutputStream("allfivefiles.txt")) {
            for (int i = 0; i < 5; i++) {
                FileInputStream in = new FileInputStream("file_" + i + ".txt");
                al.add(in);
            }
            Enumeration<InputStream> e = Collections.enumeration(al);
            SequenceInputStream seq = new SequenceInputStream(e);
            int x;
            while ((x = seq.read()) != -1) {
                out.write(x);
            }
            seq.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void readFromFile() {
        try (InputStreamReader in = new InputStreamReader(new FileInputStream("allfivefiles.txt"), "UTF-8")) {
            int x;
            int y = 0;
            while ((x = in.read()) != -1) {
                y++;
                if (y % 50 == 0) {
                    System.out.println();
                }
                System.out.print((char) x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readBigFile(){
        final int PAGE = 1800;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            RandomAccessFile raf = new RandomAccessFile("big_file.txt", "r")) {
            System.out.println("Введите номер необходимой страницы:");
            int userPageChoice = Integer.parseInt(br.readLine()) - 1;
            if (userPageChoice > 0) {
                raf.seek(userPageChoice * PAGE);
                for (int i = 0; i < PAGE; i++) {
                    System.out.print((char) raf.read());
                }
            } else {
                System.out.println("Вы ввели несуществующую страницу");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

