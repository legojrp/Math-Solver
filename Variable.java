class Variable extends Part {
    private String name;

    public Variable(String name) {
        super(name);
        this.name = name;
    }

    public static boolean is(String s){
        if (s.length() == 1 && s.charAt(0) >= 'a' && s.charAt(0) <= 'z') {
            return true;
        }
        return false;
    }

}