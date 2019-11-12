package com.company;

import java.io.IOException;

public class BlockNode implements INode {
    INode statementsNode;

    public BlockNode(Tokenizer tokenizer) throws Exception {
        System.out.println("fjdkslfds" + tokenizer.getCurrent().token().toString());

        if(tokenizer.getCurrent().token() == Token.LEFT_CURLY){
            tokenizer.moveNext();
            statementsNode = new StatementsNode(tokenizer);
        } else {
            throw new IOException("Wrong token " + tokenizer.getCurrent().token());
        }
        if(tokenizer.getCurrent().token() == Token.RIGHT_CURLY){
            tokenizer.moveNext();
        }
    }

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return "funkar";
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {
    }




    public INode getStatementsNode() {
        return statementsNode;
    }

    public void setStatementsNode(INode statementsNode) {
        this.statementsNode = statementsNode;
    }
}
