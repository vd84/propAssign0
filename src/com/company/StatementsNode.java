package com.company;

public class StatementsNode implements INode {

    INode assignmentNode;
    INode statementNode;

    public StatementsNode(Tokenizer t) throws Exception {

        if (t.getCurrent().token() == Token.IDENT) {
            assignmentNode = new AssignmentNode(t);
            statementNode = new StatementsNode(t);

        } else if (t.current().token() != Token.IDENT && t.current().token() != Token.RIGHT_CURLY) {
            throw new ParserException("Wrong token, expected Identifier " + t.getCurrent().token());

        }

    }

    @Override
    public Object evaluate(Object[] args) throws Exception {


        StringBuilder stringBuilder = new StringBuilder();
        if (assignmentNode != null) {

            ResultNode currentResult = (ResultNode) assignmentNode.evaluate(args);

            stringBuilder.append(currentResult.getId() + " = " + String.format("%.01f\n", currentResult.getValue()));

            for (int i = 0; i < args.length; i++) {
                if (args[i] != null) {
                    ResultNode curRes = (ResultNode) args[i];
                    if (curRes.getId().equals(currentResult.getId())) {
                        args[i] = currentResult;
                        break;
                    }
                } else {
                    args[i] = currentResult;
                    break;
                }
            }
            stringBuilder.append(statementNode.evaluate(args));
        }


        return stringBuilder;
    }


    @Override
    public void buildString(StringBuilder builder, int tabs) {
        System.out.println(tabs);
        for (int i = 0; i < tabs; i++) {
            builder.append("\t");
        }
        tabs++;

        builder.append("StatementsNode \n");

        if (assignmentNode != null)
            assignmentNode.buildString(builder, tabs);
        if (statementNode != null)
            statementNode.buildString(builder, tabs);


    }
}