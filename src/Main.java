import java.io.FileReader;

import lexico.InterfazGrafica;
import lexico.Lexico;

public class Main {
    public static void main(String[] args) {
        try {
            FileReader f = new FileReader("prueba.txt");
            Lexico Lexer = new Lexico(f);
            InterfazGrafica pantalla = new InterfazGrafica();
            Lexer.next_token();
        } catch (Exception ex) {
            System.out.println("No se encontró el archivo");
        }
    }
}