package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedHashMap;
import java.util.Map;

public class Menu extends JPanel {

    private Game game;

    public Menu(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(900, 600));
        setBackground(Color.black);

        createButtons();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.offg.setColor(Color.black);
        game.offg.fillRect(0,0,900,600);
        game.offg.setColor(Color.red);
        game.offg.setFont(new Font("MySTyle", Font.ROMAN_BASELINE, 30));
        game.offg.drawString("Menu" , 410, 80);

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

        this.add(createColorSelector());
        this.add(createGunSelector());
    }

    private JComboBox createColorSelector() {

        Map<String, Color> colorsMap = new LinkedHashMap<>();
        colorsMap.put("Red", Color.RED);
        colorsMap.put("Green", Color.GREEN);
        colorsMap.put("Blue", Color.BLUE);
        colorsMap.put("Cyan", Color.CYAN);
        colorsMap.put("Magenta", Color.MAGENTA);
        colorsMap.put("Orange", Color.ORANGE);
        colorsMap.put("Yellow", Color.YELLOW);
        colorsMap.put("White", Color.WHITE);
        colorsMap.put("Pink", Color.PINK);
        colorsMap.put("Gray", Color.GRAY);
        colorsMap.put("Dark gray", Color.DARK_GRAY);

        JComboBox dropdown = new JComboBox(colorsMap.keySet().toArray());

        String shipColor = getKeyByValue(colorsMap, game.ship.getShipColor());

        shipColor = shipColor.substring(0, 1).toUpperCase() + shipColor.substring(1);

        dropdown.setSelectedItem(shipColor);

        dropdown.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == 1) {
                    game.ship.setShipColor(colorsMap.get(event.getItem()));
                }
            }
        });
        dropdown.setBounds(400, 330, 100, 40);
        dropdown.setFocusable(false);
        return dropdown;
    }

    private <K, V> K getKeyByValue(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue() == value) {
                return entry.getKey();
            }
        }
        return null;
    }

    private JComboBox createGunSelector() {
        Map<String, GunType> gunMap = new LinkedHashMap<>();
        gunMap.put("Pistol", GunType.PISTOL);
        gunMap.put("Machine Gun", GunType.MACHINE_GUN);
        gunMap.put("Shotgun", GunType.SHOTGUN);


        JComboBox dropdown = new JComboBox(gunMap.keySet().toArray());

        String gunName = getKeyByValue(gunMap, game.ship.getGunType());

        dropdown.setSelectedItem(gunName);

        dropdown.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == 1) {
                    game.ship.setGunType(gunMap.get(event.getItem()));
                }
            }
        });
        dropdown.setBounds(400, 260, 100, 40);
        dropdown.setFocusable(false);
        return dropdown;
    }

}
