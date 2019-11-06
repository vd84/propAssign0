package com.company;

import java.io.IOException;

public class Tokenizer implements ITokenizer {

    private Scanner scanner;

    @Override
    public void open(String fileName) throws IOException, TokenizerException {

        scanner = new Scanner();
        scanner.open(fileName);


    }

    @Override
    public Lexeme current() {
        return null;
    }

    @Override
    public void moveNext() throws IOException, TokenizerException {

    }

    @Override
    public void close() throws IOException {

    }
}
