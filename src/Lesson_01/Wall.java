package Lesson_01;

public class Wall extends Obstacle{
    private double height;

    public Wall(double height) {
        this.height = height;
    }

    @Override
    public boolean pass(Actions member) {
        member.jump();
        return check(member.getMaxHeight());
    }

    public boolean check(double maxHeight){
        boolean result = maxHeight >= height;

        System.out.printf( result ? "Успешно преодолел стену высотой %.1f м %n" : "Не смог преодолеть стену высотой %.1f м %n", height);

        return result;
    }
}
