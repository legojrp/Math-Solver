public class Parenthesis extends Part{// handles parenthesis 
    private Number value;
    public Parenthesis(String input) {
        
        // Trims off the start and end parenthesis from the input string
        if (input.startsWith("(")) {
            input = input.substring(1);
        }
        if (input.endsWith(")")) {
            input = input.substring(0, input.length() - 1);
        }
        Expression expression = new Expression(input);
        value = expression.evaluate();
        
        
    }
    public Number getValue(){
        return value;
    }

}
