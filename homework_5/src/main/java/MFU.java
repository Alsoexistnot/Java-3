public class MFU {
    public void scan() {
        Thread thread = new Thread(() -> {
            System.out.println("Сканирую");
        });
    }

    public void sendEmail() {
        Thread thread = new Thread(() -> {
            System.out.println("Отправляю эмейл");
        });
    }

    public void print() {
        Thread thread = new Thread(() -> {
            System.out.println("Печатаю");
        });
    }

    public void photocopy() {
        scan();
        print();
    }
}
