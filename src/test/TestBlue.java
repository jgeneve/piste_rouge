package test;

public class TestBlue extends Test {
	public static void main(String[] args){
		Test.main(args);
		verbose = true;
		test(verbose, "test/blueDef.calc", "(= a 11) a", "11");
		test(verbose, "test/blueDefError.calc", "(= A 11) A", "error");
		test(verbose, "test/blueDef2.calc", "(= a 1) (= b 2) (+ a b)", "3");
        test(verbose, "test/blueDef3.calc", "(= a 1) (= b (- a 1)) (+ a b)", "1");
		test(verbose, "test/blueRedef.calc", "(= a 11) (= a 12)", "error");
        test(verbose, "test/blueUndef.calc", "a", "error");
		report();
	}
}
