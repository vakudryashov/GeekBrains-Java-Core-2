package Lesson_05;

import java.util.Arrays;

public class Multithreading{
    private final float[] arr;
    private final long time;

    public Multithreading(float[] arr) {
        this.arr = arr;
        Arrays.fill(this.arr,1);
        time = System.currentTimeMillis();
    }

    private void realFill(float[] a, int base){
        for (int i = 0; i < a.length; i++) {
            int j = i + base;
            a[i] = (float)(a[i] * Math.sin(0.2f + j / 5) * Math.cos(0.2f + j / 5) * Math.cos(0.4f + j / 2));
        }
    }

    public long oneThreadFill(){
        realFill(arr,0);
        return System.currentTimeMillis() - time;
    }

    public long twoThreadFill(){
        int h = arr.length / 2;
        float[][] half = new float[2][h];
        Thread[] thread = new Thread[2];
        for (int i = 0; i < 2; i++) {
            int j = i;
            System.arraycopy(arr, h * i, half[i], 0, h);
            thread[i] = new Thread(() -> realFill(half[j],h * j));
            thread[i].start();
        }
        for (Thread t : thread) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return System.currentTimeMillis() - time;
    }
}
