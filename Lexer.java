// Brandon Lara
// Ethan Riley


import java.io.File;  
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Lexer {
    static String lexeme;

    static void Tokenize(String fileName) throws IOException {
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


}
