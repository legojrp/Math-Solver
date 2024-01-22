import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean exit = false;
        System.out.println("Calculator - By John Patch");
        System.out.println("Enter 'exit' to exit");
        while (!exit){
            System.out.print("Enter an expression (or 'exit' to exit): ");
            String input = scan.nextLine();
            if (input.equals("exit")){
                exit = true;
            }
            else {
                Expression expression = new Expression(input);
                System.out.println("Calculating...");
                System.out.println(expression.evaluate().getValue());
            }
        }
        scan.close();
        





        //  String input = "(2+3) * (4+5)";
        // List<String> parsedExpression = ExpressionParser.parseExpression(input);
        // String[] array = parsedExpression.toArray(new String[0]);
        // //System.out.println(parsedExpression);
        // for (String s : array) {
        //     System.out.println(s);
        // }
    }   
}
