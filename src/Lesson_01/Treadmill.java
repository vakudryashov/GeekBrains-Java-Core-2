package Lesson_01;

public class Treadmill extends Obstacle {
    private double length;

    public Treadmill(double length) {
        this.length = length;
    }

    @Override
    public boolean pass(Actions member) {
        member.run();
        return check(member.getMaxLength());
    }

    @Override
    public boolean check(double maxLength){
        boolean result = maxLength >= length;

        System.out.printf( result ? "Успешно преодолел дистанцию длиной %.1f м %n" : "Не смог преодолеть дистанцию длиной %.1f м %n",length);

        return result;
    }
}
