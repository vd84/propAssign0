package com.company;

import java.io.IOException;

public class FactorNode implements INode {

    Lexeme var;
    INode expressionNode;
    Lexeme leftParen;

    public FactorNode(Tokenizer tokenizer) throws IOException, TokenizerException, ParserException {

        if (tokenizer.getCurrent().token() == Token.INT_LIT || tokenizer.getCurrent().token() == Token.IDENT) {
            var = tokenizer.getCurrent();
            System.out.println("FactorNode " + tokenizer.getCurrent().value().toString());
            tokenizer.moveNext();
        } else if (tokenizer.getCurrent().token() == Token.LEFT_PAREN) {
            leftParen = tokenizer.getCurrent();
            System.out.println("FactorNode " + tokenizer.getCurrent().value().toString());
            tokenizer.moveNext();
            expressionNode = new ExpressionNode(tokenizer);
            if (tokenizer.getCurrent().token() == Token.RIGHT_PAREN) {
                var = tokenizer.getCurrent();
                System.out.println("FactorNode " + tokenizer.getCurrent().value().toString());
                tokenizer.moveNext();
            } else
                throw new ParserException("Wrong token, expected: RIGHT_PAREN, got: " + tokenizer.getCurrent().token().toString());

        } else
            throw new ParserException("Wrong token, expected: LEFT_PAREM, got: " + tokenizer.getCurrent().token().toString());

    }

    @Override
    public Object evaluate(Object[] args) throws Exception {

        if (expressionNode == null) {
            if (var.token() == Token.INT_LIT) {
                return var.value();
            } else if (var.token() == Token.IDENT) {
                for (int i = 0; i < args.length; i++) {

                    ResultNode v = (ResultNode) args[i];
                    if (v.getId().equals(var.value().toString())) {
                        return v.getValue();
                    }
                }
            }
        }
        return expressionNode.evaluate(args);


    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        builder.append("\t".repeat(Math.max(0, tabs)));
        tabs++;

        builder.append("FactorNode\n");

        if (leftParen != null) {
            builder.append("\t".repeat(Math.max(0, tabs)));
            builder.append(leftParen);
            builder.append("\n");


        }

        if (expressionNode != null) {
            expressionNode.buildString(builder, tabs);
        }

        if (var != null) {
            builder.append("\t".repeat(Math.max(0, tabs)));
            builder.append(var);
            builder.append("\n");


        }


    }
}
