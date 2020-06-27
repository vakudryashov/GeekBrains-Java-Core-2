package Lesson_05;

import java.util.Arrays;

public class App{
    static final int size = 10_000_000;
    static final int h = size / 2;

    public static void main(String[] args) {
        long t1 = oneThreadFill();
        System.out.printf("Обработка массива в одном потоке заняла %d милисекунд\n",t1);
        long t2 = 0;
        try {
            t2 = multiThreadFill();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Обработка массива в двух потоках заняла %d милисекунд\n",t2);
        System.out.printf("Второй метод быстрее на %.0f%%",(double)(t1-t2)/t1*100);

    }
    public static long oneThreadFill(){
        float[] arr = new float[size];
        Arrays.fill(arr,1);
        long time = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        return System.currentTimeMillis() - time;
    }

    public static long multiThreadFill() throws InterruptedException {
        float[] arr = new float[size];
        Arrays.fill(arr,1);

        long time = System.currentTimeMillis();
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        Thread thread1 = new Thread(() -> oneThread(a1,0));
        Thread thread2 = new Thread(() -> oneThread(a2, h));
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        return System.currentTimeMillis() - time;
    }

    public static void oneThread(float[] arr, int start){
        for (int i = 0; i < arr.length; i++) {
            int j = i + start;
            arr[i] = (float)(arr[i] * Math.sin(0.2f + j / 5) * Math.cos(0.2f + j / 5) * Math.cos(0.4f + j / 2));
        }
    }

}
