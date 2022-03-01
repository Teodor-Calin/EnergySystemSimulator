package inputfiles;

import java.util.ArrayList;

public final class InitialData {
    private ArrayList<ConsumerInput> consumers;
    private ArrayList<DistributorInput> distributors;
    private ArrayList<ProducerInput> producers;

    public ArrayList<ConsumerInput> getConsumers() {
        return consumers;
    }

    public ArrayList<DistributorInput> getDistributors() {
        return distributors;
    }

    public void setConsumers(final ArrayList<ConsumerInput> consumers) {
        this.consumers = consumers;
    }

    public void setDistributors(final ArrayList<DistributorInput> distributors) {
        this.distributors = distributors;
    }

    public ArrayList<ProducerInput> getProducers() {
        return producers;
    }

    public void setProducers(ArrayList<ProducerInput> producers) {
        this.producers = producers;
    }
}
