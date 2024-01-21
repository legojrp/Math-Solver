import java.util.List;

public class Main {
    public static void main(String[] args) {
        Expression expression = new Expression("45.4");
        System.out.println(expression.evaluate().getValue());





        //  String input = "(2+3) * (4+5)";
        // List<String> parsedExpression = ExpressionParser.parseExpression(input);
        // String[] array = parsedExpression.toArray(new String[0]);
        // //System.out.println(parsedExpression);
        // for (String s : array) {
        //     System.out.println(s);
        // }
    }   
}
