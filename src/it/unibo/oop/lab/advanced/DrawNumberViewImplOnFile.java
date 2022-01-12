package it.unibo.oop.lab.advanced;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Graphical {@link DrawNumberView} implementation.
 */
public final class DrawNumberViewImplOnFile implements DrawNumberView {

    private static final String NEW_GAME = ": a new game starts!";
    private static final String PATH = System.getProperty("user.home") + System.getProperty("file.separator") + "output.txt";


    private DrawNumberViewObserver observer;
    private PrintStream editable;
    
    /**
     * 
     */
    public DrawNumberViewImplOnFile() {
        try {
            editable = new PrintStream(PATH);
        } catch (FileNotFoundException e) {
            System.err.println("File not found at current directory.");
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
    }

    @Override
    public void setObserver(final DrawNumberViewObserver observer) {
        this.observer = observer;
    }

    @Override
    public void numberIncorrect() {
        System.out.println("Incorrect Number.. try again");
    }

    @Override
    public void result(final DrawResult res) {
        switch (res) {
        case YOURS_HIGH:
        case YOURS_LOW:
            plainMessage(res.getDescription());
            return;
        case YOU_WON:
            plainMessage(res.getDescription() + NEW_GAME);
            break;
        default:
            throw new IllegalStateException("Unexpected result: " + res);
        }

        observer.resetGame();
    }

    @Override
    public void limitsReached() {
            editable.append("You lost" + NEW_GAME + "\n");

    }

    private void plainMessage(final String msg) {
            editable.append(msg + "\n");
    }

    @Override
    public void displayError(final String message) {
            editable.append(message + "\n");
    }
}
