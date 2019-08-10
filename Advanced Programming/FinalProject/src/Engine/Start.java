package Engine;

import Serialization.Serialize;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class Start extends MouseAdapter {
    private JFrame frame;
    private JLabel label;
    private JLabel label2;
    public static Clip clip = null;
    private int mouseX;
    private int mouseY;

    private boolean flag = false;
    private boolean flag2 = false;
    private boolean load = false;
    private GameStateSuperInfo gameStateSuperInfo ;

    public Start(GameStateSuperInfo gameStateSuperInfo ) {

        this.gameStateSuperInfo = gameStateSuperInfo;
        frame = new JFrame();
        label = new JLabel();

        label2 = new JLabel();


        frame.setResizable(false);
        frame.setSize(GameFrame.GAME_WIDTH, GameFrame.GAME_HEIGHT);


        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./sound/start.wav").getAbsoluteFile());
            clip.open(audioInputStream);
            clip.start();
            clip.loop(2);
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }


        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2 , dim.height / 2 - frame.getSize().height / 2);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1778, 1000);
        label.setSize(1778, 1000);
        label2.setSize(1778, 1000);

        label2.addMouseListener(this);
        label.addMouseListener(this);


        Icon icon_page1 = new ImageIcon("./pictures/page1.jpg");
        Icon icon_page2 = new ImageIcon("./pictures/page2.jpg");

        label.setIcon(icon_page1);
        label2.setIcon(icon_page2);

        frame.add(label);
        frame.setVisible(true);

    }

    @Override
    public void mousePressed(MouseEvent e) {

        mouseX = e.getX();
        mouseY = e.getY();

        //click on page2
        if (mouseX < 296 && mouseX > 128 && mouseY < 406 && mouseY > 330 && flag) {
            runTheGame();
            flag2 = true;
            frame.setVisible(false);
            playSelectMusic();
            GameState.setDifficultyLevel(1);
        }
        if (mouseX < 317 && mouseX > 134 && mouseY < 535 && mouseY > 468 && flag == true) {
            runTheGame();
            System.out.println("Normal");
            flag2 = true;
            frame.setVisible(false);
            playSelectMusic();
            GameState.setDifficultyLevel(2);

        }
        if (mouseX < 278 && mouseX > 127 && mouseY < 675 && mouseY > 609 && flag == true) {
            runTheGame();
            System.out.println("Hard");
            flag2 = true;
            frame.setVisible(false);
            playSelectMusic();
            GameState.setDifficultyLevel(3);
        }
        if (mouseX < 310 && mouseX > 127 && mouseY < 806 && mouseY > 740 && flag == true) {
            load = true ;
            runTheGame();
            System.out.println("Load");
            flag2 = true;
            frame.setVisible(false);
            playSelectMusic();
            GameState.setDifficultyLevel(2);
        }


        //click on page1
        if (mouseX < 315 && mouseX > 134 && mouseY < 400 && mouseY > 331 && flag == false) {

            System.out.println("Co-op");
            frame.remove(label);
            frame.add(label2);
            frame.invalidate();
            frame.validate();
            frame.repaint();
            playSelectMusic();

            flag = true;

        }
        if (mouseX < 403 && mouseX > 131 && mouseY < 536 && mouseY > 470 && flag == false) {

            System.out.println("play");
            frame.remove(label);
            frame.add(label2);
            frame.invalidate();
            frame.validate();
            frame.repaint();
            playSelectMusic();

            flag = true;

        }
        if (mouseX < 313 && mouseX > 135 && mouseY < 677 && mouseY > 611 && flag == false) {

            System.out.println("Exit");
            System.exit(0);
            playSelectMusic();

            flag = true;
        }


    }

    private void runTheGame() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameFrame frame = new GameFrame("Tanki !");
                frame.setLocationRelativeTo(null); // put frame at center of screen
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.initBufferStrategy();
                // Create and execute the game-loop
                GameLoop game = new GameLoop(frame , gameStateSuperInfo , load);
                game.init();
                ThreadPool.execute(game);
                // and the game starts ...
            }
        });
    }

    public boolean isFlag2() {
        return flag2;
    }

    private void playSelectMusic () {
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(
                    new File("./sound/tick.wav"));
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            clip.open(audioInputStream);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FloatControl gainControl =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//        gainControl.setValue(-3.0f); // Reduce volume by 10 decibels.
        clip.start();
    }

}
