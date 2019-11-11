package com.company;

import java.io.IOException;

public class Parser implements IParser {
    private Tokenizer tokenizer = null;


    @Override
    public void open(String fileName) throws IOException, TokenizerException {
        tokenizer = new Tokenizer();
        tokenizer.open(fileName);
        tokenizer.moveNext();
    }

    @Override
    public INode parse() throws IOException, TokenizerException, ParserException {
        if (tokenizer == null)
            throw new IOException("No open file.");
        tokenizer.moveNext();

        return new BlockNode(tokenizer);
    }

    @Override
    public void close() throws IOException {
        tokenizer = null;
    }



}
