package test;

import java.io.FileInputStream;
import calc.Calc;

public class Test {
    static int count = 0;
    static int success = 0;
    static boolean verbose = false;

    public static void report(){
        System.out.println(success + " successful tests out of " + count);
    }

    public static void test(boolean verbose, String filename, String mess1, String mess2){
        count++;
        String[] args0 = new String[1];
        args0[0] = filename;
        if (verbose) {
            System.out.println("====: " + filename);
            System.out.println("content: " + mess1);
            System.out.println("expected: " + mess2);
        }
        try {
            int found = Calc.interpret(new FileInputStream(filename));

            if (mess2.equals("error")) { // error expected
                System.err.println("FAILURE on " + filename);
                System.err.println("error expected, found " + found);
            } else { // integer expected
                int expected = Integer.parseInt(mess2);
                if (verbose) System.out.println("result: " + found);
                if (found != expected) {
                    System.err.println("FAILURE on " + filename);
                    System.err.println("expected " + expected + " found " + found);
                } else {
                    success++;
                    System.out.println("SUCCESS on " + filename);
                }
            }
        } catch(Exception e){
            if (mess2.equals("error")) // expected error
                if (e instanceof NullPointerException) {
                    System.err.println("FAILURE on " + filename); // for debugging purposes
                    if (verbose) e.printStackTrace();
                } else {
                    success++;
                    System.out.println("SUCCESS on " + filename + " with error : " + e.getMessage()); // is it indeed a meaningful error?
                    if (verbose) e.printStackTrace();
                }
            else { // unexpected error
                System.err.println("FAILURE on " + filename); // for debugging purposes
                if (verbose) e.printStackTrace();
            }
        }
    }
    public static void main(String[] args){
        if (args.length > 0 && args[0].equals("-v"))
            verbose = true;
        else verbose = false;
    }
}

