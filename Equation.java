public class Equation {
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
