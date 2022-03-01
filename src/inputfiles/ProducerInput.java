package inputfiles;

public final class ProducerInput {
    private int id;
    private String energyType;
    private int maxDistributors;
    private double priceKW;
    private int energyPerDistributor;

    public int getId() {
        return id;
    }

    public String getEnergyType() {
        return energyType;
    }

    public int getMaxDistributors() {
        return maxDistributors;
    }

    public double getPriceKW() {
        return priceKW;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEnergyType(String energyType) {
        this.energyType = energyType;
    }

    public void setMaxDistributors(int maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    public void setPriceKW(double priceKW) {
        this.priceKW = priceKW;
    }

    public void setEnergyPerDistributor(int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }


}
