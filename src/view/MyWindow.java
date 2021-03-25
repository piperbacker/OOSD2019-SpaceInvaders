package view;

import controller.KeyEventListener;
import controller.Main;
import controller.MouseEventListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyWindow extends JFrame {

    public MyCanvas canvas;
    public JButton startButton;

    public void init() {
        setSize(700, 500);
        setLocation(300, 200);
        setTitle("Game Engine");

        canvas = new MyCanvas();

        MouseEventListener listener = new MouseEventListener();
        canvas.addMouseListener(listener);
        canvas.addMouseMotionListener(listener);

        KeyEventListener keyEventListener = new KeyEventListener();
        canvas.addKeyListener(keyEventListener);
        canvas.setFocusable(true);


        var cp = getContentPane();
        cp.add(BorderLayout.CENTER, canvas);

        startButton = new JButton("Start");
        startButton.setFocusable(false);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        cp.add(BorderLayout.SOUTH, buttonPanel);

        // annonymous class, lambda function
        startButton.addActionListener(e -> {
            if (!Main.running) {   // start button pressed
                startButton.setText("Quit");
                Main.running = true;
            }
            else {  // quit button
                System.exit(0);
            }
        });

    }


}
