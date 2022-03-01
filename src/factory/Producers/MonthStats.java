package factory.Producers;

import java.util.ArrayList;

public final class MonthStats {
    private int month;
    private ArrayList<Integer> distributorsIds;

    public MonthStats(int month) {
        this.month = month;
        this.distributorsIds = new ArrayList<>();
    }

    public int getMonth() {
        return month;
    }

    public ArrayList<Integer> getDistributorsIds() {
        return distributorsIds;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDistributorsIds(ArrayList<Integer> distributorsIds) {
        this.distributorsIds = distributorsIds;
    }
}
