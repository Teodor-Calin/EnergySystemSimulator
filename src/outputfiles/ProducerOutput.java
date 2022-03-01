package outputfiles;

import factory.Producers.MonthStats;
import factory.Producers.Producer;

import java.util.ArrayList;

public final class ProducerOutput {
    private int id;
    private int maxDistributors;
    private double priceKW;
    private String energyType;
    private int energyPerDistributor;
    private ArrayList<MonthStats> monthlyStats;

    public ProducerOutput(final Producer p) {
        this.id = p.getId();
        this.maxDistributors = p.getMaxDistributors();
        this.priceKW = p.getPriceKW();
        this.energyType = p.getEnergyType().getLabel();
        this.energyPerDistributor = p.getEnergyPerDistributor();
        this.monthlyStats = p.getMonthlyStats();
    }

    public int getId() {
        return id;
    }

    public int getMaxDistributors() {
        return maxDistributors;
    }

    public double getPriceKW() {
        return priceKW;
    }

    public String getEnergyType() {
        return energyType;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public ArrayList<MonthStats> getMonthlyStats() {
        return monthlyStats;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMaxDistributors(int maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    public void setPriceKW(double priceKW) {
        this.priceKW = priceKW;
    }

    public void setEnergyType(String energyType) {
        this.energyType = energyType;
    }

    public void setEnergyPerDistributor(int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public void setMonthlyStats(ArrayList<MonthStats> monthlyStats) {
        this.monthlyStats = monthlyStats;
    }
}
