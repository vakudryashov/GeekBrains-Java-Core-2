package Lesson_05;

public class App{
    static final int size = 10_000_000;

    public static void main(String[] args) {
        Multithreading obj1 = new Multithreading(new float[size]);
        long t1 = obj1.oneThreadFill();
        System.out.printf("Обработка массива в одном потоке заняла %d милисекунд\n", t1);

        Multithreading obj2 = new Multithreading(new float[size]);
        long t2 = obj2.twoThreadFill();
        System.out.printf("Обработка массива в двух потоках заняла %d милисекунд\n", t2);
        System.out.printf("Второй метод быстрее на %.0f%%",(double)(t1-t2)/t1*100);
    }


}
