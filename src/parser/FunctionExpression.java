package parser;

import eval.State;
import lexer.Identifier;

import java.util.ArrayList;
import java.util.List;

public class FunctionExpression extends Expression {
    private Identifier identifier;
    private List<Expression> expressions;

    public FunctionExpression(Identifier identifier, List<Expression> expressions){
        this.identifier = identifier;
        this.expressions = expressions;
    }
    public String toString(){
        return "FunctionExpression(" + this.identifier + "," + this.expressions + ")";
    }

    @Override
    public int eval(State<Integer> stateVar, State<FuncDef> stateFunc) {
        if (stateFunc.lookup(this.identifier.val) != null) {
            FuncDef funcDef = stateFunc.lookup(this.identifier.toString());
            List<Integer> params = new ArrayList<>();
            for (Expression exp : expressions) {
                params.add(exp.eval(stateVar, stateFunc));
            }
            return funcDef.eval(params, stateFunc);
        } else {
            throw new SyntaxError("Unknown function");
        }
    }
}
