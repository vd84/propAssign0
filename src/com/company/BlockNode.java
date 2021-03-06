package com.company;

public class BlockNode implements INode {
    Lexeme leftCurly, rightCurly = null;
    INode statementsNode = null;

    public BlockNode(Tokenizer tokenizer) throws Exception {
        System.out.println("block" + tokenizer.getCurrent().value().toString());

        if (tokenizer.getCurrent().token() == Token.LEFT_CURLY) {
            leftCurly = tokenizer.current();
            tokenizer.moveNext();
            statementsNode = new StatementsNode(tokenizer);
        } else {
            throw new ParserException("Wrong token, expected: LEFT_CURLY, got: " + tokenizer.getCurrent().token().toString());
        }
        if (tokenizer.getCurrent().token() == Token.RIGHT_CURLY) {
            rightCurly = tokenizer.current();
            tokenizer.moveNext();
        } else
            throw new ParserException("Wrong Token " + tokenizer.getCurrent().token().toString());
    }

    @Override
    public Object evaluate(Object[] args) throws Exception {

        ResultNode[] resultArray = new ResultNode[512];
        if(statementsNode != null) {
            return statementsNode.evaluate(resultArray);
        }
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

        if (leftCurly != null)
            builder.append("Blocknode\n" + leftCurly + "\n");
        tabs++;

        if (statementsNode != null) {
            statementsNode.buildString(builder, tabs);
        }
        if (rightCurly != null) {
            builder.append(rightCurly + "\n");

        }


    }

    public INode getStatementsNode() {
        return statementsNode;
    }

    public void setStatementsNode(INode statementsNode) {
        this.statementsNode = statementsNode;
    }


}
