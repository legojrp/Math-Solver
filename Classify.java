public class Classify {
    public static boolean isVariable(String s){
        if (s.length() == 1 && s.charAt(0) >= 'a' && s.charAt(0) <= 'z') {
            return true;
        }
        return false;
    }
    public static boolean isOperator(String s){ // at the current only handles +,-,*,/
        if (s.length() == 1 && (s.charAt(0) == '+' || s.charAt(0) == '-' || s.charAt(0) == '*' || s.charAt(0) == '/')) {
            return true;
        }
        return false;
    }
    public static boolean isNumber(String s){
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }   
        return true;
    }
    public static boolean isExpression(String s){
        String[] components = s.split(" ");
        int count = 0;

        for (String component : components) {
            if (isNumber(component)) {
                count++;
            } else if (isOperator(component)) {
                count++;
            } else if (isVariable(component)) {
                count++;
            } else {
                return false;  // Found an unknown component
            }
        }

        return count >= 2;
    }

    public static boolean isEquation(String s){
        String[] components = s.split(" ");
        int count = 0;
        
        for (String component : components) {
            if (isNumber(component)) {
                count++;
            } else if (isOperator(component)) {
                count++;
            } else if (isVariable(component)) {
                count++;
            }
            else if (component.equals("=")){
                count++;
            }
             else {
                return false;  // Found an unknown component
            }
            
        }
        return count >= 2;
    }
}
