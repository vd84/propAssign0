package com.company;

import java.io.IOException;

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
        scanner.moveNext();


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
                if (Character.isLetter(current) && current >= 'a' && current <= 'z') {
                    StringBuilder stringBuilder = new StringBuilder();

                    while (Character.isLetter(current)) {
                        stringBuilder.append(current);
                        current = scanner.current();
                        scanner.moveNext();
                    }
                    return new Lexeme(stringBuilder.toString(), Token.IDENT);
                }
                if (Character.isDigit(current) && current >= '0' && current <= '9') {
                    StringBuilder digitBuilder = new StringBuilder();

                    while (Character.isDigit(current)) {
                        digitBuilder.append(current);
                        current = scanner.current();
                        scanner.moveNext();
                    }
                    return new Lexeme(digitBuilder.toString(), Token.IDENT);
                }
                throw new TokenizerException("Unknown token " + current);
        }
    }

    @Override
    public Lexeme current() {
        return current;
    }

    public Lexeme getNextLexeme() {
        return next;
    }

    @Override
    public void moveNext() throws IOException, TokenizerException {
        if (scanner == null) {
            throw new IOException("no open file");
        }
        if (next.token() != Token.EOF) {
            next = extractLexeme();
        }
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
