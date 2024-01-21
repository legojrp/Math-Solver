import java.util.List;
import java.util.ArrayList;
public class Expression { // for example 2 + 2, or 56 + x, one side of an equals, to simplify, like <56 + x> = 0, which would be just 56 + x
    
    private String contains;
    private Part[] parts;
    public Expression(String input) {
        List<String> parsedExpression = parseExpression(input);
        String[] tokens = parsedExpression.toArray(new String[0]);
        contains = input;
        parts = new Part[tokens.length];
        for (int i = 0; i < tokens.length; i++) { // for each token
            if (Number.is(tokens[i])) {
                parts[i] = new Number(tokens[i]);
            } else if (Operator.is(tokens[i])) {
                parts[i] = new Operator(tokens[i]);
            } else if (Variable.is(tokens[i])) {
                parts[i] = new Variable(tokens[i]);
            }
            else {
                if (tokens[i].contains("(")){
                    int nestedParenthesisStart = tokens[i].indexOf("(");
                    int nestedParenthesisEnd = tokens[i].lastIndexOf(")");
                    if (nestedParenthesisStart != -1 && nestedParenthesisEnd != -1 && nestedParenthesisStart < nestedParenthesisEnd) {
                        String insideParenthesis = tokens[i].substring(nestedParenthesisStart + 1, nestedParenthesisEnd);
                        Parenthesis parenthesis = new Parenthesis(insideParenthesis);
                        parts[i] = parenthesis.getValue();
                    }
                
                }
                else {
                    throw new IllegalArgumentException("Invalid input: " + tokens[i]);
                }
            }
        }

        
        

    }

    public Number evaluate() {
        for  (Part part : parts){
            if (part instanceof Variable){
                return null;
            }
        }
        for (int priority = 0; priority < 3; priority++) { // this loop is priority loop, priority is pemdas order in reverse eg 0 is exp, 1 is mult, 2 is add
            // for (Part part : parts){
            //     System.out.println(part.getContains() + " " + this);
            // }
            boolean operationDone = true; // this works somehow? to make sure no infinite loops
            while (operationDone) { // makes sure that there are operations left to be done on that priority level
                operationLoop:
                for (int i = 0; i < parts.length; i++) { // this loop is for operations themselves

                    for (Part part : parts){
                        if (part == null){
                            break operationLoop;
                        }
                    }
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
                parts = Expression.removeNull(parts);
                
   

            }
        }
        return (Number)parts[0];
    }

    public static Part[] removeNull(Part[] parts){
        List<Part> nonNullParts = new ArrayList<>();
        for (Part part : parts) {
            if (part != null) {
                nonNullParts.add(part);
            }
        }
        parts = nonNullParts.toArray(new Part[0]);
        return parts;
    } 

    public static boolean is(String s){
        String[] components = s.split(" ");
        int count = 0;

        for (String component : components) {
            if (Number.is(component)) {
                count++;
            } else if (Operator.is(component)) {
                count++;
            } else if (Variable.is(component)) {
                count++;
            } else {
                return false;  // Found an unknown component
            }
        }

        return count >= 2;
    }

    public static List<String> parseExpression(String input) {
        List<String> tokens = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();
        boolean inParentheses = false;
    
        for (char c : input.toCharArray()) {
            if (c == '(') {
                if (currentToken.length() > 0) {
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0);
                }
                currentToken.append(c);  // Preserve the opening parenthesis
                inParentheses = true;
            } else if (c == ')') {
                inParentheses = false;
                currentToken.append(c);
                tokens.add(currentToken.toString());
                currentToken.setLength(0);
            } else if (inParentheses) {
                currentToken.append(c);
            } else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
                if (currentToken.length() > 0) {
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0);
                }
                tokens.add(Character.toString(c));
            } else if (Character.isDigit(c)) {
                currentToken.append(c);
            } else if (c != ' ') {
                currentToken.append(c);
            }
        }
    
        if (currentToken.length() > 0) {
            tokens.add(currentToken.toString());
        }
    
        return tokens;
    }
}