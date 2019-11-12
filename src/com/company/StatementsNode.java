package com.company;

import java.io.IOException;

public class StatementsNode implements INode {
    Lexeme ident, assignOp;

    INode assignmentNode;
    INode statementNode;

    public StatementsNode(Tokenizer t) throws IOException, TokenizerException {
        //statement ska ha barnen statement eller assignment

        if(t.getCurrent().token() == Token.IDENT){
            System.out.println("statementsNode" + t.getCurrent().token().toString());
            ident = t.getCurrent();
            t.moveNext();
            assignmentNode = new AssignmentNode(t);

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