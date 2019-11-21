package com.company;

import java.io.IOException;

public class TermNode implements INode {
    INode factorNode = null;
    Lexeme operator = null;
    private TermNode termNode = null;

    public TermNode(Tokenizer tokenizer) throws IOException, TokenizerException, ParserException {
        factorNode = new FactorNode(tokenizer);

        if (tokenizer.getCurrent().token() == Token.MULT_OP || tokenizer.getCurrent().token() == Token.DIV_OP) {
            operator = tokenizer.getCurrent();
            System.out.println("TermNode " + tokenizer.getCurrent().value().toString());
            tokenizer.moveNext();
            termNode = new TermNode(tokenizer);


        } else if (tokenizer.current().token() != Token.SUB_OP && tokenizer.current().token() != Token.ADD_OP && tokenizer.current().token() != Token.INT_LIT && tokenizer.current().token() != Token.IDENT && tokenizer.current().token() != Token.RIGHT_PAREN && tokenizer.current().token() != Token.SEMICOLON) {
            throw new ParserException("Wrong token, expected: MULT_OP or DIV_OP, got: " + tokenizer.getCurrent().token().toString());


        }
    }

    @Override
    public Object evaluate(Object[] args) throws Exception {

        Double factorValue = Double.parseDouble(factorNode.evaluate(args).toString());
        if (termNode == null) {
            return factorValue;
        } else {
            Double termvalue;
            if (termNode.operator != null && termNode.operator.token() == Token.DIV_OP) {
                Double secondTermFactor = Double.parseDouble(termNode.factorNode.evaluate(args).toString());
                factorValue = factorValue /secondTermFactor;
                termvalue= Double.parseDouble(termNode.termNode.evaluate(args).toString());
            } else {
                termvalue= Double.parseDouble(termNode.evaluate(args).toString());
            }
            if(operator.token() == Token.MULT_OP){
                return factorValue * termvalue;
            } else
                return factorValue / termvalue;
        }

    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        builder.append("\t".repeat(Math.max(0, tabs)));
        builder.append("TermNode\n");
        tabs++;


        if (factorNode != null) {
            factorNode.buildString(builder, tabs);
        }
        if (operator != null) {
            builder.append("\t".repeat(Math.max(0, tabs)));
            builder.append(operator);
            builder.append("\n");
        }


        if (termNode != null) {
            termNode.buildString(builder, tabs);
        }


    }
}
