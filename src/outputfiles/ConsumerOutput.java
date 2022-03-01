package outputfiles;

import factory.Consumers.Consumer;

public final class ConsumerOutput {
    private int id;
    private boolean isBankrupt;
    private int budget;

    public ConsumerOutput(final Consumer c) {
        this.id = c.getId();
        this.isBankrupt = c.getIsBankrupt();
        this.budget = c.getBudget();
    }

    public int getId() {
        return id;
    }

    public boolean getIsBankrupt() {
        return isBankrupt;
    }

    public int getBudget() {
        return budget;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public void setIsBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public void setBudget(final int budget) {
        this.budget = budget;
    }
}
