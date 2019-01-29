package lexer;

public class Literal implements Token {

    public int val;

    public Literal(int val) {
        this.val = val;
    }

    public String toString()
    {
        return String.valueOf(val);
    }
}
