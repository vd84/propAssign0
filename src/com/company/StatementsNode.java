package com.company;

import java.io.IOException;

public class StatementsNode implements INode {

    INode assignmentNode;
    INode statementNode;

    public StatementsNode(Tokenizer t) throws Exception {

        if(t.getCurrent().token() == Token.IDENT) {
            System.out.println("statementsNode " + t.getCurrent().value().toString());
            assignmentNode = new AssignmentNode(t);
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