import java.util.HashMap;

public class Lexer {
    // binary operations
    public static final HashMap<Character,Token<BinaryOperation>> ops =
	(new SymbolTable()).getTable();
    public static final HashMap<String,BinaryOperation> tt =
	(new SymbolTable()).tokenTable();
    
    public static final String EOF = "EOF",INT="INT",LPAR="LPAR",RPAR="RPAR";
    public static final char EMPTY = '\0';

    private String text;
    private int pos;
    private char curr_char;

    public Lexer(String text) {
	this.text = text;
	pos = 0;
	curr_char = text.charAt(pos);	
    }
    
    public void error() {
	throw new Error("Invalid character");
    }

    /** increment position pointer
     */
    public void increment() {
	pos++;
	if(pos > text.length() - 1) {
	    curr_char = EMPTY;	    
	} else {
	    curr_char = text.charAt(pos);
	}
    }

    /** skip whitespace
     */
    public void skipSpace() {
	while(curr_char != EMPTY && Character.isSpace(curr_char))
	    increment();
    }

    /** return integer represented by string of consecutive digits
     */
    public int extractInt() {
	String res = "";
	while(curr_char != EMPTY && Character.isDigit(curr_char)) {
	    res += curr_char;
	    increment();
	}

	return Integer.parseInt(res);
    }

    /** return token corresponding to next character(s)
     */
    public Token next() {
	while(curr_char!=EMPTY) {
	    if(Character.isSpace(curr_char)) {
		skipSpace();
		continue;
	    }
	
	    if(Character.isDigit(curr_char)) {
		return new Token<Integer>(INT, extractInt());
	    }

	    if(ops.containsKey(curr_char)) {
		if(curr_char=='+') {
		    increment();
		    return new Token<Functor>("PLUS", null);		    
		}

		if(curr_char=='-') {
		    increment();
		    return new Token<Functor>("MINUS", null);
		}
		
		Token<BinaryOperation> res = ops.get(curr_char);
		increment();
		return res;
	    }

	    if(curr_char=='(') {
		increment();
		return new Token<Character>(LPAR, '(');
	    }

	    if(curr_char==')') {
		increment();
		return new Token<Character>(RPAR, ')');
	    }

	    error();
	}

	return new Token<Character>(EOF,EMPTY);
    }
}
