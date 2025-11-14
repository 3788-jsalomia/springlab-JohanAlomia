package edu.espe.springlab.dto;

public class StatsResponse {
    private long total;
    private long active;
    private long inactive;

    public StatsResponse(long total, long active, long inactive) {
        this.total = total;
        this.active = active;
        this.inactive = inactive;
    }

    public long getTotal() {
        return total;
    }

    public long getActive() {
        return active;
    }

    public long getInactive() {
        return inactive;
    }
}
