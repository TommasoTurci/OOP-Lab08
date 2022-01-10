package it.unibo.oop.lab.simplegui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JTextField;

/**
 * Java COCK.
 */
public class NewGUI {
    /**
     */
    private final JFrame frame = new JFrame("My first GUI!");
    private final Random rng = new Random();
    /**
     */
    public NewGUI() {
        final JPanel canvas = new JPanel();
        final JTextField result = new JTextField();
        canvas.setLayout(new BorderLayout());
        frame.setContentPane(canvas);
        final JButton buttbutt = new JButton("OwO UwU TwT ^w^ QwQ");
        canvas.add(buttbutt);
        canvas.add(result, BorderLayout.NORTH);
        buttbutt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i=rng.nextInt();
                result.setText(String.valueOf(i));
                System.out.println(i);
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
       new NewGUI().display();
    }
}
