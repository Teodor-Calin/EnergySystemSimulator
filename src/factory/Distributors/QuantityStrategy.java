package factory.Distributors;

import factory.Producers.Producer;
import java.util.ArrayList;

public final class QuantityStrategy implements Strategy {

    private Distributor distributor;

    public void setDistributor(Distributor distributor) {
        this.distributor = distributor;
    }

    @Override
    public void findProducers(ArrayList<Producer> producerList) {
        int totalEnergy = 0;
        int maxQuantity = 0;
        Producer bestProducer = null;

        while (totalEnergy < distributor.getEnergyNeededKW()) {
            maxQuantity = 0;
            bestProducer = null;

            for (Producer i : producerList) {
                if (!distributor.getProducers().contains(i)
                        && i.getClients().size() < i.getMaxDistributors()) {
                    if (maxQuantity != 0) {
                        if (i.getEnergyPerDistributor() > maxQuantity) {
                            bestProducer = i;
                            maxQuantity = bestProducer.getEnergyPerDistributor();
                        } else if (i.getEnergyPerDistributor() == maxQuantity) {
                            bestProducer = i;
                            maxQuantity = bestProducer.getEnergyPerDistributor();
                        }
                    } else {
                        maxQuantity = i.getEnergyPerDistributor();
                        bestProducer = i;
                    }
                }
            }
            distributor.getProducers().add(bestProducer);
            totalEnergy += bestProducer.getEnergyPerDistributor();
            bestProducer.getClients().add(distributor);
            bestProducer.getObservers().add(distributor);
        }
    }
}
