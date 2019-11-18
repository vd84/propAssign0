package com.company;

import java.io.IOException;

public class Parser implements IParser {
    private Tokenizer tokenizer = null;


    @Override
    public void open(String fileName) throws IOException, TokenizerException {
/*        tokenizer = new Tokenizer();
        tokenizer.open(fileName);
        tokenizer.setCurrent(tokenizer.getNext());
        tokenizer.moveNext();*/
        tokenizer = new Tokenizer();
        tokenizer.open(fileName);
        tokenizer.moveNext();

    }

    @Override
    public INode parse() throws Exception {
        if (tokenizer == null)
            throw new IOException("No open file. ");

        INode node = new BlockNode(tokenizer);

        return node;
    }

    private INode createBlockNode() throws Exception {

        BlockNode blockNode = new BlockNode(tokenizer);
        blockNode.setStatementsNode(createStatementsNode());
        return blockNode;

    }

    private INode createStatementsNode() {

        return null;
    }

    @Override
    public void close() throws IOException {
        tokenizer = null;
    }


}
