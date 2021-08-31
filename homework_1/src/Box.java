import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> fruitsBox;

    public Box() {
        fruitsBox = new ArrayList<>();
    }

    public float getWeight() {
        float fruitsWeight = 0f;
        if (fruitsBox.isEmpty()) {
            System.out.print("Коробка пустая. Вес: ");
        } else if (getFruitsBox().get(0) instanceof Apple) {
            for (int i = 0; i < getFruitsBox().size(); i++) {
                fruitsWeight += Apple.getAppleWeight();
            }
        } else if (getFruitsBox().get(0) instanceof Orange) {
            for (int i = 0; i < getFruitsBox().size(); i++) {
                fruitsWeight += Orange.getOrangeWeight();
            }
        }
        return fruitsWeight;
    }

    public void setFruitsBox(ArrayList<T> fruitsBox) {
        this.fruitsBox = fruitsBox;
    }

    public ArrayList<T> getFruitsBox() {
        return fruitsBox;
    }

    public boolean compare(Box<? extends Fruit> box) {
        if (getWeight() == box.getWeight()) {
            return true;
        }
        return false;
    }

    public void transfer(Box<T> box) {
        box.getFruitsBox().addAll(fruitsBox);
        fruitsBox.clear();
    }

}
