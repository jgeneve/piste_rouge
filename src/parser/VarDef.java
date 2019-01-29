package parser;

import eval.State;
import lexer.*;
import lexer.Identifier;

import java.io.IOException;

public class VarDef extends AST {

    private Defvar defvar;
    private Identifier identifier;
    private Expression exp2;

    public VarDef(Identifier identifier, Expression e2){
        this.defvar= new Defvar();
        this.identifier=identifier;
        this.exp2=e2;
    }

    public static VarDef parse(Token t) {
            try {
                if (t instanceof Identifier) {
                    Expression exp = Expression.parse(SLexer.getToken());
                    checkRPar(SLexer.getToken());
                    return new VarDef((Identifier)t, exp);
                }
            } catch (IOException | UnexpectedCharacter e) {
                e.printStackTrace();
            }
        return null;
    }

    private static void checkRPar(Token token) {
        if (!(token instanceof lexer.RPar)) {
            throw new SyntaxError("Unexpected closing token");
        }
    }

    public void eval(State<Integer> stateVar, State<FuncDef> stateFunc) {
        if (stateVar.lookup(this.identifier.toString()) != null) {
            throw new SyntaxError("You cannot rebind this variable");
        } else {
            stateVar.bind(this.identifier.toString(), this.exp2.eval(stateVar, stateFunc));
        }
    }

    @Override
    public String toString() {
        return "VAR_DEF("+defvar + ", " + identifier + ", " + exp2 + ")";
    }

}
