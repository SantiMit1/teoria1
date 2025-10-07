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

%{
    private int commentLevel = 0;

    private List<String> respuesta = new ArrayList<>();
    public List<String> getRespuesta() {
        return respuesta;
    }
%}

%state COMMENT

ESPACIO     = [ \t\f\n\r\n]+
LETRA       = [a-zA-Z]
DIGITO      = [0-9]
CONST_INT   = {DIGITO}+
CONST_FLOAT = ({DIGITO}+ "." {DIGITO}* | {DIGITO}* "." {DIGITO}+)
CONST_HEX   = "0"[hH]({DIGITO}|[a-fA-F])+
CONST_STRING = \"([^\"\n])*\"
ID          = {LETRA}({LETRA}|{DIGITO}|_)*

%%

<YYINITIAL> {

"INT"               {respuesta.add("Token INT encontrado, Lexema " + yytext());}
"FLOAT"             {respuesta.add("Token FLOAT encontrado, Lexema " + yytext());}

"REPEAT"            {respuesta.add("Token REPEAT encontrado, Lexema " + yytext());}
"IF"                {respuesta.add("Token IF encontrado, Lexema " + yytext());}
"THEN"              {respuesta.add("Token THEN encontrado, Lexema " + yytext());}
"ELSE"              {respuesta.add("Token ELSE encontrado, Lexema " + yytext());}
"DECVAR"            {respuesta.add("Token DECVAR encontrado, Lexema " + yytext());}
"ENDDECVAR"         {respuesta.add("Token ENDDECVAR encontrado, Lexema " + yytext());}
"ENDPROGRAM\.SECTION" {respuesta.add("Token ENDPROGRAM_SECTION encontrado, Lexema " + yytext());}
"PROGRAM\.SECTION"    {respuesta.add("Token PROGRAM_SECTION encontrado, Lexema " + yytext());}
"SHOW"              {respuesta.add("Token SHOW encontrado, Lexema " + yytext());}
"STRING"            {respuesta.add("Token STRING encontrado, Lexema " + yytext());}
"MAP"               {respuesta.add("Token MAP encontrado, Lexema " + yytext());}

":="                {respuesta.add("Token ASSIGN encontrado, Lexema " + yytext());}
"+"                 {respuesta.add("Token PLUS encontrado, Lexema " + yytext());}
"-"                 {respuesta.add("Token MINUS encontrado, Lexema " + yytext());}
"*"                 {respuesta.add("Token MULT encontrado, Lexema " + yytext());}
"/"                 {respuesta.add("Token DIV encontrado, Lexema " + yytext());}
"<"                 {respuesta.add("Token LESS encontrado, Lexema " + yytext());}
"<="                {respuesta.add("Token LESS_EQUAL encontrado, Lexema " + yytext());}
">"                 {respuesta.add("Token GREATER encontrado, Lexema " + yytext());}
">="                {respuesta.add("Token GREATER_EQUAL encontrado, Lexema " + yytext());}
"=="                {respuesta.add("Token EQUAL encontrado, Lexema " + yytext());}
"!="                {respuesta.add("Token NOT_EQUAL encontrado, Lexema " + yytext());}
"&&"                {respuesta.add("Token AND encontrado, Lexema " + yytext());}
"||"                {respuesta.add("Token OR encontrado, Lexema " + yytext());}

":"                 {respuesta.add("Token COLON encontrado, Lexema " + yytext());}
";"                 {respuesta.add("Token SEMICOLON encontrado, Lexema " + yytext());}
","                 {respuesta.add("Token COMMA encontrado, Lexema " + yytext());}
"("                 {respuesta.add("Token LPAREN encontrado, Lexema " + yytext());}
")"                 {respuesta.add("Token RPAREN encontrado, Lexema " + yytext());}
"["                 {respuesta.add("Token LBRACKET encontrado, Lexema " + yytext());}
"]"                 {respuesta.add("Token RBRACKET encontrado, Lexema " + yytext());}
"{"                 {respuesta.add("Token LBRACE encontrado, Lexema " + yytext());}
"}"                 {respuesta.add("Token RBRACE encontrado, Lexema " + yytext());}

"$*"                {commentLevel = 1; yybegin(COMMENT);}

{ID}                {respuesta.add("Token ID encontrado, Lexema " + yytext());}
{CONST_INT}         {
    try {
        long valor = Long.parseLong(yytext());
        if (valor < -32768 || valor > 32767) {
            respuesta.add("CONST_INT fuera de rango 16 bits: " + yytext() + " en la linea " + yyline);
        }
        respuesta.add("Token CONST_INT encontrado, Lexema " + yytext());
    } catch (NumberFormatException e) {
        respuesta.add("CONST_INT inválido: " + yytext() + " en la linea " + yyline);
    }
}
{CONST_FLOAT}             {respuesta.add("Token CONST_FLOAT encontrado, Lexema " + yytext());}
{CONST_HEX}               {respuesta.add("Token HEX encontrado, Lexema " + yytext());}
{CONST_STRING}            {respuesta.add("Token CONST_STRING encontrado, Lexema " + yytext());}

{ESPACIO}           {/* ignorar */}
}

<COMMENT> {
"$*"                {
                        commentLevel++;
                        if (commentLevel > 2) {
                            respuesta.add("Comentarios anidados exceden el máximo de 1 nivel en la línea " + yyline);
                        }
                    }

"*$"                {
                        commentLevel--;
                        if (commentLevel == 0) {
                            yybegin(YYINITIAL);
                        }
                    }

[^]                 {/* ignorar cualquier caracter dentro del comentario */}
}

[^]		{ respuesta.add("Caracter no permitido: <" + yytext() + "> en la linea " + yyline); }
