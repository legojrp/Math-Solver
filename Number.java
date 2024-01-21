public class Number extends Part {
    private double value;

    public Number(String number) {
        super(number);
        value = Double.parseDouble(number);
    }
    public Number(double number) {
        super(String.valueOf(number));
        value = number;
    }
    public double getValue() {
        return value;
    }

    public static boolean is(String s){
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }   
        return true;
    }
}
