package com.company;

public class TermNode implements INode {
    INode factorNode;
    Lexeme operator;
    INode termNode;

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

    }
}
