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
                // Do nothing
            } else {
                symbolResolved = false;
                unk(brf);
            }
            integerRepresentation = brf.read();
        }
            
    }

    static void id(BufferedReader brf) throws IOException {

        while(integerRepresentation != -1) {
            nextChar = (char) integerRepresentation;
            if (Character.isLetter(nextChar) || Character.isDigit(nextChar)) {
                lexeme += nextChar;
            } else {
                if (tree_map_L.containsKey(lexeme)) {
                    System.out.println(tree_map_L.get(lexeme));
                } else {
                    System.out.println("IDENT");
                    symbolResolved = true;
                }
            }
            if (symbolResolved) {
                lexeme = "";
                integerRepresentation = -1; 
            } else {
                integerRepresentation = brf.read();
                
            }
            
        }
    }

    static void num(BufferedReader brf) throws IOException {

        while(integerRepresentation != -1) {
            nextChar = (char) integerRepresentation;
            if (Character.isDigit(nextChar)) {
                lexeme += nextChar;
            } else {
                System.out.println("INT_LIT");
                symbolResolved = true;
            } 

            if (symbolResolved) {
                integerRepresentation = -1; 
                lexeme = "";
            } else {
                integerRepresentation = brf.read();
                
            }
        }
    }

    static void unk(BufferedReader brf) throws IOException {
        lexeme += nextChar;
        if (tree_map_ID.containsKey(lexeme)) {
            symbolResolved = true;
            System.out.println(tree_map_ID.get(lexeme));
        } else {
            integerRepresentation = brf.read();
            if (integerRepresentation == -1) {
                // ERROR STATE
            }
            nextChar = (char) integerRepresentation;
            lexeme += nextChar;
            if (tree_map_ID.containsKey(lexeme)) {
                symbolResolved = true;
                System.out.println(tree_map_ID.get(lexeme));
            }
        }
        if (symbolResolved) {
            integerRepresentation = -1; 
            lexeme = "";
        } else {
            // ERROR STATE
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
