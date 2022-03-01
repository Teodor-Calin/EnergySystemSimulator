package inputfiles;

import java.util.ArrayList;

public final class Input {
    private int numberOfTurns;
    private InitialData initialData;
    private ArrayList<UpdateInput> monthlyUpdates;

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public InitialData getInitialData() {
        return initialData;
    }

    public ArrayList<UpdateInput> getMonthlyUpdates() {
        return monthlyUpdates;
    }

    public void setNumberOfTurns(final int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    public void setInitialData(final InitialData initialData) {
        this.initialData = initialData;
    }

    public void setMonthlyUpdates(final ArrayList<UpdateInput> monthlyUpdates) {
        this.monthlyUpdates = monthlyUpdates;
    }
}
