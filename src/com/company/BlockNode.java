package com.company;

public class BlockNode implements INode {
    Lexeme curlyLeft;

    public BlockNode(Tokenizer tokenizer) {
       curlyLeft = tokenizer.getCurrent();

    }

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return "funkar";
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

    }
}
