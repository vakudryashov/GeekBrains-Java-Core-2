package Lesson_01;

public class App {
    public static void main(String[] args) {
        Actions[] members = {
            new Human("Василий Иванович", 1, 500),
            new Cat("Васька", 2, 1000),
            new Robot("В2 Ск2", 3, 5000)
        };

        Obstacle[] obstacles = {
            new Wall(1),
            new Treadmill(500),
            new Wall(1.5),
            new Treadmill(2000),
            new Wall(3),
            new Treadmill(5000)
        };

        for (Obstacle obstacle : obstacles){
            for (Actions member : members){
                if (member.getLost() || !obstacle.pass(member)){
                    member.setLost(true);
                    continue;
                }
            }
            System.out.println();
        }
    }
}
