import java.util.List;
import java.util.ArrayList;
public class Expression { // for example 2 + 2, or 56 + x, one side of an equals, to simplify, like <56 + x> = 0, which would be just 56 + x
    
    private String contains;
    private Part[] parts;
    public Expression(String input) {
        List<String> parsedExpression = ExpressionParser.parseExpression(input);
        String[] tokens = parsedExpression.toArray(new String[0]);
        contains = input;
        parts = new Part[tokens.length];
        for (int i = 0; i < tokens.length; i++) { // for each token
            if (Classify.isNumber(tokens[i])) {
                parts[i] = new Number(tokens[i]);
            } else if (Classify.isOperator(tokens[i])) {
                parts[i] = new Operator(tokens[i]);
            } else if (Classify.isVariable(tokens[i])) {
                parts[i] = new Variable(tokens[i]);
            }
            else {
                if (tokens[i].contains("(")){
                    int nestedParenthesisStart = tokens[i].indexOf("(");
                    int nestedParenthesisEnd = tokens[i].lastIndexOf(")");
                    if (nestedParenthesisStart != -1 && nestedParenthesisEnd != -1 && nestedParenthesisStart < nestedParenthesisEnd) {
                        String insideParenthesis = contains.substring(nestedParenthesisStart + 1, nestedParenthesisEnd);
                        Parenthesis parenthesis = new Parenthesis(insideParenthesis);
                        parts[i] = parenthesis.getValue();
                    }
                
                }
                else {
                    throw new IllegalArgumentException("Invalid input: " + tokens[i]);
                }
            // Check if there are nested parenthesis inside the current ones
            }
        }

        
        

    }

    public Number evaluate() {
        for  (Part part : parts){
            if (part instanceof Variable){
                return null;
            }
        }
        for (int priority = 0; priority < 3; priority++) { // this loop is priority loop 
            //Honestly I don't know how it works
            boolean operationDone = true;
            while (operationDone == true) { // makes sure that there are operations left to be done on that priority level
                for (int i = 0; i < parts.length; i++) { // this loop is for operations themselves
                    boolean isM = true;
                    if (parts[i] instanceof Operator){
                        if (parts[i+1] instanceof Operator){
                            return null;
                        }
                        
                        if (((Operator)parts[i]).getPriority() >= priority){
                            parts[i] = ((Operator)parts[i]).operate((Number)parts[i-1], (Number)parts[i+1]);
                            parts[i-1] = null;
                            parts[i+1] = null;

                        }
                    }
                    else if(i>= parts.length && parts[i+1] instanceof Number && parts[i] instanceof Number && priority >= 1) {
                        Operator operator = new Operator("*");
                        parts[i] = operator.operate((Number)parts[i], (Number)parts[i+1]);
                        parts[i+1] = null;
                    }
                    else {
                        operationDone = false;
                    }
                }
                List<Part> nonNullParts = new ArrayList<>();
                for (Part part : parts) {
                    if (part != null) {
                        nonNullParts.add(part);
                    }
                }
                parts = nonNullParts.toArray(new Part[0]);

            }
        }
        return (Number)parts[0];
    }
}
