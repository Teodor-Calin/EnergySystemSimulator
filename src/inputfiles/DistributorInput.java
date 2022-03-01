package inputfiles;

public final class DistributorInput {
    private int id;
    private int contractLength;
    private int initialBudget;
    private int initialInfrastructureCost;
    private int energyNeededKW;
    private String producerStrategy;

    public int getId() {
        return id;
    }

    public int getContractLength() {
        return contractLength;
    }

    public int getInitialBudget() {
        return initialBudget;
    }

    public int getInitialInfrastructureCost() {
        return initialInfrastructureCost;
    }

    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public String getProducerStrategy() {
        return producerStrategy;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public void setContractLength(final int contractLength) {
        this.contractLength = contractLength;
    }

    public void setInitialBudget(final int initialBudget) {
        this.initialBudget = initialBudget;
    }

    public void setInitialInfrastructureCost(final int initialInfrastructureCost) {
        this.initialInfrastructureCost = initialInfrastructureCost;
    }

    public void setEnergyNeededKW(int energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public void setProducerStrategy(String producerStrategy) {
        this.producerStrategy = producerStrategy;
    }
}
