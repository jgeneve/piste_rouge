package parser;

import eval.State;
import lexer.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Expression extends AST {
    public abstract int eval(State<Integer> stateVar, State<FuncDef> stateFunc);

    public static Expression parse(Token t) throws IOException, UnexpectedCharacter {
        if (t instanceof lexer.Literal) {
            return new Literal(((lexer.Literal) t).val);
        } else if (t instanceof lexer.Identifier) {
            return new Identifier(((lexer.Identifier)t).val);
        }else if(t instanceof lexer.LPar){
            Token t2 = SLexer.getToken();
            return getExpression(t2);
        }else {
            throw new SyntaxError("Unexpected first token in expression : " + t);
        }
    }

    static Expression parseCompositeExpressionTail(Token t2) throws IOException, UnexpectedCharacter {
        return getExpression(t2);
    }

    private static Expression getExpression(Token t2) throws IOException, UnexpectedCharacter {
        if (t2 instanceof Op) {
            Expression exp1 = Expression.parse(SLexer.getToken());
            Token t3 = SLexer.getToken();
            if (t2.equals(Op.MINUS)) {
                if (t3 instanceof RPar) {
                    return new UnaryMinus(exp1);
                }
            }
            Expression exp2 = Expression.parse(t3);
            checkRPar(SLexer.getToken());
            return new BinaryExpression((Op) t2, exp1, exp2);
        } else if (t2 instanceof If) {
            Expression exp1 = Expression.parse(SLexer.getToken());
            Expression exp2 = Expression.parse(SLexer.getToken());
            Expression exp3 = Expression.parse(SLexer.getToken());
            checkRPar(SLexer.getToken());
            return new ConditionnalExpression(exp1, exp2, exp3);
        } else if (t2 instanceof lexer.Identifier) {
            List<Expression> expressions = new ArrayList<>();
            Token t3 = SLexer.getToken();
            while (!(t3 instanceof RPar)) {
                expressions.add(Expression.parse(t3));
                t3 = SLexer.getToken();
            }
            return new FunctionExpression((lexer.Identifier) t2, expressions);
        } else {
            throw new SyntaxError("Error unknown expression " + t2);
        }
    }

    public static void checkRPar(Token token) {
        if (!(token instanceof lexer.RPar)) {
            throw new SyntaxError("Unexpected closing token");
        }
    }
}
