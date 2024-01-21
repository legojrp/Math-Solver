public class Operator extends Part{ // add symbol, subtract symbol, multiply symbol, divide symbol, exponent symbol,and much more
    private String symbol;
    private int priority;

    public Operator(String symbol, int priority){ 
        this.symbol = symbol;
        this.priority = priority;
    }
    public Operator(String symbol){
        this.symbol = symbol;
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
        if (symbol.equals("+")){
            return new Number(num1.getValue() + num2.getValue());
        }
        else if (symbol.equals("-")){
            return new Number(num1.getValue() - num2.getValue());
        }
        else if (symbol.equals("*")){
            return new Number(num1.getValue() * num2.getValue());
        }
        else if (symbol.equals("/")){
            return new Number(num1.getValue() / num2.getValue());
        }
        else if (symbol.equals("^")){
            return new Number(Math.pow(num1.getValue(), num2.getValue()));
        }
        return null;
    }
    public int getPriority(){
        return priority;
    }
}
