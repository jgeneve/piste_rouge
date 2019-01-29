package parser;

import eval.State;

public class Literal extends Expression {
    private int val;
    public Literal(int val){
        this.val=val;
    }
    public String toString(){
        return "LITTERAL("+this.val+")";
    }

    @Override
    public int eval(State<Integer> stateVar, State<FuncDef> stateFunc) {
        return this.val;
    }
}
