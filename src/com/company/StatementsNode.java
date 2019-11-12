package com.company;

public class StatementsNode implements INode {
    INode assignmentNode;

    public StatementsNode(Tokenizer t) {
        assignmentNode = null;
    }

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

    }
}