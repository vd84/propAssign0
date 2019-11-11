package com.company;

import java.io.IOException;

public class Parser implements IParser {
    Tokenizer tokenizer = null;


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

        StatementNode statementNode = new StatementNode(tokenizer);

        return null;
    }

    @Override
    public void close() throws IOException {
    }



}
