public class cERC721 {
    String from;
    String to;
    int ID;
    double Amount;
    public cERC721(String f,String t ,int i,double A){
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
