package parser;

import eval.State;
import lexer.SLexer;
import lexer.Token;
import lexer.UnexpectedCharacter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Body extends AST {
    private List<VarDef> defs;
    private Expression exp;

    private Body(List<VarDef> defs, Expression exp) {
        this.defs = defs;
        this.exp = exp;
    }

    public static Body parse(Token token, List<VarDef> defs) throws IOException, UnexpectedCharacter {
        if (token instanceof lexer.LPar) {
            Token token2 = SLexer.getToken();
            return getBody(token2, defs);
        } else {
            // this is the "simple" expression at the end of the body
            return parseSimpleBody(token, defs);
        }
    }

    public static Body parseCompositeExpressionTail(Token token2, List<VarDef> defs) throws IOException, UnexpectedCharacter {
        return getBody(token2, defs);
    }

    private static Body getBody(Token token2, List<VarDef> defs) throws IOException, UnexpectedCharacter {
        if (token2 instanceof lexer.Defvar) { // this is a definition
            // parse tail of definition
            VarDef def = VarDef.parse(SLexer.getToken());
            // accumulate definition
            defs.add(def);
            // loop on the rest of the body with the accumulated definitions
            return parse(SLexer.getToken(), defs);
        } else { // this is the "composite" expression at the end of the body
                Expression exp = Expression.parseCompositeExpressionTail(token2);
                return new Body(defs, exp);
        }
    }

    static Body parseSimpleBody(Token token, List<VarDef> defs) throws IOException, UnexpectedCharacter {
        Expression exp = Expression.parse(token);
        return new Body(defs, exp);
    }

    @Override
    public String toString() {
        return null;
    }

    private void setDefs(List<VarDef> defs, State<Integer> stateVar, State<FuncDef> stateFunc) {
        for (VarDef def : defs) {
            def.eval(stateVar, stateFunc);
        }
    }

    public int eval(State<Integer> stateVar, State<FuncDef> stateFunc) {
        setDefs(defs, stateVar, stateFunc);
        return this.exp.eval(stateVar, stateFunc);
    }
}