package controller;

import model.AlienLaser.AlienLaser;

import java.util.TimerTask;

public class Helper extends TimerTask {
    public static int i = 0;
    public void run()
    {
        //System.out.println("Timer ran " + ++i);
        AlienLaser m = new AlienLaser(100, 70);
        Main.gameData.enemyObject.add(m);
    }
}

