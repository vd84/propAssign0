package com.company;

import java.io.IOException;

public class TermNode implements INode {
    INode factorNode;
    Lexeme operator;
    INode termNode;

    public TermNode(Tokenizer tokenizer) throws IOException, TokenizerException {
        factorNode = new FactorNode(tokenizer);

        if(tokenizer.getCurrent().token() == Token.MULT_OP || tokenizer.getCurrent().token() == Token.DIV_OP){
            System.out.println("FactorNode " + tokenizer.getCurrent().value().toString() );
            tokenizer.moveNext();
            if(tokenizer.getCurrent().token() == Token.INT_LIT || tokenizer.getCurrent().token() == Token.IDENT || tokenizer.getCurrent().token() == Token.LEFT_PAREN) {
                System.out.println("FactorNode " + tokenizer.getCurrent().value().toString() );
                termNode = new TermNode(tokenizer);
            }



        }

    }

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

    }
}
