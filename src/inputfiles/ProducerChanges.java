package inputfiles;

public final class ProducerChanges {
    private int id;
    private int energyPerDistributor;

    public void setId(int id) {
        this.id = id;
    }

    public void setEnergyPerDistributor(int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public int getId() {
        return id;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }
}
