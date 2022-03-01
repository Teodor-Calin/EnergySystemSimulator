package factory.Distributors;

import factory.Producers.Producer;

import java.util.ArrayList;

public interface Strategy {

    /**
     * sets the Distributor object that "owns" the strategy
     * @param distributor
     */
    void setDistributor(Distributor distributor);

    /**
     * simulates the searching for producers of a distributor, depending on the
     * strategy of the distributor
     * @param producerList
     */
    void findProducers(ArrayList<Producer> producerList);
}
