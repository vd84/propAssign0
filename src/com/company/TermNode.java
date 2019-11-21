package com.company;

import java.io.IOException;
import java.util.ArrayList;

public class TermNode implements INode {
    public FactorNode factorNode = null;
    public Lexeme operator = null;
    private TermNode termNode = null;
    Double divisionResult = 0.0;
    Double prevDouble = 0.0;

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

        double factorNodeValue = Double.parseDouble(factorNode.evaluate(args).toString());

        if (termNode == null) {
            return factorNodeValue;
        } else {
            if (operator.token() == Token.DIV_OP && prevDouble == 0.0) {
                if (termNode.operator != null && termNode.operator.token() == Token.DIV_OP) {
                    termNode.setPrevDouble(factorNodeValue);
                    return termNode.evaluate(args);

                } else {
                    factorNodeValue = Double.parseDouble(factorNode.evaluate(args).toString());
                }

                //factorNodeValue = factorNodeValue / prevDouble;
            } else if (termNode.operator != null && termNode.operator.token() == Token.DIV_OP) {
                Double temp = prevDouble / factorNodeValue;
                termNode.setPrevDouble(temp);
                return termNode.evaluate(args);
            } else {
                double termNodeValue = Double.parseDouble(termNode.evaluate(args).toString());
                return factorNodeValue * termNodeValue;
            }

        }
        return factorNodeValue;

    }

    public double divide(double x1, double x2) {

        return x1 / x2;


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

    public FactorNode getFactorNode() {
        return factorNode;
    }

    public Lexeme getOperator() {
        return operator;
    }

    public TermNode getTermNode() {
        return termNode;
    }

    public void setFactorNode(FactorNode factorNode) {
        this.factorNode = factorNode;
    }

    public void setOperator(Lexeme operator) {
        this.operator = operator;
    }

    public void setTermNode(TermNode termNode) {
        this.termNode = termNode;
    }

    public Double getDivisionResult() {
        return divisionResult;
    }

    public void setDivisionResult(Double divisionResult) {
        this.divisionResult = divisionResult;
    }

    public Double getPrevDouble() {
        return prevDouble;
    }

    public void setPrevDouble(Double prevDouble) {
        this.prevDouble = prevDouble;
    }
}
