package com.company;

public class AssignmentNode implements INode {
    INode expressionNode;
    Lexeme id;

    public AssignmentNode(Tokenizer tokenizer) {

        if(tokenizer.getCurrent().token() == Token.ASSIGN_OP){
            System.out.println("assignmentNODE " + tokenizer.getCurrent().token());
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
