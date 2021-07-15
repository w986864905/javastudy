package test;

public class Super {
    protected int number;
     
    protected void showNumber() {
        System.out.println("number = " + number);
    }
    public static int i = 3;
    static {
        i = 4;
    }
}

class Sub extends Super {
    static int num;
    void bar() {
        super.number = 10;
        super.showNumber();
    }
}