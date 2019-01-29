package parser;

import eval.State;

public class ConditionnalExpression extends Expression{
    private Expression exp1;
    private Expression exp2;
    private Expression exp3;

    public ConditionnalExpression(Expression exp1,Expression exp2, Expression exp3){
        this.exp1=exp1;
        this.exp2=exp2;
        this.exp3=exp3;
    }


    @Override
    public String toString() {
        return "CONDITIONNAL_EXPRESSION("+exp1+","+exp2+","+exp3+")";
    }

    @Override
    public int eval(State<Integer> stateVar, State<FuncDef> stateFunc) {
        if (exp1.eval(stateVar, stateFunc) != 0) {
            return exp2.eval(stateVar, stateFunc);
        } else {
            return exp3.eval(stateVar, stateFunc);
        }
    }
}
