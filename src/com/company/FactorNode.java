package com.company;

import java.io.IOException;

public class FactorNode implements INode {

    Lexeme var;
    INode expressionNode;

    public FactorNode(Tokenizer tokenizer) throws IOException, TokenizerException {

        if (tokenizer.getCurrent().token() == Token.INT_LIT || tokenizer.getCurrent().token() ==  Token.IDENT){

            System.out.println("FactorNode " + tokenizer.getCurrent().value().toString());
            tokenizer.moveNext();
        } else if (tokenizer.getCurrent().token() == Token.LEFT_PAREN){
            System.out.println("FactorNode " + tokenizer.getCurrent().value().toString());
            tokenizer.moveNext();
            expressionNode = new ExpressionNode(tokenizer);
            if(tokenizer.getCurrent().token() == Token.RIGHT_PAREN){
                System.out.println("FactorNode " + tokenizer.getCurrent().value().toString());
                tokenizer.moveNext();
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
