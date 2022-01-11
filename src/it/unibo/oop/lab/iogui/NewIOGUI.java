package it.unibo.oop.lab.iogui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JTextField;

/**
 * Java COCK.
 */
public class NewIOGUI {
    /**
     */
    private final JFrame frame = new JFrame("My first GUI!");
    private final Random rng = new Random();
    private static final String PATH = System.getProperty("user.home")
            + System.getProperty("file.separator")
            + BadIOGUI.class.getSimpleName() + ".txt";
    /**
     */
    public NewIOGUI() {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BoxLayout(canvas, 0));
        frame.setContentPane(canvas);
        final JButton write = new JButton("Write!");
        canvas.add(write);
        final JButton read = new JButton("Read!");
        canvas.add(read);
        write.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try (PrintStream ps = new PrintStream(PATH)) {
                    ps.print(rng.nextInt());
                } catch (FileNotFoundException e1) {
                    JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                }
            }
        });
        read.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final File filetto=new File(PATH);
                try (Scanner sc = new Scanner(filetto);) {
                    System.out.println(sc.nextLine());
                } catch (FileNotFoundException e1) {
                    JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                }
            }
        });
    }
    void display() {
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * @param args ignored
     */
    public static void main(final String... args) {
       new NewIOGUI().display();
    }
}

