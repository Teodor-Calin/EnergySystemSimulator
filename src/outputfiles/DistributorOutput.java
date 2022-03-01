package outputfiles;

import contracts.Contract;
import factory.Distributors.Distributor;

import java.util.ArrayList;

public final class DistributorOutput {
    private int id;
    private int energyNeededKW;
    private int contractCost;
    private int budget;
    private String producerStrategy;
    private boolean isBankrupt;
    private ArrayList<Contract> contracts;

    public DistributorOutput(final Distributor d) {
        this.id = d.getId();
        this.energyNeededKW = d.getEnergyNeededKW();
        this.contractCost = d.getPrice();
        this.budget = d.getBudget();
        this.isBankrupt = d.getIsBankrupt();
        this.producerStrategy = d.getProducerStrategy().label;
        if (d.getContracts() == null) {
            this.contracts = new ArrayList<>();
        } else {
            this.contracts = new ArrayList<>(d.getContracts());
        }
    }

    public int getId() {
        return id;
    }

    public int getBudget() {
        return budget;
    }

    public boolean getIsBankrupt() {
        return isBankrupt;
    }

    public ArrayList<Contract> getContracts() {
        return contracts;
    }

    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public int getContractCost() {
        return contractCost;
    }

    public String getProducerStrategy() {
        return producerStrategy;
    }

    public void setEnergyNeededKW(int energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public void setContractCost(int contractCost) {
        this.contractCost = contractCost;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public void setProducerStrategy(String productStrategy) {
        this.producerStrategy = productStrategy;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public void setInitialBudget(final int initialBudget) {
        this.budget = initialBudget;
    }

    public void setIsBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public void setContracts(final ArrayList<Contract> contracts) {
        this.contracts = contracts;
    }
}
