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

        createButtons();

        g.drawImage(game.offscreen, 0, 0, this);
        repaint();

    }

    public void createButtons() {
        JButton startButton = new JButton("Start");
        this.setLayout(null);
        startButton.setBounds(400, 400, 100, 40);
        this.add(startButton);

        startButton.setFocusable(false);// makes so we can use keys after pressing button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.startGame = true;
            }
        });

        JButton shipColorButton = new JButton("Ship color");
        shipColorButton.setBounds(300, 350, 100, 40);
        this.add(shipColorButton);

        shipColorButton.setFocusable(false);// makes so we can use keys after pressing button
    }
}
