package factory;

import factory.Consumers.Consumer;
import factory.Distributors.Distributor;
import factory.Distributors.GreenStrategy;
import factory.Distributors.PriceStrategy;
import factory.Distributors.QuantityStrategy;
import factory.Producers.Producer;
import inputfiles.ConsumerInput;
import inputfiles.DistributorInput;
import inputfiles.ProducerInput;


public final class BusinessEntityFactory {

    private static BusinessEntityFactory instance = null;

    private BusinessEntityFactory() { }

    /**
     * @return the factory itself if it exists,
     * or creates one and returns it
     */
    public static BusinessEntityFactory getInstance() {
        if (instance == null) {
            instance = new BusinessEntityFactory();
        }
        return instance;
    }



    /**
     * @param object DistributorInput or ConsumerInput object
     * @return Distributor or Consumer object
     */
    public BusinessEntity createEntity(final Object object) {
        if (object == null) {
            return null;
        }

        if (object instanceof DistributorInput) {
            switch (((DistributorInput) object).getProducerStrategy()) {
                case "GREEN": return new Distributor((DistributorInput) object,
                                                        new GreenStrategy());
                case "PRICE": return new Distributor((DistributorInput) object,
                                                        new PriceStrategy());
                case "QUANTITY": return new Distributor((DistributorInput) object,
                                                        new QuantityStrategy());
            }
        } else if (object instanceof ConsumerInput) {
            return new Consumer((ConsumerInput) object);
        } else if (object instanceof ProducerInput) {
            return new Producer((ProducerInput) object);
        }

        return null;
    }
}
