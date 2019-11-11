package com.company;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Tokenizer implements ITokenizer {
    private Scanner scanner;
    private Lexeme current = null;
    private Lexeme next = null;

    @Override
    public void open(String fileName) throws IOException, TokenizerException {

        scanner = new Scanner();
        scanner.open(fileName);
        scanner.moveNext();
        next = extractLexeme();


    }

    private void consumeWhiteSpaces() throws IOException {
        while (Character.isWhitespace(scanner.current())) {
            scanner.moveNext();
        }
    }

    private Lexeme extractLexeme() throws IOException, TokenizerException {
        consumeWhiteSpaces();
        char current = scanner.current();

        switch (current) {
            case '+':
                return new Lexeme(current, Token.ADD_OP);
            case '-':
                return new Lexeme(current, Token.SUB_OP);
            case '*':
                return new Lexeme(current, Token.MULT_OP);
            case '/':
                return new Lexeme(current, Token.DIV_OP);
            case '{':
                return new Lexeme(current, Token.LEFT_CURLY);
            case '}':
                return new Lexeme(current, Token.RIGHT_CURLY);
            case '(':
                return new Lexeme(current, Token.LEFT_PAREN);
            case ')':
                return new Lexeme(current, Token.RIGHT_PAREN);
            case '=':
                return new Lexeme(current, Token.ASSIGN_OP);
            case ';':
                return new Lexeme(current, Token.SEMICOLON);
            case Scanner.EOF:
                return new Lexeme(current, Token.EOF);
            case Scanner.NULL:
                return new Lexeme(current, Token.NULL);
            default:
                if (current >= 'a' && current <= 'z') {
                    return new Lexeme(current, Token.IDENT);
                }
                if (current >= '0' && current <= '9') {
                    return new Lexeme(current, Token.INT_LIT);
                }

                throw new TokenizerException("Unknown token " + current);
        }


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

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public Lexeme getCurrent() {
        return current;
    }

    public void setCurrent(Lexeme current) {
        this.current = current;
    }

    public Lexeme getNext() {
        return next;
    }

    public void setNext(Lexeme next) {
        this.next = next;
    }
}
