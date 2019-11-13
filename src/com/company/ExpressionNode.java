package com.company;

import java.io.IOException;

public class ExpressionNode implements INode {

    INode termNode;
    Lexeme operator;
    INode expressionNode;

    public ExpressionNode(Tokenizer tokenizer) throws IOException, TokenizerException {
            System.out.println("ExpressionNode " + tokenizer.getCurrent().value().toString());
                termNode = new TermNode(tokenizer);


        if (tokenizer.getCurrent().token() == Token.SUB_OP || tokenizer.getCurrent().token() == Token.ADD_OP){
            System.out.println("ExpressionNode " + tokenizer.getCurrent().value().toString());

            tokenizer.moveNext();
            expressionNode = new ExpressionNode(tokenizer);
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
