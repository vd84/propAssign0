package com.company;
public class AssignmentNode implements INode {
    INode expressionNode;
    Lexeme id;

    public AssignmentNode(Tokenizer tokenizer) throws Exception {

        if (tokenizer.getCurrent().token() == Token.IDENT) {

            System.out.println("AssignmentNodeIdent " + tokenizer.getCurrent().value().toString());
            tokenizer.moveNext();

            if(tokenizer.getCurrent().token() == Token.ASSIGN_OP){
                System.out.println("AssignmentNodeAssignment " + tokenizer.getCurrent().value().toString());
                tokenizer.moveNext();
                expressionNode = new ExpressionNode(tokenizer);


                if(tokenizer.getCurrent().token() == Token.SEMICOLON){
                    System.out.println("AssignmentNodeSemicolon " + tokenizer.getCurrent().value().toString());
                    tokenizer.moveNext();
                }

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
