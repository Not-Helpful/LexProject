// Brandon Lara
// Ethan Riley


import java.io.File;  
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

public class Lexer {
    static String lexeme;
    
    TreeMap<String, String> tree_map_L = new TreeMap<String, String>();
    TreeMap<String, String> tree_map_ID = new TreeMap<String, String>();

    static void Tokenize(String fileName) throws IOException {
        fillL();
        fill_ID();
        File f = new File(fileName + ".txt");
        FileReader freader = new FileReader(f);
        BufferedReader brf = new BufferedReader(freader);
        start(brf);
    }

    static void start(BufferedReader brf) throws IOException {
        lexeme = "";
        int integerRepresentation = brf.read();
        while(integerRepresentation != -1) {
            char nextChar = (char) integerRepresentation;
            if (Character.isLetter(nextChar)) {
                lexeme += nextChar;
                id(brf);
            }else if (Character.isDigit(nextChar)) {
                lexeme += nextChar;
                num(brf);
            }else if (nextChar == ' '){
                // Do nothing
            }else {
                lexeme += nextChar;
                unk(brf);
            }
            integerRepresentation = brf.read();
        }
            
    }

    static void id(BufferedReader brf) throws IOException {
        int integerRepresentation = brf.read();
        char nextChar = (char) integerRepresentation;
        while(integerRepresentation != -1) {
            if (Character.isLetter(nextChar) || Character.isDigit(nextChar)) {
                lexeme += nextChar;
            } else if() {
                
            } else {
                if () {
                    
                }
                System.out.println("IDENT");
                start(brf);
            }
            integerRepresentation = brf.read();
        }
    }

    static void num(BufferedReader brf) throws IOException {
        int integerRepresentation = brf.read();
        char nextChar = (char) integerRepresentation;
        while(integerRepresentation != -1) {
            if (Character.isDigit(nextChar)) {
                lexeme += nextChar;
            } else {
                System.out.println("INT_LIT");
                start(brf);
            }
            integerRepresentation = brf.read();
        }
    }

    static void unk(BufferedReader brf) throws IOException {
        int integerRepresentation = brf.read();
        char nextChar = (char) integerRepresentation;
        while(integerRepresentation != -1) {
            if (Character.isDigit(nextChar)) {
                lexeme += nextChar;
            } else {
                System.out.println("INT_LIT");
                start(brf);
            }
            integerRepresentation = brf.read();
        }
    }
    
    static void fillL(){
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
    static void fill_ID(){
        tree_map_L.put("=", "ASSIGN");
        tree_map_L.put("+", "ADD");
        tree_map_L.put("-", "SUB");
        tree_map_L.put("*", "MUL");
        tree_map_L.put("/", "DIV");
        tree_map_L.put("%", "MOD");
        tree_map_L.put(">", "GT");
        tree_map_L.put("<", "LT");
        tree_map_L.put(">=", "GE");
        tree_map_L.put("<=", "LE");
        
        tree_map_L.put("++", "INC");
        tree_map_L.put("(", "LP");
        tree_map_L.put(")", "RP");
        tree_map_L.put("{", "LB");
        tree_map_L.put("}", "RB");
        tree_map_L.put("|", "OR");
        tree_map_L.put("&", "AND");
        tree_map_L.put("==", "EE");
        tree_map_L.put("!", "NEG");
        tree_map_L.put(",", "COMMA");
        tree_map_L.put(";", "SEMI");
        
    }


}
