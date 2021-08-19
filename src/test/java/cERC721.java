public class cERC721 {
    String from;
    String to;
    int ID;
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public cERC721(String f, String t , int i, double A){
        from=f;
        to=t;
        Amount=A;
        ID=i;
    }
    @Override
    public String toString() {
        return "From: " +from  + " To: "+ to +" ID: "+ID+ " Amount: "+Amount;
    }
}
