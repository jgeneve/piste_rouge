package parser;

import eval.State;
import lexer.*;
import lexer.Identifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FuncDef extends AST {

    private Defun defun;
    public Head head;
    public Body body;

    public FuncDef(Head head, Body body){
        this.defun= new Defun();
        this.head=head;
        this.body=body;
    }

    public static FuncDef parse(Token t) {
            try {
                Head head = Head.parse(t);
                Body body = Body.parse(SLexer.getToken(), new ArrayList<>());
                checkRPar(SLexer.getToken());
                return new FuncDef(head, body);
            } catch (IOException | UnexpectedCharacter e) {
                e.printStackTrace();
            }
        return null;
    }

    public static void checkRPar(Token token) {
        if (!(token instanceof RPar)) {
            throw new SyntaxError("Unexpected closing token");
        }
    }

    public int eval(List<Integer> params, State<FuncDef> stateFunc) {
        List<String> identifiers = new ArrayList<>();
        for (Identifier var_id : this.head.var_ids) {
            identifiers.add(var_id.toString());
        }

        State<Integer> stateVar = bindArgumentsFunction(identifiers, params);
        return this.body.eval(stateVar, stateFunc);
    }

    private State<Integer> bindArgumentsFunction(List<String> variables, List<Integer> params) {
        State<Integer> stateVar = new State<>();
        for (int i = 0; i < params.size(); i++) {
            stateVar.bind(variables.get(i), params.get(i));
        }
        return stateVar;
    }

    @Override
    public String toString() {
        return "FUNC_DEF("+defun + ", " + head + ", " + body + ")";
    }

}
