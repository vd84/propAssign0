package com.company;

import java.io.IOException;

public class ExpressionNode implements INode {

    INode termNode = null;
    Lexeme operator = null;
    INode expressionNode = null;

    public ExpressionNode(Tokenizer tokenizer) throws IOException, TokenizerException, ParserException {
        System.out.println("ExpressionNode " + tokenizer.getCurrent().value().toString());
        termNode = new TermNode(tokenizer);


        if (tokenizer.getCurrent().token() == Token.SUB_OP || tokenizer.getCurrent().token() == Token.ADD_OP) {
            operator = tokenizer.getCurrent();
            System.out.println("ExpressionNode " + tokenizer.getCurrent().value().toString());

            tokenizer.moveNext();
            expressionNode = new ExpressionNode(tokenizer);
        } else if (tokenizer.current().token() != Token.INT_LIT && tokenizer.current().token() != Token.IDENT && tokenizer.current().token() != Token.RIGHT_PAREN && tokenizer.current().token() != Token.SEMICOLON) {
            throw new ParserException("Wrong token, expected: SUB_OP OR ADD_OP, got: " + tokenizer.getCurrent().token().toString());
        }
    }



    @Override
    public Object evaluate(Object[] args) throws Exception {

        double termValue = Double.parseDouble(termNode.evaluate(args).toString());

        if (expressionNode == null) {
            return termValue;
        } else {
            double exprValue = Double.parseDouble(expressionNode.evaluate(args).toString());
            if (operator.token() == Token.ADD_OP) {
                return termValue + exprValue;
            } else {
                return termValue - exprValue;


            }
        }
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
