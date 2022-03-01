package factory.Producers;

import entities.EnergyType;
import factory.BusinessEntity;
import factory.Distributors.Distributor;
import inputfiles.ProducerInput;

import java.util.ArrayList;

import static java.util.Collections.sort;

public final class Producer extends Observable implements BusinessEntity {
    private int id;
    private EnergyType energyType;
    private int maxDistributors;
    private double priceKW;
    private int energyPerDistributor;
    private ArrayList<Distributor> clients;
    private ArrayList<MonthStats> monthlyStats;

    public Producer(ProducerInput input) {
        super();
        this.id = input.getId();
        this.energyType = stringToEnergyType(input.getEnergyType());
        this.maxDistributors = input.getMaxDistributors();
        this.priceKW = input.getPriceKW();
        this.energyPerDistributor = input.getEnergyPerDistributor();
        this.clients = new ArrayList<>();
        this.monthlyStats = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public EnergyType getEnergyType() {
        return energyType;
    }

    public int getMaxDistributors() {
        return maxDistributors;
    }

    public double getPriceKW() {
        return priceKW;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public ArrayList<Distributor> getClients() {
        return clients;
    }

    public ArrayList<MonthStats> getMonthlyStats() {
        return monthlyStats;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public void setEnergyType(final EnergyType energyType) {
        this.energyType = energyType;
    }

    public void setMaxDistributors(final int maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    public void setPriceKW(final double priceKW) {
        this.priceKW = priceKW;
    }

    public void setEnergyPerDistributor(final int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public void setMonthlyStats(final ArrayList<MonthStats> monthlyStats) {
        this.monthlyStats = monthlyStats;
    }

    /**
     *
     * @param word represents the name of one of the energy type possible
     * @return the EnergyType object with the name given
     */
    public static EnergyType stringToEnergyType(final String word) {
        switch (word) {
            case "WIND": return EnergyType.WIND;
            case "SOLAR": return EnergyType.SOLAR;
            case "HYDRO": return EnergyType.HYDRO;
            case "COAL": return EnergyType.COAL;
            case "NUCLEAR": return EnergyType.NUCLEAR;
            default: return null;
        }
    }

    /**
     * Creates a MonthStats object for the given producer in the respective month
     */
    public void makeMonthlyStats() {
        MonthStats stats = new MonthStats(this.monthlyStats.size() + 1);
        for (Distributor i : this.getClients()) {
            stats.getDistributorsIds().add(i.getId());
        }
        sort(stats.getDistributorsIds());
        this.monthlyStats.add(stats);

    }

    /**
     * Makes changes given for the given producer and notifies its observers (distributors)
     * @param newEnergy
     */
    public void makeChanges(int newEnergy) {
        this.setEnergyPerDistributor(newEnergy);
        notifyObservers();
    }

    @Override
    public void payDebts() {
        return;
    }
}
