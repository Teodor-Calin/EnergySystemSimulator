package inputfiles;

import java.util.ArrayList;

public final class UpdateInput {
    private ArrayList<ConsumerInput> newConsumers;
    private ArrayList<DistributorChanges> distributorChanges;
    private ArrayList<ProducerChanges> producerChanges;

    public ArrayList<ConsumerInput> getNewConsumers() {
        return newConsumers;
    }

    public void setNewConsumers(final ArrayList<ConsumerInput> newConsumers) {
        this.newConsumers = newConsumers;
    }

    public ArrayList<DistributorChanges> getDistributorChanges() {
        return distributorChanges;
    }

    public ArrayList<ProducerChanges> getProducerChanges() {
        return producerChanges;
    }

    public void setDistributorChanges(ArrayList<DistributorChanges> distributorChanges) {
        this.distributorChanges = distributorChanges;
    }

    public void setProducerChanges(ArrayList<ProducerChanges> producerChanges) {
        this.producerChanges = producerChanges;
    }
}
