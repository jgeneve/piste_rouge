package lexer;

public class Identifier implements Token {

    public String val;

    public Identifier(String val) {
        this.val = val;
    }

    public String toString()
    {
        return val;
    }

}
