package calculator.model.payment;

public class Payment {
        public String id;
        public String calculation_id;
        public String created_at;
        public String type;
        public int amountExp2;

    public Payment(String id, String calculation_id, String created_at, String type, int amountExp2) {
        this.id = id;
        this.calculation_id = calculation_id;
        this.created_at = created_at;
        this.type = type;
        this.amountExp2 = amountExp2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCalculation_id() {
        return calculation_id;
    }

    public void setCalculation_id(String calculation_id) {
        this.calculation_id = calculation_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmountExp2() {
        return amountExp2;
    }

    public void setAmountExp2(int amountExp2) {
        this.amountExp2 = amountExp2;
    }
}
