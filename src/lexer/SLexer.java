package lexer;

import java.io.IOException;
import java.io.InputStream;

public class SLexer {
    private static Lexer lexer;
    public static void init(InputStream is) throws IOException {
        lexer = new Lexer(is);
    }
    public static Token getToken() throws IOException, UnexpectedCharacter {
        return lexer.getToken();
    }
 }
