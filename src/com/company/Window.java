package com.company;

import javax.swing.*;
import java.awt.*;

public class Window extends JPanel {

    private Game game;


    public Window(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(900, 600));
        setBackground(Color.black);

    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.offg.setColor(Color.BLACK);
        game.offg.fillRect(0, 0, 900, 600);
        game.offg.setColor(Color.GREEN);
        game.offg.setFont(new Font ("MySTyle", Font.ROMAN_BASELINE, 30));
        game.offg.drawString("Lives: " + game.ship.lives, 20, 570);
        game.offg.drawString("Score: " + game.score, 20, 530);
        if (game.ship.lives == 0) {
            game.offg.setFont(new Font ("MySTyle", Font.ROMAN_BASELINE, 50));
            game.offg.setColor(Color.red);
            game.offg.drawString("Wasted", 380, 300);
            game.ship.active = false;
        }

        if (game.ship.active) {
            game.ship.paint(game.offg);
        }
        game.offg.setColor(Color.GRAY);
        for (int i = 0; i < game.asteroidList.size(); i++) {
            game.asteroidList.get(i).paint(game.offg);

        }
        game.offg.setColor(Color.red);
        for (int i = 0; i < game.bulletsList.size(); i++) {
            game.bulletsList.get(i).paint(game.offg);

        }
        if (game.asteroidList.isEmpty()) {
            game.offg.setColor(Color.CYAN);

            game.offg.drawString("Mission complete! ", 360, 300);
            game.offg.drawString("Go eat pizza and party!", 340, 330);
        }
        g.drawImage(game.offscreen, 0, 0, this);
        repaint();
    }




}
