package com.company;

import java.io.IOException;

public class AssignmentNode implements INode {
    INode expressionNode;
    Lexeme id;

    public AssignmentNode(Tokenizer tokenizer) throws IOException, TokenizerException {

        if (tokenizer.getCurrent().token() == Token.IDENT) {
            id = tokenizer.getCurrent();
            tokenizer.moveNext();

            if(tokenizer.getCurrent().token() == Token.ASSIGN_OP){
                tokenizer.moveNext();

            }
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
