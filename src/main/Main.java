package main;

import ui.GamePanel;

import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        JFrame window = new JFrame();
        window.setTitle("Snake and The Mighty Fruit");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();
        window.setLocationRelativeTo(null); // to make the frame on center screen
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
