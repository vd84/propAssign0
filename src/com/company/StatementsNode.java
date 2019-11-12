package com.company;

import java.io.IOException;

public class StatementsNode implements INode {

    INode assignmentNode;
    INode statementNode;

    public StatementsNode(Tokenizer t) throws IOException, TokenizerException {

        if(t.getCurrent().token() == Token.IDENT){
            System.out.println("statementsNode" + t.getCurrent().token().toString());
            t.moveNext();
            assignmentNode = new AssignmentNode(t);
            //hur ska detta hanteras? Rekursivt anrop
            statementNode = new StatementsNode(t);
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