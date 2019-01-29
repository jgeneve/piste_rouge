package test;

public class TestGreen extends Test {
	
	public static void main(String[] args){
		Test.main(args);
		// Calc Green	
		verbose = true;
		test(verbose, "test/greenZeroUnix.calc", "0", "0");
		test(verbose, "test/greenZeroDos.calc", "0", "0");
		test(verbose, "test/greenZeroTab.calc", "0", "0");
		test(verbose, "test/greenOne.calc", "1", "1");
		test(verbose, "test/greenLit.calc", "123", "123");
		test(verbose, "test/greenUnExpMinus.calc", "-1", "-1");
		test(verbose, "test/greenUnExpMinus2.calc", "(- (- 1 0))", "-1");
		test(verbose, "test/greenBinExpPlus.calc", "(+ 1 1)", "2"); 
		test(verbose, "test/greenBinExpPlus01.calc", "(+ 01)", "1");
		test(verbose, "test/greenBinExpMinus.calc", "(- 1 1)", "0");			
		test(verbose, "test/greenBinExpDivide.calc", "(/ 5 2)", "2");
		test(verbose, "test/greenBinExpEqual.calc", "(== 1 2)", "0");
		test(verbose, "test/greenBinExpEqual2.calc", "(== 3 3)", "1");
		test(verbose, "test/greenNestedExp.calc", "(+ (- 2 3) (+ 0 1))", "0");
		test(verbose, "test/greenIf0.calc", "(if 0 1 0)", "0");
		test(verbose, "test/greenIf0Indent.calc", "(if 0 1 0)", "0");
		test(verbose, "test/greenIf1.calc", "(if 1 1 0)", "1"); 
		test(verbose, "test/greenIf2.calc", "(if 2 1 0)", "1"); 
		test(verbose, "test/greenNestedIfInTest.calc", "(if (if 1 1 0) 2 3)", "2"); 
		test(verbose, "test/greenNestedIfInBranch.calc", "(if 1 (if 1 2 0) 3)", "2");
		test(verbose, "test/greenNestedIfInExp.calc", "(+ (if 1 1 0) 1)", "2");
		test(verbose, "test/greenNestedExpAsTest.calc", "(if (- 1 1) 1 44)", "44");
 
		report();
	}
}
