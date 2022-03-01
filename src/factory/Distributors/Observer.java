package factory.Distributors;

public abstract class Observer {

    /**
     * This method is called by the observable objects to notify the observers
     * that changes have been made.
     */
    public abstract void update();
}
