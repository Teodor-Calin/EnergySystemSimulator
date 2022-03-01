package outputfiles;

import java.util.ArrayList;

public final class Output {
    private ArrayList<ConsumerOutput> consumers;
    private ArrayList<DistributorOutput> distributors;
    private ArrayList<ProducerOutput> energyProducers;

    public Output() {
        consumers = new ArrayList<>();
        distributors = new ArrayList<>();
        energyProducers = new ArrayList<>();
    }

    public ArrayList<ConsumerOutput> getConsumers() {
        return consumers;
    }

    public ArrayList<DistributorOutput> getDistributors() {
        return distributors;
    }

    public ArrayList<ProducerOutput> getEnergyProducers() {
        return energyProducers;
    }

    public void setConsumers(final ArrayList<ConsumerOutput> consumers) {
        this.consumers = consumers;
    }

    public void setDistributors(final ArrayList<DistributorOutput> distributors) {
        this.distributors = distributors;
    }

    public void setEnergyProducers(ArrayList<ProducerOutput> energyProducers) {
        this.energyProducers = energyProducers;
    }
}
