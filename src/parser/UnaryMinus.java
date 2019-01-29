package parser;

import eval.State;
import lexer.Op;

public class UnaryMinus extends Expression {

    private Op operator;
    private Expression exp1;

    public UnaryMinus(Expression e1){
        this.operator= Op.MINUS;
        this.exp1=e1;
    }

    @Override
    public String toString() {
        return "UNARY_EXPRESSION("+operator + ", " + exp1 + ")";
    }

    @Override
    public int eval(State<Integer> stateVar, State<FuncDef> stateFunc) {
        return - this.exp1.eval(stateVar, stateFunc);
    }
}
