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

        if (game.ship.active) {
            game.ship.paint(game.offg);
        }
        game.rock.paint(game.offg);
        g.drawImage(game.offscreen, 0, 0, this);
        repaint();
    }
}
