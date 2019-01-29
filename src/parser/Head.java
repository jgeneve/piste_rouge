package parser;

import lexer.Identifier;
import lexer.RPar;
import lexer.SLexer;
import lexer.Token;

import java.util.ArrayList;
import java.util.List;

public class Head extends AST {

    public Identifier func_id;
    public List<Identifier> var_ids;

    private Head(Identifier func_id, List<Identifier> var_ids){
        this.func_id=func_id;
        this.var_ids=var_ids;
    }

    public static Head parse(Token t) {
        try {
            List<Identifier> var_ids = new ArrayList<>();
            if (t instanceof lexer.LPar) {
                Token t2 = SLexer.getToken();
                if (t2 instanceof lexer.Identifier) {
                    Token t3 = SLexer.getToken();
                    if (t3 instanceof Identifier) {
                        do {
                            Expression.parse(t3);
                            if (t3 instanceof lexer.Identifier) {
                                var_ids.add((Identifier)t3);
                            }
                            t3 = SLexer.getToken();
                        } while (!(t3 instanceof RPar));
                        return new Head((Identifier) t2, var_ids);
                    } else {
                        return new Head((Identifier) t2, var_ids);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "HEAD("+this.func_id + ", " + this.var_ids + ")";
    }

}
