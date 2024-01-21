public class Operator extends Part{ // add symbol, subtract symbol, multiply symbol, divide symbol, exponent symbol,and much more
    // super value - uses this a lot as symbol!
    private int priority;

    public Operator(String symbol, int priority){ 
        super(symbol);
        this.priority = priority;
    }
    public Operator(String symbol){
        super(symbol);
        if (symbol.equals("+")){
            priority = 2;
        }
        else if (symbol.equals("-")){
            priority = 2;
        }
        else if (symbol.equals("*")){
            priority = 1;
        }
        else if (symbol.equals("/")){
            priority = 1;
        }
        else if (symbol.equals("^")){
            priority = 0;
        }
    }

    public Number operate(Number num1, Number num2){
        if (num1 == null){
            num1 = new Number(0.0);
        }
        if (num2 == null){
            num2 = new Number(0.0);
        }
        if (super.getContains().equals("+")){
            return new Number(num1.getValue() + num2.getValue());
        }
        else if (super.getContains().equals("-")){
            return new Number(num1.getValue() - num2.getValue());
        }
        else if (super.getContains().equals("*")){
            return new Number(num1.getValue() * num2.getValue());
        }
        else if (super.getContains().equals("/")){
            return new Number(num1.getValue() / num2.getValue());
        }
        else if (super.getContains().equals("^")){
            return new Number(Math.pow(num1.getValue(), num2.getValue()));
        }
        return null;
    }
    public int getPriority(){
        return priority;
    }

    public static boolean is(String s){ // at the current only handles +,-,*,/
        if (s.length() == 1 && (s.charAt(0) == '+' || s.charAt(0) == '-' || s.charAt(0) == '*' || s.charAt(0) == '/')) {
            return true;
        }
        return false;
    }
}
