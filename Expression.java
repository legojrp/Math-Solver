public class Expression { // for example 2 + 2, or 56 + x, one side of an equals, to simplify, like <56 + x> = 0, which would be just 56 + x
    
    String contains;
    Part[] parts;
    public Expression(String input) {
        input = input.replaceAll("(\\d)(\\+|-|\\*|/)(\\d)", "$1 $2 $3");
            
        contains = input;
        // Assuming the parts field is supposed to be an array of Part objects representing parts of the expression
        String[] tokens = contains.split(" ");
        parts = new Part[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            if (Classify.isNumber(tokens[i])) {
                parts[i] = new Number(tokens[i]);
            } else if (Classify.isOperator(tokens[i])) {
                parts[i] = new Operator(tokens[i]);
            } else if (Classify.isVariable(tokens[i])) {
                parts[i] = new Variable(tokens[i]);
            }
        }

    }

    public Number evaluate() {
        
    }
}
