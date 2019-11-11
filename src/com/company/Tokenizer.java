package com.company;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Tokenizer implements ITokenizer {

    private Scanner scanner;
    private Lexeme current = null;


    private static final Map<Character, Token> SYMBOLS;
    //private static final HashSet<String> NOUNS;


    static {
        SYMBOLS = new HashMap<>();




        SYMBOLS.put(Scanner.EOF,Token.EOF);
        SYMBOLS.put(Scanner.NULL, Token.NULL);







    }


    @Override
    public void open(String fileName) throws IOException, TokenizerException {

        scanner = new Scanner();
        scanner.open(fileName);
        scanner.moveNext();


    }

    private void consumeWhiteSpaces() throws IOException {
        while (Character.isWhitespace(scanner.current())) {
            scanner.moveNext();
        }
    }

    private Lexeme extractLexeme() throws IOException {
        consumeWhiteSpaces();


        char current = scanner.current();


        return null;



    }

    @Override
    public Lexeme current() {
        return current;
    }

    @Override
    public void moveNext() throws IOException, TokenizerException {

    }

    @Override
    public void close() throws IOException {

    }
}
