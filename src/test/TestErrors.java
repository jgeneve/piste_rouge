package test;

public class TestErrors extends Test {
	
	public static void main(String[] args){
		Test.main(args);
		verbose = true;
	// various errors
		test(verbose, "test/error.calc", "no file", "error");
		test(verbose, "test/errorEmpty.calc", "", "error");
		test(verbose, "test/errorGarbage.calc", "FOO!", "error");
		test(verbose, "test/errorGarbage2.calc", "01", "error");
		test(verbose, "test/errorUnaryExp.calc", "(+ 2)", "error");
		test(verbose, "test/errorParens.calc", "(2)", "error");
		test(verbose, "test/errorRPar.calc", "(+ 1 2", "error");
		test(verbose, "test/errorRPar2.calc", "(-1 (+ 1 2)=", "error");
		test(verbose, "test/errorRPar3.calc", "(-1 (+ 1 2=)", "error");
		test(verbose, "test/errorIf.calc", "(if1 1 0)", "error");
		test(verbose, "test/errorIf2.calc", "(if 1 1)", "error");
		test(verbose, "test/errorDivide.calc", "(/ 1 0)", "error");
		report();
	}
}
