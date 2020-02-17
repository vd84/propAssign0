package com.company;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.IllegalFormatPrecisionException;

public class ExpressionNode implements INode {

    INode termNode = null;
    Lexeme operator, prevOperator = null;
    INode expressionNode = null;


    public ExpressionNode(Tokenizer tokenizer, Lexeme prevOperator) throws Exception {
        this.prevOperator = prevOperator;
        termNode = new TermNode(tokenizer);


        if (tokenizer.getCurrent().token() == Token.SUB_OP || tokenizer.getCurrent().token() == Token.ADD_OP) {
            operator = tokenizer.getCurrent();

            tokenizer.moveNext();
            expressionNode = new ExpressionNode(tokenizer, operator);
        } else if (tokenizer.current().token() != Token.INT_LIT && tokenizer.current().token() != Token.IDENT && tokenizer.current().token() != Token.RIGHT_PAREN && tokenizer.current().token() != Token.SEMICOLON) {
            throw new ParserException("Wrong token, expected: SUB_OP OR ADD_OP, got: " + tokenizer.getCurrent().token().toString());
        }
    }


    @Override
    public Object evaluate(Object[] args) throws Exception {

        double termNodeValue = Double.parseDouble(termNode.evaluate(args).toString());

        if (expressionNode == null) {
            return termNodeValue;
        } else {
            double exprNodeValue = Double.parseDouble(expressionNode.evaluate(args).toString());
            if (operator.token() == Token.ADD_OP) {
                if (prevOperator != null && prevOperator.token() == Token.SUB_OP) {
                    return exprNodeValue - termNodeValue;
                }
                return termNodeValue + exprNodeValue;
            } else {
                return termNodeValue - exprNodeValue;
            }
        }
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        builder.append("\t".repeat(Math.max(0, tabs)));
        tabs++;

        builder.append("ExpressionNode\n");


        if (termNode != null) {
            termNode.buildString(builder, tabs);
        }

        if (operator != null) {
            builder.append("\t".repeat(Math.max(0, tabs)));
            builder.append(operator);
            builder.append("\n");
        }
        if (expressionNode != null) {
            expressionNode.buildString(builder, tabs);
        }
    }

    public INode getTermNode() {
        return termNode;
    }

    public void setTermNode(INode termNode) {
        this.termNode = termNode;
    }

    public Lexeme getOperator() {
        return operator;
    }

    public void setOperator(Lexeme operator) {
        this.operator = operator;
    }

    public Lexeme getPrevOperator() {
        return prevOperator;
    }

    public void setPrevOperator(Lexeme prevOperator) {
        this.prevOperator = prevOperator;
    }

    public INode getExpressionNode() {
        return expressionNode;
    }

    public void setExpressionNode(INode expressionNode) {
        this.expressionNode = expressionNode;
    }
}
