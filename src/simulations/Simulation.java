package simulations;

import factory.BusinessEntityFactory;
import factory.Consumers.Consumer;
import factory.Distributors.Distributor;
import factory.Producers.Producer;
import inputfiles.ConsumerInput;
import inputfiles.DistributorChanges;
import inputfiles.DistributorInput;
import inputfiles.Input;
import inputfiles.ProducerChanges;
import inputfiles.ProducerInput;
import inputfiles.UpdateInput;
import outputfiles.ConsumerOutput;
import outputfiles.DistributorOutput;
import outputfiles.Output;
import outputfiles.ProducerOutput;

import java.util.ArrayList;

public final class Simulation {
    private static Simulation instance = null;
    private final int turns;
    private final ArrayList<Distributor> distributorList;
    private final ArrayList<Consumer> consumerList;
    private final ArrayList<Producer> producerList;
    private final ArrayList<UpdateInput> monthlyUpdates;
    private final BusinessEntityFactory factory;

    private Simulation(final Input input) {
        factory = BusinessEntityFactory.getInstance();

        consumerList = new ArrayList<>();
        for (ConsumerInput i : input.getInitialData().getConsumers()) {
            consumerList.add((Consumer) factory.createEntity(i));
        }
        distributorList = new ArrayList<>();
        for (DistributorInput i : input.getInitialData().getDistributors()) {
            distributorList.add((Distributor) factory.createEntity(i));
        }
        producerList = new ArrayList<>();
        for (ProducerInput i : input.getInitialData().getProducers()) {
            producerList.add((Producer) factory.createEntity(i));
        }
        turns = input.getNumberOfTurns();
        monthlyUpdates = new ArrayList<>(input.getMonthlyUpdates());
    }

    /**
     *
     * @param input
     * @return the Simulation object itself, in case it exists,
     * or creates and returns it
     */
    public static Simulation getInstance(final Input input) {
        if (instance == null) {
            instance = new Simulation(input);
        }
        return instance;
    }


    /**
     * This method finds the distributor with the best price
     * and makes contracts for the consumers that don't have a contract
     * @param distributorList - list of all distributors
     * @param consumerList - list of all consumers
     */
    public static void makeNewContracts(final ArrayList<Distributor> distributorList,
                                        final ArrayList<Consumer> consumerList) {

        // the best price and the distributor with the best price are found and stored
        Distributor bestDistributor = null;
        int min = -1;
        for (Distributor i : distributorList) {
            if (!i.getIsBankrupt()) {
                if (min != -1) {
                    if (i.getPrice() < min) {
                        bestDistributor = i;
                        min = bestDistributor.getPrice();
                    }
                } else {
                    min = i.getPrice();
                    bestDistributor = i;
                }
            }
        }

        // all consumers without contract make a contract with the best distributor
        for (Consumer i : consumerList) {
            i.setUpContract(bestDistributor);
        }
    }

    /**
     * simulates all the processes done in a month
     * @param distributorList - list of all distributors
     * @param consumerList - list of all consumers
     */
    private static void runMonth(final ArrayList<Distributor> distributorList,
                                 final ArrayList<Consumer> consumerList,
                                 final ArrayList<Producer> producerList) {

        // all distributors calculate their first prices
        // and update their contracts list
        for (Distributor i : distributorList) {
            i.getThisMonthPrice();
            i.deleteFinishedContracts();
        }

        // all consumers get their first salary
        for (Consumer i : consumerList) {
            i.addIncomeToBudget();
        }

        // all consumers make contracts
        makeNewContracts(distributorList, consumerList);

        // all consumers pay their distributors
        for (Consumer i : consumerList) {
            i.payDebts();
        }

        // all distributors pay their debts and update
        // thier contracts lists
        for (Distributor i : distributorList) {
            i.payDebts();
            i.deleteBankrupts(consumerList);
            i.checkContracts();
        }
    }


    /**
     * The start of the simulation, once all data needed is stored
     * @return the Output variable
     */
    public Output start() {


        for (Distributor i : distributorList) {
            i.findProducers(producerList);
        }
        // contract signings, payments etc. in the first month
        runMonth(distributorList, consumerList, producerList);

        for (int n = 0; n < turns; n++) {

            // all distributors calculate their first prices
            // and update their contracts list
            for (ConsumerInput i : monthlyUpdates.get(n).getNewConsumers()) {
                consumerList.add((Consumer) factory.createEntity(i));
            }
            for (DistributorChanges i : monthlyUpdates.get(n).getDistributorChanges()) {
                distributorList.get(i.getId()).setInfrastructureCost(i.getInfrastructureCost());
            }

            // contract signings, payments etc. in the current month
            runMonth(distributorList, consumerList, producerList);

            // producer changes
            for (ProducerChanges i : monthlyUpdates.get(n).getProducerChanges()) {
                producerList.get(i.getId()).makeChanges(i.getEnergyPerDistributor());
            }

            // distributors find other producers after changes
            for (Distributor i : distributorList) {
                i.findProducers(producerList);
            }

            // producers keep the Ids of their clients this month
            for (Producer i : producerList) {
                i.makeMonthlyStats();
            }
        }


        // the data needed for output is stored in an
        // Output variable
        Output output = new Output();
        for (Consumer i : consumerList) {
            output.getConsumers().add(new ConsumerOutput(i));
        }
        for (Distributor i : distributorList) {
            output.getDistributors().add(new DistributorOutput(i));
        }
        for (Producer i : producerList) {
            output.getEnergyProducers().add(new ProducerOutput(i));
        }

        instance = null;
        return output;
    }
}
