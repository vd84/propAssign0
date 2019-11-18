package com.company;

import java.io.IOException;

public class AssignmentNode implements INode {
    INode expressionNode = null;
    Lexeme id = null;
    Lexeme assign = null;
    Lexeme semicolon = null;

    public AssignmentNode(Tokenizer tokenizer) throws Exception {

        if (tokenizer.getCurrent().token() == Token.IDENT) {
            id = tokenizer.getCurrent();

            tokenizer.moveNext();

            if(tokenizer.getCurrent().token() == Token.ASSIGN_OP){
                assign = tokenizer.getCurrent();
                System.out.println("AssignmentNodeAssignment " + tokenizer.getCurrent().value().toString());
                tokenizer.moveNext();
                expressionNode = new ExpressionNode(tokenizer);


                if(tokenizer.getCurrent().token() == Token.SEMICOLON){
                    semicolon = tokenizer.getCurrent();

                    System.out.println("AssignmentNodeSemicolon " + tokenizer.getCurrent().value().toString());
                    tokenizer.moveNext();
                } else
                    throw new IOException("Wrong token, expected: SEMICOLON, got: " + tokenizer.getCurrent().token().toString());

            } else
                throw new IOException("Wrong token, expected: ASSIGN_OP, got: " + tokenizer.getCurrent().token().toString());
        }



    }

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

        builder.append("\t".repeat(Math.max(0, tabs)));
        tabs++;

        builder.append("AssignmentNode \n");
        if(id != null) {
            builder.append("\t".repeat(Math.max(0, tabs)));
            builder.append(id);
            builder.append("\n");

        }
        if(assign != null) {
            builder.append("\t".repeat(Math.max(0, tabs)));
            builder.append(assign);
            builder.append("\n");

        }


        if(expressionNode != null)
            expressionNode.buildString(builder,tabs);




        if(semicolon != null) {
            builder.append("\t".repeat(Math.max(0, tabs)));
            builder.append(semicolon);
            builder.append("\n");


        }

    }


}
