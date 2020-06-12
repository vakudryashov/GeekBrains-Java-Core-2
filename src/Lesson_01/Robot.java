package Lesson_01;

public class Robot implements Actions{
    private String name;
    private double maxHeight;
    private double maxLength;
    private boolean lost = false;

    public Robot(String name, double maxHeight, double maxLength) {
        this.name = name;
        this.maxHeight = maxHeight;
        this.maxLength = maxLength;
    }

    public double getMaxHeight() {
        return maxHeight;
    }

    public double getMaxLength() {
        return maxLength;
    }

    @Override
    public void jump(){
        System.out.printf("Робот с именем %s совершает прыжок в высоту.%n", name);
    }

    @Override
    public void run() {
        System.out.printf("Робот с именем %s бежит дистанцию.%n", name);
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


