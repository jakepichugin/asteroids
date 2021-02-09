package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel {

    private Game game;

    public Menu(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(900, 600));
        setBackground(Color.black);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.offg.setColor(Color.black);
        game.offg.fillRect(0,0,900,600);
        game.offg.setColor(Color.red);
        game.offg.setFont(new Font("MySTyle", Font.ROMAN_BASELINE, 30));
        game.offg.drawString("Menu" , 410, 80);

        //Create JButton
        JButton startButton = new JButton("Start");
        // add the button
        this.add(startButton);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.startGame = true;
            }
        });

        g.drawImage(game.offscreen, 0, 0, this);
        repaint();

    }
}
