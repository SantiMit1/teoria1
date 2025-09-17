package lexico;
import java_cup.runtime.*;
import java.util.*;

%%


%cup
%public
%class Lexico
%line
%column
%char




ESPACIO     = [ \t\f\n\r\n]+
LETRA       = [a-zA-Z]
DIGITO      = [0-9]
CONST_INT   = {DIGITO}+
CONST_FLOAT = ({DIGITO}+ "." {DIGITO}* | {DIGITO}* "." {DIGITO}+)
CONST_HEX   = "0"[hH]({DIGITO}|[a-fA-F])+
CONST_STRING = \"([^\"\n])*\"
COMMENT     = "\\$\\*"([^])*"\\*\\$"
ID          = {LETRA}({LETRA}|{DIGITO}|_)*

%%

<YYINITIAL> {

"INT"               {System.out.println("Token INT encontrado, Lexema " + yytext());}
"FLOAT"             {System.out.println("Token FLOAT encontrado, Lexema " + yytext());}

"REPEAT"            {System.out.println("Token REPEAT encontrado, Lexema " + yytext());}
"IF"                {System.out.println("Token IF encontrado, Lexema " + yytext());}
"THEN"              {System.out.println("Token THEN encontrado, Lexema " + yytext());}
"ELSE"              {System.out.println("Token ELSE encontrado, Lexema " + yytext());}
"DECVAR"            {System.out.println("Token DECVAR encontrado, Lexema " + yytext());}
"ENDDECVAR"         {System.out.println("Token ENDDECVAR encontrado, Lexema " + yytext());}
"ENDPROGRAM\.SECTION" {System.out.println("Token ENDPROGRAM_SECTION encontrado, Lexema " + yytext());}
"PROGRAM\.SECTION"    {System.out.println("Token PROGRAM_SECTION encontrado, Lexema " + yytext());}
"SHOW"              {System.out.println("Token SHOW encontrado, Lexema " + yytext());}
"STRING"            {System.out.println("Token STRING encontrado, Lexema " + yytext());}
"MAP"               {System.out.println("Token MAP encontrado, Lexema " + yytext());}

":="                {System.out.println("Token ASSIGN encontrado, Lexema " + yytext());}
"+"                 {System.out.println("Token PLUS encontrado, Lexema " + yytext());}
"-"                 {System.out.println("Token MINUS encontrado, Lexema " + yytext());}
"*"                 {System.out.println("Token MULT encontrado, Lexema " + yytext());}
"/"                 {System.out.println("Token DIV encontrado, Lexema " + yytext());}
"<"                 {System.out.println("Token LESS encontrado, Lexema " + yytext());}
"<="                {System.out.println("Token LESS_EQUAL encontrado, Lexema " + yytext());}
">"                 {System.out.println("Token GREATER encontrado, Lexema " + yytext());}
">="                {System.out.println("Token GREATER_EQUAL encontrado, Lexema " + yytext());}
"=="                {System.out.println("Token EQUAL encontrado, Lexema " + yytext());}
"!="                {System.out.println("Token NOT_EQUAL encontrado, Lexema " + yytext());}
"&&"                {System.out.println("Token AND encontrado, Lexema " + yytext());}
"||"                {System.out.println("Token OR encontrado, Lexema " + yytext());}

":"                 {System.out.println("Token COLON encontrado, Lexema " + yytext());}
";"                 {System.out.println("Token SEMICOLON encontrado, Lexema " + yytext());}
","                 {System.out.println("Token COMMA encontrado, Lexema " + yytext());}
"("                 {System.out.println("Token LPAREN encontrado, Lexema " + yytext());}
")"                 {System.out.println("Token RPAREN encontrado, Lexema " + yytext());}
"["                 {System.out.println("Token LBRACKET encontrado, Lexema " + yytext());}
"]"                 {System.out.println("Token RBRACKET encontrado, Lexema " + yytext());}
"{"                 {System.out.println("Token LBRACE encontrado, Lexema " + yytext());}
"}"                 {System.out.println("Token RBRACE encontrado, Lexema " + yytext());}

{ID}                {System.out.println("Token ID encontrado, Lexema " + yytext());}
{CONST_INT}         {
    try {
        long valor = Long.parseLong(yytext());
        if (valor < -32768 || valor > 32767) {
            throw new Error("CONST_INT fuera de rango 16 bits: " + yytext() + " en la linea " + yyline);
        }
        System.out.println("Token CONST_INT encontrado, Lexema " + yytext());
    } catch (NumberFormatException e) {
        throw new Error("CONST_INT inválido: " + yytext() + " en la linea " + yyline);
    }
}
{CONST_FLOAT}             {System.out.println("Token CONST_FLOAT encontrado, Lexema " + yytext());}
{CONST_HEX}               {System.out.println("Token HEX encontrado, Lexema " + yytext());}
{CONST_STRING}            {System.out.println("Token CONST_STRING encontrado, Lexema " + yytext());}

{ESPACIO}           {/* ignorar */}
{COMMENT}           {/* ignorar */}
}


[^]		{ throw new Error("Caracter no permitido: <" + yytext() + "> en la linea " + yyline); }






















