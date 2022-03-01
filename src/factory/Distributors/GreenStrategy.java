package factory.Distributors;

import factory.Producers.Producer;
import java.util.ArrayList;

public final class GreenStrategy implements Strategy {

    private Distributor distributor;

    public void setDistributor(Distributor distributor) {
        this.distributor = distributor;
    }

    @Override
    public void findProducers(ArrayList<Producer> producerList) {

        int totalEnergy = 0;
        double min;
        Producer bestProducer;

        while (totalEnergy < distributor.getEnergyNeededKW()) {
            min = 0;
            bestProducer = null;

            for (Producer i : producerList) {
                if (!distributor.getProducers().contains(i)
                        && i.getClients().size() != i.getMaxDistributors()
                        && i.getEnergyType().isRenewable()) {
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

            if (min == 0) {
                break;
            }
            distributor.getProducers().add(bestProducer);
            totalEnergy += bestProducer.getEnergyPerDistributor();
            bestProducer.getClients().add(distributor);
            bestProducer.getObservers().add(distributor);
        }

        while (totalEnergy < distributor.getEnergyNeededKW()) {
            min = 0;
            bestProducer = null;

            for (Producer i : producerList) {
                if (!distributor.getProducers().contains(i)
                        && i.getClients().size() != i.getMaxDistributors()
                        && !i.getEnergyType().isRenewable()) {
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
