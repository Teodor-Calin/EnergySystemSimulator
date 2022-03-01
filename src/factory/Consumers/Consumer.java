package factory.Consumers;

import contracts.Contract;
import factory.BusinessEntity;
import factory.Distributors.Distributor;
import inputfiles.ConsumerInput;

public final class Consumer implements BusinessEntity {
    private int id;
    private int budget;
    private int monthlyIncome;
    private boolean isBankrupt;
    private final Contract currentContract;
    private int penalty;
    private Distributor lastPenaltyDistributor;
    private Distributor chainedDistributor;
    public static final double PENALTY_FACTOR = 1.2;

    public Consumer(final ConsumerInput input) {
        this.id = input.getId();
        this.budget = input.getInitialBudget();
        this.isBankrupt = false;
        this.monthlyIncome = input.getMonthlyIncome();
        this.penalty = 0;
        this.currentContract = new Contract(id);
    }

    public int getId() {
        return id;
    }

    public int getBudget() {
        return budget;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    public boolean getIsBankrupt() {
        return isBankrupt;
    }

    public Contract getCurrentContract() {
        return currentContract;
    }

    public Distributor getChainedDistributor() {
        return chainedDistributor;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public void setBudget(final int budget) {
        this.budget = budget;
    }

    public void setMonthlyIncome(final int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public void setIsBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public void setChainedDistributor(final Distributor chainedDistributor) {
        this.chainedDistributor = chainedDistributor;
    }

    /**
     * Adds the monthly income of the consumer to his budget
     */
    public void addIncomeToBudget() {
        if (isBankrupt) {
            return;
        }
        this.budget += monthlyIncome;
    }

    /**
     * This method simulates the making of a contract between the consumer
     * and the distributor given as parameter
     * @param distributor
     */
    public void setUpContract(final Distributor distributor) {
        if (isBankrupt) {
            return;
        }
        if (chainedDistributor == null
                || chainedDistributor.getIsBankrupt()
                || currentContract.getRemainedContractMonths() == 0) {
            currentContract.setRemainedContractMonths(distributor.getContractLength());
            currentContract.setPrice(distributor.getPrice());
            this.setChainedDistributor(distributor);
            distributor.getContracts().add(currentContract);
        }
    }

    /**
     * This method simulates the payment of the consumer to the
     * distributor who has a contract with him and the payment of the penalty
     */
    public void payDebts() {
        if (isBankrupt) {
            return;
        }

        if (this.budget >= this.getCurrentContract().getPrice() + penalty) {
            budget -= (this.getCurrentContract().getPrice() + penalty);
            this.getChainedDistributor()
                    .addToBudget(this.getCurrentContract().getPrice());
            if (lastPenaltyDistributor != null) {
                lastPenaltyDistributor.addToBudget(penalty);
            }
            penalty = 0;
            lastPenaltyDistributor = null;

        } else {
            if (penalty != 0) {
                this.isBankrupt = true;

            } else {
                penalty = (int) Math.round(Math.floor(PENALTY_FACTOR
                                                        * this.currentContract.getPrice()));
                lastPenaltyDistributor = chainedDistributor;
            }
        }
    }
}
