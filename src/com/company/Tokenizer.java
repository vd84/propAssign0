package com.company;

import java.io.IOException;

public class Tokenizer implements ITokenizer {
    private Scanner scanner;
    private Lexeme current = null;
    private Lexeme next = null;


    @Override
    public void open(java.lang.String fileName) throws IOException, TokenizerException {
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
        //char current = scanner.current();
        //scanner.moveNext();
        //this.moveNext();

        Lexeme lexeme;


        switch (scanner.current()) {
            case '+':
                lexeme = new Lexeme(scanner.current(), Token.ADD_OP);
                scanner.moveNext();
                return lexeme;

            case '-':
                lexeme =  new Lexeme(scanner.current(), Token.SUB_OP);
                scanner.moveNext();
                return lexeme;

            case '*':
                lexeme =  new Lexeme(scanner.current(), Token.MULT_OP);
                scanner.moveNext();
                return lexeme;

            case '/':
                lexeme =  new Lexeme(scanner.current(), Token.DIV_OP);
                scanner.moveNext();
                return lexeme;
            case '{':
                lexeme =  new Lexeme(scanner.current(), Token.LEFT_CURLY);
                scanner.moveNext();
                return lexeme;

            case '}':
                lexeme =  new Lexeme(scanner.current(), Token.RIGHT_CURLY);
                scanner.moveNext();
                return lexeme;

            case '(':
                lexeme =  new Lexeme(scanner.current(), Token.LEFT_PAREN);
                scanner.moveNext();
                return lexeme;

            case ')':
                lexeme =  new Lexeme(scanner.current(), Token.RIGHT_PAREN);
                scanner.moveNext();
                return lexeme;

            case '=':
                lexeme =  new Lexeme(scanner.current(), Token.ASSIGN_OP);
                scanner.moveNext();
                return lexeme;

            case ';':
                lexeme =  new Lexeme(scanner.current(), Token.SEMICOLON);
                scanner.moveNext();
                return lexeme;

            case Scanner.EOF:
                lexeme =  new Lexeme(scanner.current(), Token.EOF);
                scanner.moveNext();
                return lexeme;

            case Scanner.NULL:
                lexeme =  new Lexeme(scanner.current(), Token.NULL);
                scanner.moveNext();
                return lexeme;

            default:
                if (Character.isLetter(scanner.current()) && scanner.current() >= 'a' && scanner.current() <= 'z') {
                    StringBuilder stringBuilder = new StringBuilder();

                    while (Character.isLetter(scanner.current())) {
                        stringBuilder.append(scanner.current());
                        scanner.moveNext();
                    }
                    return new Lexeme(stringBuilder.toString(), Token.IDENT);
                }
                else if (Character.isDigit(scanner.current())) {
                    StringBuilder digitBuilder = new StringBuilder();

                    while (Character.isDigit(scanner.current())) {
                        digitBuilder.append(Double.parseDouble(java.lang.String.valueOf(scanner.current())));
                        scanner.moveNext();
                    }
                    return new Lexeme(digitBuilder.toString(), Token.INT_LIT);
                } else {
                    throw new TokenizerException("Unknown token " + scanner.current());
                }
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
        current = next;
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
