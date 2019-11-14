package com.company;

import java.io.IOException;

public class TermNode implements INode {
    INode factorNode = null;
    Lexeme operator = null;
    INode termNode = null;

    public TermNode(Tokenizer tokenizer) throws IOException, TokenizerException {
        factorNode = new FactorNode(tokenizer);

        if(tokenizer.getCurrent().token() == Token.MULT_OP || tokenizer.getCurrent().token() == Token.DIV_OP){
            operator = tokenizer.getCurrent();
            System.out.println("FactorNode " + tokenizer.getCurrent().value().toString() );
            tokenizer.moveNext();
            termNode = new TermNode(tokenizer);




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

        builder.append("TermNode\n");


        if(factorNode != null){
            factorNode.buildString(builder, tabs);
        }
        if(operator != null){
            builder.append("\t".repeat(Math.max(0, tabs)));
            builder.append(operator);
            builder.append("\n");
        }


        if(termNode != null){
            termNode.buildString(builder, tabs);
        }





    }
}
