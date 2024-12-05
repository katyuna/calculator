package calculator.model.calculation;

public class Calculation {
    private String calculation_id;
    private String financial_product_code;
    private SpecialTerms special_terms;

    public Calculation(String calculation_id, String financial_product_code, SpecialTerms special_terms) {
        this.calculation_id = calculation_id;
        this.financial_product_code = financial_product_code;
        this.special_terms = special_terms;
    }

    public String getCalculation_id() {
        return calculation_id;
    }

    public void setCalculation_id(String calculation_id) {
        this.calculation_id = calculation_id;
    }

    public String getFinancial_product_code() {
        return financial_product_code;
    }

    public void setFinancial_product_code(String financial_product_code) {
        this.financial_product_code = financial_product_code;
    }

    public SpecialTerms getSpecial_terms() {
        return special_terms;
    }

    public void setSpecial_terms(SpecialTerms special_terms) {
        this.special_terms = special_terms;
    }
}
