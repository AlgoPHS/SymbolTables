public class Parser {
    public static final String INT="INT",LPAR="LPAR",RPAR="RPAR",
	PLUS="PLUS",MINUS="MINUS";
    private Token curr_token;
    private Lexer lex;

    public Parser(Lexer lex) {
	this.lex = lex;
	curr_token = lex.next();	
    }
    
    public void error() {
	throw new Error("Parsing Error");
    }

    public void update() {
	curr_token = lex.next();
    }

    /** Parser consumes a sequence of tokens, digests them, and outputs an AST
	for the Interpreter to evaluate. Here, Parser tries to "eat" the token
	with given name and move on to the next token.
     */
    public void eat(String name) {
	if(curr_token.name().equals(name)) {
	    update();
	} else {
	    error();
	}
    }

    /** tokens with highest precedents
     */
    public AST nextInt() {
	String ctn = curr_token.name();
	
	if(ctn.equals(PLUS)) {
	    Token<UnaryOperation> curr = new Token<UnaryOperation>(PLUS,new Plus());
	    eat(PLUS);
	    return new UnOpNode(curr,nextInt());
	} else if(ctn.equals(MINUS)) {
	    Token<UnaryOperation> curr = new Token<UnaryOperation>(MINUS,new Minus());	    
	    eat(MINUS);
	    return new UnOpNode(curr,nextInt());
	} else if(curr_token.name().equals(INT)) {	    
	    Token<Integer> curr = (Token<Integer>)curr_token;
	    eat(INT);
	    return new NumNode(curr);
	} else if(curr_token.name().equals(LPAR)) {
	    eat(LPAR);
	    AST res = eval();
	    eat(RPAR);
	    return res;
	} else {
	    error();
	    return null;
	}
    }

    /** returns AST of multiplication or division operations and their two operands
     */
    public AST nextTerm() {
        AST res = nextInt();

	while(curr_token.equals(Lexer.ops.get('*')) || curr_token.equals(Lexer.ops.get('/'))) {
	    Token<BinaryOperation> tok = curr_token;
	    eat(curr_token.name());
	    res = new BinOpNode(res,tok,nextInt());
	}	
	
	return res;
    }
    
    /** returns AST representation of arithmetic expression
     */
    public AST eval() {
	AST res = nextTerm();
	
	while(curr_token.equals(Lexer.ops.get('+')) || curr_token.equals(Lexer.ops.get('-'))) {
	    String ctn = curr_token.name();
	    Token<BinaryOperation> tok = new Token<BinaryOperation>(ctn, Lexer.tt.get(ctn));    
	    eat(ctn);
	    res = new BinOpNode(res,tok,nextTerm());
	}	

	return res;
    }
}
