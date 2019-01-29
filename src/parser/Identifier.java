package parser;

import eval.State;

public class Identifier extends Expression {
    private String val;
    public Identifier(String val){
        this.val=val;
    }
    public String toString(){
        return "IDENTIFIER("+this.val+")";
    }

    @Override
    public int eval(State<Integer> stateVar, State<FuncDef> stateFunc) {
        if (stateVar.lookup(this.val) != null) {
            Integer value = stateVar.lookup(this.val);
            return value;
        } else {
            throw new SyntaxError("Unknown variable name " + this.val);
        }
    }
}
