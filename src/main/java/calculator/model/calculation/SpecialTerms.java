package calculator.model.calculation;

public class SpecialTerms {
    private Double rate;
    private Double commission;
    private int days_count;
    private Double penalty_percent;
    private int penalty_count;

    public Double getRate() {
        return rate;
    }

    public SpecialTerms(Double rate, Double commission, int days_count, Double penalty_percent, int penalty_count) {
        this.rate = rate;
        this.commission = commission;
        this.days_count = days_count;
        this.penalty_percent = penalty_percent;
        this.penalty_count = penalty_count;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public int getDays_count() {
        return days_count;
    }

    public void setDays_count(int days_count) {
        this.days_count = days_count;
    }

    public Double getPenalty_percent() {
        return penalty_percent;
    }

    public void setPenalty_percent(Double penalty_percent) {
        this.penalty_percent = penalty_percent;
    }

    public int getPenalty_count() {
        return penalty_count;
    }

    public void setPenalty_count(int penalty_count) {
        this.penalty_count = penalty_count;
    }
}
