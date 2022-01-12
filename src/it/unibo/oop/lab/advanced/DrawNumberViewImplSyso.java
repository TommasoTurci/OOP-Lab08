package it.unibo.oop.lab.advanced;

/**
 * Graphical {@link DrawNumberView} implementation.
 */
public final class DrawNumberViewImplSyso implements DrawNumberView {

    private static final String NEW_GAME = ": a new game starts!";

    private DrawNumberViewObserver observer;

    /**
     * 
     */
    //public DrawNumberViewImplSyso() {}

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
        System.out.println("You lost" + NEW_GAME);
    }

    private void plainMessage(final String msg) {
        System.out.println(msg);
    }

    @Override
    public void displayError(final String message) {
        System.out.println(message);
    }
}
