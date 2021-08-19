public class cERC20 {
    String from;
    String to;
    double Amount;
    public cERC20(String f,String t ,double A){
        from=f;
        to=t;
        Amount=A;
    }
    @Override
    public String toString() {
        return " From: " +from  + " To: "+ to + " Amount: "+Amount;
    }
}
