package com.company;

import java.io.IOException;

public class FactorNode implements INode {

    Lexeme var;
    INode expressionNode;

    public FactorNode(Tokenizer tokenizer) throws IOException, TokenizerException {

        if (tokenizer.getCurrent().token() == Token.INT_LIT || tokenizer.getCurrent().token() ==  Token.IDENT){
            var = tokenizer.getCurrent();

            System.out.println("FactorNode " + tokenizer.getCurrent().value().toString());
            tokenizer.moveNext();
        } else if (tokenizer.getCurrent().token() == Token.LEFT_PAREN){
            var = tokenizer.getCurrent();
            System.out.println("FactorNode " + tokenizer.getCurrent().value().toString());
            tokenizer.moveNext();
            expressionNode = new ExpressionNode(tokenizer);
            if(tokenizer.getCurrent().token() == Token.RIGHT_PAREN){
                var = tokenizer.getCurrent();
                System.out.println("FactorNode " + tokenizer.getCurrent().value().toString());
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
        builder.append("\t".repeat(Math.max(0, tabs)));
        tabs++;

        builder.append("FactorNode\n");



        if(expressionNode != null){
            expressionNode.buildString(builder, tabs);
        }

        if(var != null){
            builder.append("\t".repeat(Math.max(0, tabs)));
            builder.append(var);
            builder.append("\n");


        }



    }
}
