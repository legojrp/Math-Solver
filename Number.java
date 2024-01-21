public class Number extends Part {
    private double value;

    public Number(String number) {
        value = Double.parseDouble(number);
    }
    public Number(double number) {
        value = number;
    }
    public double getValue() {
        return value;
    }
}
