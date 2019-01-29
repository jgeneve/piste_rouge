package calc;

import eval.State;
import lexer.SLexer;
import lexer.UnexpectedCharacter;
import parser.Body;
import parser.FuncDef;
import parser.Program;
import parser.SyntaxError;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Calc {
    public static void main(String[] args) throws IOException, UnexpectedCharacter {
        String inputFile = null;
        InputStream is = System.in;
        if ( args.length>0 ) {
            inputFile = args[0];
            is = new FileInputStream(inputFile);
        }
        System.out.println(interpret(is));
    }

    public static int interpret(InputStream is) throws IOException, UnexpectedCharacter {
        try {
            SLexer.init(is);
            Program program = Program.parse(SLexer.getToken(), new ArrayList<>());
            State<FuncDef> stateFunc = new State<>();

            for (FuncDef funcDef : program.defs) {
                if (!stateFunc.containsKey(funcDef.head.func_id.val)) {
                    stateFunc.bind(funcDef.head.func_id.toString(), funcDef);
                } else {
                    throw new SyntaxError("Unknown function");
                }
            }
            return program.eval(stateFunc);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
