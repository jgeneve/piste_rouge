package parser;

import eval.State;
import lexer.Op;

public class BinaryExpression extends Expression {

    private Op operator;
    private Expression exp1;
    private Expression exp2;

    public BinaryExpression(Op o, Expression e1, Expression e2){
        this.operator=o;
        this.exp1=e1;
        this.exp2=e2;
    }

    @Override
    public String toString() {
        return "BINARY_EXPRESSION("+operator + ", " + exp1 + ", " + exp2 + ")";
    }

    @Override
    public int eval(State<Integer> stateVar, State<FuncDef> stateFunc) {
        if (operator.equals(Op.MINUS)) {
            return this.exp1.eval(stateVar, stateFunc) - this.exp2.eval(stateVar, stateFunc);
        } else if (operator.equals(Op.PLUS)) {
            return this.exp1.eval(stateVar, stateFunc) + this.exp2.eval(stateVar, stateFunc);
        } else if (operator.equals(Op.DIVIDE)) {
            return this.exp1.eval(stateVar, stateFunc) / this.exp2.eval(stateVar, stateFunc);
        } else if (operator.equals(Op.TIMES)) {
            return this.exp1.eval(stateVar, stateFunc) * this.exp2.eval(stateVar, stateFunc);
        } else if (operator.equals(Op.EQUAL)) {
            return this.exp1.eval(stateVar, stateFunc) == this.exp2.eval(stateVar, stateFunc) ? 1 : 0;
        } else if (operator.equals(Op.LESS)) {
            return this.exp1.eval(stateVar, stateFunc) < this.exp2.eval(stateVar, stateFunc) ? 1 : 0;
        } else if (operator.equals(Op.MORE)) {
            return this.exp1.eval(stateVar, stateFunc) > this.exp2.eval(stateVar, stateFunc) ? 1 : 0;
        } else {
            throw new SyntaxError("Invalid operator");
        }
    }
}
