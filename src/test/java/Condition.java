public class Condition {

    private String attribute;
    private String operator;
    private String value;

    public String toString(){
        return attribute + " " + operator + " " + value;
    }

    public Condition(String att, String op, String val){
        attribute = att;
        operator = op;
        value = val;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
