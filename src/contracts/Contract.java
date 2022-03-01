package contracts;

public final class Contract {
    private int consumerId;
    private int price;
    private int remainedContractMonths;

    public Contract(final int id) {
        this.consumerId = id;
        this.remainedContractMonths = 0;
        this.price = 0;
    }

    public int getConsumerId() {
        return consumerId;
    }

    public int getPrice() {
        return price;
    }

    public int getRemainedContractMonths() {
        return remainedContractMonths;
    }

    public void setConsumerId(final int consumerId) {
        this.consumerId = consumerId;
    }

    public void setPrice(final int price) {
        this.price = price;
    }

    public void setRemainedContractMonths(final int remainedContractMonths) {
        this.remainedContractMonths = remainedContractMonths;
    }

    /**
     * Subtract the number of remaining months of the contract
     * simulating the passing of the month
     */
    public void decrementMonths() {
        this.remainedContractMonths--;
    }

}
