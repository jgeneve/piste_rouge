package parser;

import eval.State;
import lexer.SLexer;
import lexer.Token;
import lexer.UnexpectedCharacter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Program extends AST {
    public List<FuncDef> defs;
    private Body body;
    private State<Integer> stateVar = new State<>();

    private Program(List<FuncDef> defs, Body body) {
        this.defs = defs;
        this.body = body;
    }

    public static Program parse(Token token, List<FuncDef> defs) throws IOException, UnexpectedCharacter {
        if (token instanceof lexer.LPar) {
            Token token2 = SLexer.getToken();
            if (token2 instanceof lexer.Defun) { // this is a definition
            // parse tail of definition
                FuncDef def = FuncDef.parse(SLexer.getToken());
            // accumulate definition
                defs.add(def);
            // loop on the rest of the body with the accumulated definitions
                return parse(SLexer.getToken(), defs);
            } else { // this is the "composite" expression at the end of the body
                Body body = Body.parseCompositeExpressionTail(token2, new ArrayList<>());
                return new Program(defs, body);
            }
        } else // this is the "simple" expression at the end of the body
            return parseSimpleBody(token, defs);
    }

    static Program parseSimpleBody(Token token, List<FuncDef> defs) throws IOException, UnexpectedCharacter {
        Body body = Body.parse(token, new ArrayList<>());
        return new Program(defs, body);
    }

    @Override
    public String toString() {
        return null;
    }

    public int eval(State<FuncDef> stateFunc) {
        return this.body.eval(stateVar, stateFunc);
    }
}