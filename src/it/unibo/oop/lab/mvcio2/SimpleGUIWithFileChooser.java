package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    /*
     * TODO: Starting from the application in mvcio:
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface.
     * Suggestion: use a second JPanel with a second BorderLayout, put the panel
     * in the North of the main panel, put the text field in the center of the
     * new panel and put the button in the line_end of the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the
     * current selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should
     * use the method showSaveDialog() to display the file chooser, and if the
     * result is equal to JFileChooser.APPROVE_OPTION the program should set as
     * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
     * then the program should do nothing. Otherwise, a message dialog should be
     * shown telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to
     * update the UI: in this example the UI knows when should be updated, so
     * try to keep things separated.
     */
    private final JFrame frame = new JFrame();
    private final Controller control = new Controller();
    /**
     * builds a new {@link SimpleGUIWithFileChooser}.
     */
    public SimpleGUIWithFileChooser(){

        //Save and text area
        final JPanel canvas = new JPanel();
        final JButton btnSave =  new JButton("Save");
        final JTextArea textarea = new JTextArea();

        canvas.setLayout(new BorderLayout());
        canvas.add(textarea);
        canvas.add(btnSave, BorderLayout.SOUTH);
        textarea.setLineWrap(true);

        //Browse and field
        final JPanel browsezone = new JPanel();
        final JButton btnBrowse = new JButton("Browse");
        final JTextField field = new JTextField();

        field.setEditable(false);
        field.setText(control.getPath());
        browsezone.setLayout(new BorderLayout());
        browsezone.add(btnBrowse, BorderLayout.EAST);
        browsezone.add(field);

        //Main panel
        final JPanel mainpanel = new JPanel();

        mainpanel.setLayout(new BorderLayout());
        mainpanel.add(canvas);
        mainpanel.add(browsezone, BorderLayout.NORTH);

        frame.setContentPane(mainpanel);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                control.editFile(textarea.getText());
            } });
        btnBrowse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser selectfile = new JFileChooser();
                if (selectfile.showSaveDialog(mainpanel) == JFileChooser.APPROVE_OPTION) {
                    control.setFile(selectfile.getSelectedFile());
                    field.setText(control.getPath());
                }
            } });
        /*
         * Make the frame half the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected.
         * 
         * In order to deal coherently with multimonitor setups, other
         * facilities exist (see the Java documentation about this issue). It is
         * MUCH better than manually specify the size of a window in pixel: it
         * takes into account the current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
    /**
     * Function main.
     * @param args
     */
    public static void main(final String... args) {
        new SimpleGUIWithFileChooser();
    }

}

