package factory.Distributors;

import factory.BusinessEntity;
import factory.Consumers.Consumer;
import contracts.Contract;
import factory.Producers.Producer;
import inputfiles.DistributorInput;
import strategies.EnergyChoiceStrategyType;

import java.util.ArrayList;

public final class Distributor extends Observer implements BusinessEntity {
    private int id;
    private int contractLength;
    private int budget;
    private int infrastructureCost;
    private boolean isBankrupt;
    private ArrayList<Contract> contracts;
    private int price;
    public static final double PROFIT_PERCENTAGE = 0.2;
    private int energyNeededKW;
    private EnergyChoiceStrategyType producerStrategy;
    private ArrayList<Producer> producers;
    private Strategy strategy;
    private boolean reconsiderProducers;

    public Distributor(final DistributorInput input, Strategy strategy) {
        super();
        this.id = input.getId();
        this.contractLength = input.getContractLength();
        this.budget = input.getInitialBudget();
        this.infrastructureCost = input.getInitialInfrastructureCost();
        this.isBankrupt = false;
        this.contracts = new ArrayList<>();
        this.energyNeededKW = input.getEnergyNeededKW();
        this.producerStrategy = stringToStrategy(input.getProducerStrategy());
        this.producers = new ArrayList<>();
        this.strategy = strategy;
        this.strategy.setDistributor(this);
        this.reconsiderProducers = true;
    }

    public int getId() {
        return id;
    }

    public int getContractLength() {
        return contractLength;
    }

    public int getBudget() {
        return budget;
    }

    public int getInfrastructureCost() {
        return infrastructureCost;
    }

    public int getPrice() {
        return this.price;
    }

    public boolean getIsBankrupt() {
        return isBankrupt;
    }

    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public EnergyChoiceStrategyType getProducerStrategy() {
        return producerStrategy;
    }

    public ArrayList<Producer> getProducers() {
        return producers;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public boolean getReconsiderProducers() {
        return reconsiderProducers;
    }

    public void setReconsiderProducers(final boolean reconsiderProducers) {
        this.reconsiderProducers = reconsiderProducers;
    }

    public void setPrice(final int price) {
        this.price = price;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public void setContractLength(final int contractLength) {
        this.contractLength = contractLength;
    }

    public void setBudget(final int budget) {
        this.budget = budget;
    }

    public void setInfrastructureCost(final int infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    public void setIsBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public ArrayList<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(final ArrayList<Contract> contracts) {
        this.contracts = contracts;
    }

    public void setEnergyNeededKW(final int energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public void setProducerStrategy(final EnergyChoiceStrategyType producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    public void setProducers(final ArrayList<Producer> producers) {
        this.producers = producers;
    }

    /**
     * Adds the value of x to the budget of the distributor.
     * @param x
     */
    public void addToBudget(final int x) {
        this.budget += x;
    }

    /**
     * This method calculates the price for the new contracts in the current month
     * and puts it into the "price" field.
     */
    public void getThisMonthPrice() {
        if (isBankrupt) {
            return;
        }

        int profit = (int) Math.round(Math.floor(PROFIT_PERCENTAGE * getProductionCost()));

        if (contracts == null || contracts.size() == 0) {
           this.price = infrastructureCost + getProductionCost() + profit;
        } else {
            this.price = (int) Math.round(Math.floor(infrastructureCost / contracts.size())
                    + getProductionCost() + profit);
        }
    }

    /**
     * @return the production cost of a distributor, considering the prices of the producers
     * it buys energy from.
     */
    public int getProductionCost() {
        int cost = 0;
        for (Producer i : this.getProducers()) {
            cost += i.getEnergyPerDistributor() * i.getPriceKW();
        }
        return (int) Math.round(Math.floor(cost / 10));
    }

    /**
     * @return The cost of the distributor this month.
     */
    public int getMonthlyCost() {
        return  infrastructureCost + getProductionCost() * this.contracts.size();
    }

    /**
     * This method simulates the distributor's payments in the current month
     */
    public void payDebts() {
        if (isBankrupt) {
            return;
        }

        this.budget -= getMonthlyCost();

        if (budget < 0) {
            this.getBankRupt();
        }
    }

    /**
     * This method applies the effects of a distributor getting bankrupt.
     */
    public void getBankRupt() {
        this.isBankrupt = true;
        this.contracts = null;
        for (Producer i : this.getProducers()) {
            i.getClients().remove(this);
            i.getObservers().remove(this);
        }
    }

    /**
     * This method simulates the "checking" of te contracts of a distributor
     * and decrements the number of months left of every contract.
     */
    public void checkContracts() {
        if (isBankrupt) {
            return;
        }
        if (contracts == null) {
            return;
        }

        for (Contract i : contracts) {
            i.decrementMonths();
        }
    }

    /**
     * Removes the contracts with bankrupt consumers from the distributor's list.
     * @param consumerList - the list of all the consumers
     */
    public void deleteBankrupts(final ArrayList<Consumer> consumerList) {

        if (contracts == null) {
            return;
        }
        this.contracts.removeIf(i -> consumerList.get(i.getConsumerId()).getIsBankrupt());
    }

    /**
     * Removes the finished contracts from the distributor's list
     */
    public void deleteFinishedContracts() {
        if (isBankrupt) {
            return;
        }

        if (contracts == null) {
            return;
        }
        this.contracts.removeIf(i -> i.getRemainedContractMonths() == 0);
    }

    /**
     * This method simulates the search for producers of a distributor (if needed), applying the
     * strategy of the given distributor
     * @param producerList
     */
    public void findProducers(final ArrayList<Producer> producerList) {
        if (this.getIsBankrupt()) {
            return;
        }

        if (this.getReconsiderProducers()) {

            for (Producer i : this.getProducers()) {
                i.getClients().remove(this);
                i.getObservers().remove(this);
            }
            this.getProducers().clear();

            this.getStrategy().findProducers(producerList);
            this.setReconsiderProducers(false);
        }

    }

    /**
     *
     * @param word represents the name of one of the strategies possible
     * @return the EnergyChoiceStrategyType object with the name given
     */
    public static EnergyChoiceStrategyType stringToStrategy(final String word) {
        switch (word) {
            case "GREEN": return EnergyChoiceStrategyType.GREEN;
            case "PRICE": return EnergyChoiceStrategyType.PRICE;
            case "QUANTITY": return EnergyChoiceStrategyType.QUANTITY;
            default: return null;
        }
    }

    /**
     * Distributors keep the fact that it needs to reconsider his producers when notified.
     */
    @Override
    public void update() {
        this.setReconsiderProducers(true);
    }
}
