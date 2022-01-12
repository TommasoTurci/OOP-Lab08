package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private int min;
    private int max = 100;
    private int attempts = 10;
    private final DrawNumber model;
    private final List<DrawNumberView> view;


    /**
     * @throws IOException 
     * @param views
     */
    public DrawNumberApp(final DrawNumberView... views) throws IOException {
        readConfig();
        this.model = new DrawNumberImpl(min, max, attempts);
        this.view = new LinkedList<>();
        this.view.addAll(Arrays.asList(views));
        for (final DrawNumberView tview : view) {
            tview.setObserver(this);
        }
        for (final DrawNumberView tview : view) {
            tview.start();
        }

    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView tview : view) {
                tview.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView tview : view) {
                tview.numberIncorrect();
            }
        } catch (AttemptsLimitReachedException e) {
            for (final DrawNumberView tview : view) {
                tview.limitsReached();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    private void readConfig() throws IOException {
        final JFileChooser selectfile = new JFileChooser();
        final FileNameExtensionFilter filter = new FileNameExtensionFilter(".yml Config files", "yml");
        selectfile.setFileFilter(filter);
        if (selectfile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(selectfile.getSelectedFile()));
                String[] temp = reader.readLine().split(" ");
                min = Integer.parseInt(temp[1]);
                temp = reader.readLine().split(" ");
                max = Integer.parseInt(temp[1]);
                temp = reader.readLine().split(" ");
                attempts = Integer.parseInt(temp[1]);
                reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * @param args
     *            ignored
     * @throws IOException 
     */
    public static void main(final String... args) throws IOException {
        final DrawNumberViewImpl msgDialogView = new DrawNumberViewImpl();
        final DrawNumberViewImplSyso stdOutDialogView = new DrawNumberViewImplSyso();
        final DrawNumberViewImplOnFile fileDialogView = new DrawNumberViewImplOnFile();
        new DrawNumberApp(msgDialogView, stdOutDialogView, fileDialogView);
    }

}
