package calc;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

import lexer.*;

public class TestLexer {

	/**
	 * @param args - arg[0] is the filename of the file to interpret
     *     (if it exists, otherwise the standard input stream is used).
	 */
	public static void main(String[] args) throws Exception {
		List<Token> tokens;
		String inputFile = null;
		InputStream is = System.in;
		if ( args.length>0 ) {
			inputFile = args[0];
			is = new FileInputStream(inputFile);
		}

		try {
			int i=0;
			Lexer lexer = new Lexer(is);
			tokens = lexer.lex(); 
			// output of the result	
			for (Token token : tokens) {
				System.out.println(token);
				i++;
			}
			System.out.println("Nombre de tokens trouvés  : "+i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
