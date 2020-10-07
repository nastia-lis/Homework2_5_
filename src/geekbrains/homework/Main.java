package geekbrains.homework;

import java.util.Arrays;

public class Main {

    final static int SIZE = 10000000;
    final static int HALF_SIZE = SIZE / 2;

    public static void main(String[] args) {
        method_1();
        method_2();
    }

    static void method_1() {
        float[] testArray = new float[SIZE];
        for (int i = 0; i < testArray.length; i++) {
            testArray[i] = 1;
        }
        long timeMillis = System.currentTimeMillis();
        for (int i = 0; i < testArray.length; i++) {
            testArray[i] = (float) (testArray[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Время работы 1 метода: " + (System.currentTimeMillis() - timeMillis));
    }

    static void method_2() {
        float[] testArray = new float[SIZE];
        float[] testArray_1 = new float[HALF_SIZE];
        float[] testArray_2 = new float[HALF_SIZE];
        for (int i = 0; i < testArray.length; i++) {
            testArray[i] = 1;
        }
        long time_1 = System.currentTimeMillis();
        System.arraycopy(testArray, 0, testArray_1, 0, HALF_SIZE);
        System.arraycopy(testArray, HALF_SIZE, testArray_2, 0, HALF_SIZE);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < testArray_1.length; i++) {
                testArray_1[i] = (float) (testArray_1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < testArray_2.length; i++) {
                testArray_2[i] = (float) (testArray_2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });
        t1.start();
        t2.start();

        System.arraycopy(testArray_1, 0, testArray, 0, HALF_SIZE);
        System.arraycopy(testArray_2, 0, testArray, HALF_SIZE, HALF_SIZE);

        System.out.println("Время работы 2 метода: " + (System.currentTimeMillis() - time_1));
    }
}
