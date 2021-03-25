package controller;

import controller.observer.UFOObserverAddNew;
import model.*;
import model.Alien.Alien;
import model.Shield.Shield;
import model.Spaceship;
import view.MyWindow;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static MyWindow win;
    public static GameData gameData;
    public static PlayerInputEventQueue playerInputEventQueue;
    public static Boolean running = false;

    public static int INDEX_MOUSE_POINTER = 0; // in GameData.fixedObject
    public static int INDEX_SHOOTER = 1;
    public static int INDEX_UFO = 2;

    public static int FPS = 20; // frames per second

    public static int livesTotal = 3;
    public static int livesRem = livesTotal;

    public static int Score = 0;
    static Timer timer = new Timer();

    public static void main (String[] args){
        win = new MyWindow();
        win.init();
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);

        gameData = new GameData();
        playerInputEventQueue = new PlayerInputEventQueue();  // store whenever event is generated, proccessed at beginning of each game loop

        startScreen();
        initGame();
        gameLoop();

    }

    static void startScreen() {
        // show initial message on canvas
        Font font = new Font("Symbol", Font.BOLD, 40);
        gameData.friendObject.add(new Text("SPACE INVADERS", 200,200, Color.GREEN, font));
        Font font2 = new Font("Symbol", Font.BOLD, 25);
        gameData.friendObject.add(new Text("press START to begin playing", 200,250, Color.YELLOW, font2));
        while (! running) {
            Main.win.canvas.render();
            try{
                Thread.sleep(500);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        // finish when running == true;
    }

    static void defeatScreen() {
        Font font = new Font("Symbol", Font.BOLD, 40);
        gameData.friendObject.add(new Text("GAME OVER", 250,200, Color.GREEN, font));
        Font font2 = new Font("Symbol", Font.BOLD, 25);
        gameData.friendObject.add(new Text("press START to play again", 225,250, Color.YELLOW, font2));

        while (! running) {
            Main.win.canvas.render();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    static void victoryScreen() {
        Font font = new Font("Symbol", Font.BOLD, 40);
        gameData.friendObject.add(new Text("CONGRATULATIONS WINNER", 200,200, Color.GREEN, font));
        Font font2 = new Font("Symbol", Font.BOLD, 25);
        gameData.friendObject.add(new Text("you have beat SPACE INVADERS. \n press START to play again",
                200,250, Color.YELLOW, font2));

        while (! running) {
            Main.win.canvas.render();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    static void initGame() {
        gameData.clear();

        gameData.fixedObject.add(new MousePointer(0, 0));
        //gameData.friendObject.add(new Laser(50,50));  // giving last exception
        int x = Main.win.getWidth() / 2;
        int y = Main.win.getHeight() - 100;
        gameData.fixedObject.add(new Spaceship(x, y));

        //gameData.fixedObject.add(new Shield(100, 325));
        //gameData.fixedObject.add(new Shield(250, 325));
        //gameData.fixedObject.add(new Shield(450, 325));
        //gameData.fixedObject.add(new Shield(600, 325));

        addUFOWithListener(100, 70);
        addUFOWithListener(200, 70);
        addUFOWithListener(300, 70);
        addUFOWithListener(400, 70);

        Font font = new Font("Symbol", Font.BOLD, 25);
        Text m = new Text("Lives: " + Main.livesRem, 10, 20, Color.YELLOW, font);
        Main.gameData.fixedObject.add(m);


        //Timer timer = new Timer();
        TimerTask createAlienLaser = new Helper();
        timer.schedule(createAlienLaser, 2000, 5000);



    }

    public static void addUFOWithListener(int x, int y) {
        var ufo = new Alien(x,y, 20, 15);
        ufo.attachListener(new UFOObserverAddNew());
        gameData.enemyObject.add(ufo);

        var ufo1 = new Alien(x+100,y+50, 25, 10);
        ufo1.attachListener(new UFOObserverAddNew());
        gameData.enemyObject.add(ufo1);

        var ufo2 = new Alien(x+150,y+100, 30, 7);
        ufo2.attachListener(new UFOObserverAddNew());
        gameData.enemyObject.add(ufo2);

        var ufo3 = new Alien(x+200,y+150, 35, 5);
        ufo2.attachListener(new UFOObserverAddNew());
        gameData.enemyObject.add(ufo3);

    }

    static void gameLoop() {

        // game loop
        while(running){
            long startTime = System.currentTimeMillis();

            playerInputEventQueue.processInputEvents();
            processCollisions();
            gameData.update();

            /*Font font = new Font("Symbol", Font.BOLD, 25);
            Text m = new Text("Lives: " + Main.livesRem, 10, 20, Color.YELLOW, font);
            Main.gameData.fixedObject.add(m); */

            win.canvas.render();

            long endTime = System.currentTimeMillis();
            long timeSpent = endTime - startTime;
            long sleepTime = (long) ((1000.0 / FPS) - timeSpent); // time between frames

            try{
                if(sleepTime > 0) Thread.sleep(sleepTime);
            } catch(InterruptedException e){
                e.printStackTrace();
            }

            if (livesRem > 0) {
                running = true;
            }
            else {
                timer.cancel();
                running = false;
                gameData.clear();
                defeatScreen();
            }

            if (Score == 5000) {
                victoryScreen();
            }
        }

    }

    static void processCollisions(){
        var spaceship = (Spaceship) Main.gameData.fixedObject.get(Main.INDEX_SHOOTER);
        for (var enemy: Main.gameData.enemyObject) {
            if (spaceship.collideWith(enemy)) {
                ++spaceship.hitCount;
                ++enemy.hitCount;
            }
        }

        for (var friend: Main.gameData.friendObject) {
            for (var enemy: Main.gameData.enemyObject) {
                if(friend.collideWith(enemy)) {
                    ++friend.hitCount;
                    ++enemy.hitCount;
                }
            }
        }

    }

}
