package view;

import controller.Main;

import javax.swing.*;
import java.awt.*;

public class MyCanvas extends JPanel {

    public int width;
    public int height;

    public void render(){  // double buffer with CPU and GPU
        width = getSize().width;
        height = getSize().height;

        // offscreen double buffer image
        Image doubleBufferImage = createImage(width, height);
        if (doubleBufferImage == null) {
            System.out.println("Critical error: doubleBufferImage is null");
            System.exit(1);
        }

        // offscreen rendering
        Graphics2D g2OffScreen = (Graphics2D) doubleBufferImage.getGraphics();
        if (g2OffScreen == null){
            System.out.println("Critical error: g2OffScreen is null");
            System.exit(1);
        }

        // initialize the image buffer
        g2OffScreen.setBackground(Color.BLACK);
        g2OffScreen.clearRect(0,0, width, height);

        // render all game data here !!
        //g2OffScreen.setColor(Color.RED);
        //g2OffScreen.drawOval(100,100,50,50);

        for (var fig: Main.gameData.fixedObject) {
            fig.render(g2OffScreen);
        }

        for (var fig: Main.gameData.friendObject) {
            fig.render(g2OffScreen);
        }

        for (var fig: Main.gameData.enemyObject) {
            fig.render(g2OffScreen);
        }

        // use active rendering to put buffer image on screen
        Graphics gOnScreen;
        gOnScreen = this.getGraphics();
        if (gOnScreen != null){
            // copy offscreen image to onscreen
            gOnScreen.drawImage(doubleBufferImage, 0,0, null);
        }

        Toolkit.getDefaultToolkit().sync(); // sync the display on some systems
        if (gOnScreen != null){
            gOnScreen.dispose();
        }


    }
}
