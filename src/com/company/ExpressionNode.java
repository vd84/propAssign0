package com.company;

public class ExpressionNode implements INode {

    INode termNode;
    Lexeme operator;
    INode expressionNode;

    public ExpressionNode(Tokenizer tokenizer) {
        System.out.println("ExpressionNode " + tokenizer);
    }

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

    }
}
