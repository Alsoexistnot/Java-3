import java.util.concurrent.BrokenBarrierException;

public class Road extends Stage {
    public static int count = 0;

    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            if (count == 0){
                MainClass.cb.await();
                count++;
            }
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
            if (this.length == 40){
                MainClass.finish.countDown();
                c.setCount(MainClass.ai.getAndIncrement());
            }
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
