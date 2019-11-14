package com.company;

import java.io.IOException;

public class ExpressionNode implements INode {

    INode termNode = null;
    Lexeme operator = null;
    INode expressionNode = null;

    public ExpressionNode(Tokenizer tokenizer) throws IOException, TokenizerException {
        System.out.println("ExpressionNode " + tokenizer.getCurrent().value().toString());
        termNode = new TermNode(tokenizer);


        if (tokenizer.getCurrent().token() == Token.SUB_OP || tokenizer.getCurrent().token() == Token.ADD_OP) {
            operator = tokenizer.getCurrent();
            System.out.println("ExpressionNode " + tokenizer.getCurrent().value().toString());

            tokenizer.moveNext();
            expressionNode = new ExpressionNode(tokenizer);
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

        builder.append("ExpressionNode\n");



        if(termNode != null){
            termNode.buildString(builder, tabs);
        }

        if(operator != null){
            builder.append("\t".repeat(Math.max(0, tabs)));
            builder.append(operator);
            builder.append("\n");
        }
        if(expressionNode != null){
            expressionNode.buildString(builder, tabs);
        }



    }
}
