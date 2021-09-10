public class Main {
    private final Object monitor = new Object();
    private volatile char currentLetter = 'D';


    public static void main(String[] args) {
        Main waitNotify = new Main();
        Thread threadA = new Thread(() -> {
            waitNotify.printA();
        });
        Thread threadB = new Thread(() -> {
            waitNotify.printB();
        });
        Thread threadC = new Thread(() -> {
            waitNotify.printC();
        });
        threadA.start();
        threadB.start();
        threadC.start();
    }


    public void printA() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'D' && currentLetter != 'A') {
                        monitor.wait();
                    }

                        System.out.print("A");
                        currentLetter = 'B';
                        monitor.notifyAll();

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printB() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'B') {
                        monitor.wait();
                    }
                    System.out.print("B");
                    currentLetter = 'C';
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printC() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'C') {
                        monitor.wait();
                    }
                    System.out.print("C");
                    currentLetter = 'A';
                    monitor.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
