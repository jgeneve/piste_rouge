package test;

public class TestRedNoLoop extends Test {
    public static void main(String[] args){
        Test.main(args);
        verbose = true;
        test(verbose, "test/redNoLoop.calc", "lazy conditional", "2");
        report();
    }
}
