package my.edu.tarc.ass2.Billing;

public class BillHistory {
    private String payment;
    private Double amount;


    public BillHistory(String payment, Double amount){
        this.payment = payment;
        this.amount = amount;
    }

    public String getPayment(){
        return payment;
    }

    public Double getAmount(){
        return amount;
    }

}
