package com.example.threadlab;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
 This component draws two car shapes.
 */
public class CarPanel extends JComponent
{
    private Car car1;
    private int x,y, delay;
    private CarQueue carQueue;
    private int direction;

    CarPanel(int x1, int y1, int d, CarQueue queue)
    {
        delay = d;
        x=x1;
        y=y1;
        car1 = new Car(x, y, this);
        carQueue = queue;
    }
    public void startAnimation()
    {
        class AnimationRunnable implements Runnable
        {
            public void run()
            {
                try {
                    while (true)
                    {
                        direction = carQueue.deleteQueue();
                        int delta_y = 10, x1 = x, y1 = y;
                        int delta_x = delta_y;
                        switch (direction) {
                            case 0:
                                y1 = y1 - delta_y;
                                break;
                            case 1:
                                y1 = y1 + delta_y;
                                break;
                            case 2:
                                x1 = x1 + delta_x;
                                break;
                            case 3:
                                x1 = x1 - delta_x;
                                break;
                        }
                        if (x1 < 0 || x1 > 300) {
                            delta_x = delta_x * -1;
                        }
                        if (y1 < 0 || y1 > 400) {
                            delta_y = delta_y * -1;
                        }
                        switch (direction) {
                            case 0:
                                y = y - delta_y;
                                break;
                            case 1:
                                y = y + delta_y;
                                break;
                            case 2:
                                x = x + delta_x;
                                break;
                            case 3:
                                x = x - delta_x;
                                break;
                        }
                        repaint();
                        Thread.sleep(delay * 1000);

                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        Runnable r = new AnimationRunnable();
        Thread t = new Thread(r);
        t.start();
    }

    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        car1.draw(g2,x,y);
    }
}