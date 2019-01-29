package lexer;
import parser.SyntaxError;

import java.util.*;
import java.io.*;


public class Lexer {
	private InputStream in;
	private int i; // current ASCII character (coded as an integer)
	
	public Lexer(InputStream in) throws IOException {
		this.in = in;
		i = in.read(); // initialize current character
	}
	
	public List<Token> lex() throws UnexpectedCharacter, IOException {
		// return the list of tokens recorded in the file
		List<Token> tokens = new ArrayList<Token>();
		
		try {
			Token token = getToken();
	
			while (! (token instanceof EOF)) {
				tokens.add(token);
				token = getToken();
			}
			tokens.add(token); // this is the EOF token
		} catch (IOException e){
				in.close(); // close the reader
				throw e; // pass the exception up the stack
		}
		return tokens;
	}
	
	public Token getToken() throws UnexpectedCharacter, IOException {
		switch (i){
		    case -1 :
			    in.close();
			    return new EOF();
			case '(' :
				i = in.read();
				return new LPar();
			case ')' :
				i = in.read();
				return new RPar();
			case '=' :
				i = in.read();
				if (i == '=') {
					i = in.read();
					return Op.EQUAL;
				}
				return new Defvar();
			case '-' :
				i = in.read();
				return Op.MINUS;
			case '+' :
				i = in.read();
				return Op.PLUS;
			case '*' :
				i = in.read();
				return Op.TIMES;
			case '/' :
				i = in.read();
				return Op.DIVIDE;
			case '>' :
				i = in.read();
				return Op.MORE;
			case '<' :
				i = in.read();
				return Op.LESS;
			case ' ' :
			case 10 :
			case 13 :
			case 9 :
				i = in.read();
				return getToken();
			case '0' :
				i = in.read();
				if ('a' <= i && i <= 'z') {
					throw new SyntaxError("Invalid literal");
				}
				return new Literal(0);
		    default :
		    	if (i >= '0' && i <= '9') {
					return getLiteral();
				} else if (i >= 'a' && i <= 'z') {
					return getIdentifier();
				} else {
					throw new UnexpectedCharacter(i);
				}
		}
	}

	public Token getLiteral() throws IOException {
		String number = "";
		do {
			number += Character.toString((char)i);
			i = in.read();
		} while(i >= '0' && i <= '9');
		if (i >= 'a' && i <= 'z') {
			throw new SyntaxError("Invalid literal");
		}
		return new Literal(Integer.parseInt(number));
	}

	public Token getIdentifier() throws IOException{
		String text = "";
		do {
			text += Character.toString((char)i);
			i = in.read();
		} while(i >= 'a' && i <= 'z' || i >= '0' && i<= '9');
		if (text.equals("if")) {
			return new If();
		} else if (text.equals("defun")) {
			return new Defun();
		} else {
			return new Identifier(text);
		}
	}
}


