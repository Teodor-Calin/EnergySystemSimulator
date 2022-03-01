package factory.Producers;

import factory.Distributors.Observer;

import java.util.ArrayList;

public abstract class Observable {

    private ArrayList<Observer> observers;

    public Observable() {
        this.observers = new ArrayList<>();
    }

    public final ArrayList<Observer> getObservers() {
        return observers;
    }

    /**
     * Producers notify its observers that he suffered changes.
     */
    public void notifyObservers() {
        for (Observer i : this.getObservers()) {
            i.update();
        }
    }
}
