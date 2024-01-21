import java.util.ArrayList;
import java.util.List;

public class ExpressionParser { // this was written by AI, so it works but i don't know how it works
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
        } else if (c == '+' || c == '-' || c == '*' || c == '/') {
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