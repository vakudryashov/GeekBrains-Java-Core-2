package Lesson_01;

public class Human implements Actions {
    private String name;
    private double maxHeight;
    private double maxLength;
    private boolean lost = false;

    public Human(String name, double maxHeight, double maxLength) {
        this.name = name;
        this.maxHeight = maxHeight;
        this.maxLength = maxLength;
    }

    @Override
    public double getMaxHeight() {
        return maxHeight;
    }

    @Override
    public double getMaxLength() {
        return maxLength;
    }

    @Override
    public void jump(){
        System.out.printf("Человек c именем %s совершает прыжок в высоту.%n", name);
    }

    @Override
    public void run() {
        System.out.printf("Человек с именем %s бежит дистанцию.%n", name);
    }

    @Override
    public boolean getLost() {
        return lost;
    }

    @Override
    public void setLost(boolean lost) {
        this.lost = lost;
    }
}
