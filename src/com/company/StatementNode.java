package com.company;

public class StatementNode implements INode {
    INode assignmentNode;
    public StatementNode(Tokenizer t) {
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