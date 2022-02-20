package lexproject;
// Brandon Lara
// Ethan Riley


import java.io.File;  
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Lexer {
    static String lexeme;
    
    static TreeMap<String, String> tree_map_L = new TreeMap<String, String>();
    static TreeMap<String, String> tree_map_ID = new TreeMap<String, String>();

    static boolean symbolResolved;
    static int integerRepresentation;
    static char nextChar;

    static void Tokenize(String fileName) throws IOException {
        fillL();
        fill_ID();
        File f = new File(fileName + ".txt");
        FileReader freader = new FileReader(f);
        BufferedReader brf = new BufferedReader(freader);

        lexeme = "";
        start(brf);
    }

    // Reads a character and then assess which function to call.
    // Functions called are responsible for reading in a new character.
    // This could be mitigated with different file read implementations.
    static void start(BufferedReader brf) throws IOException {
        integerRepresentation = brf.read();
        while(integerRepresentation != -1) {
            nextChar = (char) integerRepresentation;
            if (Character.isLetter(nextChar)) {
                symbolResolved = false;
                id(brf);
            } else if (Character.isDigit(nextChar)) {
                symbolResolved = false;
                num(brf);
            } else if (nextChar == ' '){
                integerRepresentation = brf.read();
            } else {
                symbolResolved = false;
                unk(brf);
            }
        }
            
    }

    // ID state in the finite state machine from the slides.
    // Function is called if start reads a letter.
    static void id(BufferedReader brf) throws IOException {
        while(integerRepresentation != -1) {
            nextChar = (char) integerRepresentation;
            if (Character.isLetter(nextChar) || Character.isDigit(nextChar)) {
                lexeme += nextChar;
            } else {
                if (tree_map_L.containsKey(lexeme)) {
                    System.out.println(tree_map_L.get(lexeme));
                    symbolResolved = true;
                } else {
                    System.out.println("IDENT");
                    symbolResolved = true;
                }
            }
            if (symbolResolved) {
                lexeme = "";
                break;
            } else {
                integerRepresentation = brf.read();
            }
            
        }
    }

    // ID state in the finite state machine from the slides.
    // Function is called if start reads a number.
    static void num(BufferedReader brf) throws IOException {

        while(integerRepresentation != -1) {
            nextChar = (char) integerRepresentation;
            if (Character.isDigit(nextChar)) {
                lexeme += nextChar;
            } else if (Character.isLetter(nextChar)) {
                System.out.println("ERROR: Invalid Identifier.");
                System.exit(1);
            } else {
                System.out.println("INT_LIT");
                symbolResolved = true;
            }

            if (symbolResolved) {
                lexeme = "";
                break;
            } else {
                integerRepresentation = brf.read();
            }
        }
    }

    // ID state in the finite state machine from the slides.
    // Function is called in if start did not read a letter, a number, or ' '.
    static void unk(BufferedReader brf) throws IOException {
        // lexeme holds the current character
        lexeme += nextChar;
        // tryForDoubleSymbolLexeme holds the current character 
        // plus the next character
        String tryForDoubleSymbolLexeme = "" + nextChar;

        integerRepresentation = brf.read();

        // This error is wrong.
        // in the case of 'int x = 0;'
        // ; is the last character
        // meaning that reading the character after ;
        // would result in integerRepresentation == -1
        // Must check for this case then decide if error or not.
        if (integerRepresentation == -1) {
            System.out.println("NOT ERROR: .");
            System.exit(1);
        }
        nextChar = (char) integerRepresentation;
        tryForDoubleSymbolLexeme += nextChar;
        if (tree_map_ID.containsKey(tryForDoubleSymbolLexeme)) {
            symbolResolved = true;
            integerRepresentation = brf.read();
            System.out.println(tree_map_ID.get(tryForDoubleSymbolLexeme));
        } else {
            if (tree_map_ID.containsKey(lexeme)) {
                symbolResolved = true;
                System.out.println(tree_map_ID.get(lexeme));
            }
        }
        if (symbolResolved) {
            lexeme = "";
        } else {
            System.out.println("ERROR: .");
            System.exit(1);
        }

    }
    
    static void fillL() {
        tree_map_L.put("if", "IF");
        tree_map_L.put("for", "FOR");
        tree_map_L.put("while", "WHILE");
        tree_map_L.put("function", "FUNCTION");
        tree_map_L.put("return", "RETURN");
        tree_map_L.put("int", "INT");
        tree_map_L.put("else", "ELSE");
        tree_map_L.put("do", "DO");
        tree_map_L.put("break", "BREAK");
        tree_map_L.put("end", "END");
    }

    static void fill_ID() {
        tree_map_ID.put("=", "ASSIGN");
        tree_map_ID.put("+", "ADD");
        tree_map_ID.put("-", "SUB");
        tree_map_ID.put("*", "MUL");
        tree_map_ID.put("/", "DIV");
        tree_map_ID.put("%", "MOD");
        tree_map_ID.put(">", "GT");
        tree_map_ID.put("<", "LT");
        tree_map_ID.put(">=", "GE");
        tree_map_ID.put("<=", "LE");
        tree_map_ID.put("++", "INC");
        tree_map_ID.put("(", "LP");
        tree_map_ID.put(")", "RP");
        tree_map_ID.put("{", "LB");
        tree_map_ID.put("}", "RB");
        tree_map_ID.put("|", "OR");
        tree_map_ID.put("&", "AND");
        tree_map_ID.put("==", "EE");
        tree_map_ID.put("!", "NEG");
        tree_map_ID.put(",", "COMMA");
        tree_map_ID.put(";", "SEMI");  
    }


}
