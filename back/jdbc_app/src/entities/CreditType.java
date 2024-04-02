package entities;

import java.util.UUID;

public class CreditType {
    private UUID id;
    private String name;
    private Double creditAmount;
    private Double interestRate;
    private int termInMonths;

    public CreditType() {}

    public CreditType(UUID id, String name, Double creditAmount, Double interestRate, int termInMonths) {
        this.id = id;
        this.name = name;
        this.creditAmount = creditAmount;
        this.interestRate = interestRate;
        this.termInMonths = termInMonths;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public int getTermInMonths() {
        return termInMonths;
    }

    public void setTermInMonths(int termInMonths) {
        this.termInMonths = termInMonths;
    }

    @Override
    public String toString() {
        return STR."\{id.toString()}, \{name}, \{creditAmount.toString()}, \{interestRate.toString()}, \{termInMonths}";
    }
}
