package com.example.threadlab;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class CarQueue  {
    Random random;
    Queue<Integer> queue;
    public CarQueue() {
        random = new Random();
        queue = new ArrayDeque<Integer>();
        queue.add(random.nextInt(4));
        queue.add(random.nextInt(4));
        queue.add(random.nextInt(4));
        queue.add(random.nextInt(4));
        queue.add(random.nextInt(4));
    }
    public void addToQueue() {
        class Run implements Runnable {
            @Override
            public void run() {

                try {
                    while (true) {
                        queue.add(random.nextInt(4));
                        Thread.sleep(100);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
        Run myRun = new Run();
        Thread runThread=new Thread(myRun);
        runThread.start();
    }

    public int deleteQueue() {
        int randomnum;
        randomnum = queue.remove();
        return randomnum;
    }
}
