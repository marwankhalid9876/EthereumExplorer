public class cERC20 {
    String from;
    String to;
    double Amount;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public cERC20(String f, String t , double A){
        from=f;
        to=t;
        Amount=A;
    }
    @Override
    public String toString() {
        return " From: " +from  + " To: "+ to + " Amount: "+Amount;
    }
}
