package factory.Distributors;

import factory.Producers.Producer;
import java.util.ArrayList;

public final class PriceStrategy implements Strategy {

    private Distributor distributor;

    public void setDistributor(Distributor distributor) {
        this.distributor = distributor;
    }

    @Override
    public void findProducers(ArrayList<Producer> producerList) {

        int totalEnergy = 0;
        double min = 0;
        Producer bestProducer = null;

        while (totalEnergy < distributor.getEnergyNeededKW()) {
            min = 0;
            bestProducer = null;

            for (Producer i : producerList) {
                if (!distributor.getProducers().contains(i)
                        && i.getClients().size() != i.getMaxDistributors()) {
                    if (min != 0) {
                        if (i.getPriceKW() < min) {
                            bestProducer = i;
                            min = bestProducer.getPriceKW();
                        } else if (i.getPriceKW() == min
                                && i.getEnergyPerDistributor()
                                                > bestProducer.getEnergyPerDistributor()) {
                            bestProducer = i;
                            min = bestProducer.getPriceKW();
                        }
                    } else {
                        min = i.getPriceKW();
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
