/* Generated By:JavaCC: Do not edit this line. ParserConstants.java */
package compilador.lexico;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface ParserConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int PROGRAM = 12;
  /** RegularExpression Id. */
  int DEFINE = 13;
  /** RegularExpression Id. */
  int NOT = 14;
  /** RegularExpression Id. */
  int VARIABLE = 15;
  /** RegularExpression Id. */
  int IS = 16;
  /** RegularExpression Id. */
  int NATURAL = 17;
  /** RegularExpression Id. */
  int REAL = 18;
  /** RegularExpression Id. */
  int CHAR = 19;
  /** RegularExpression Id. */
  int BOOLEAN = 20;
  /** RegularExpression Id. */
  int EXECUTE = 21;
  /** RegularExpression Id. */
  int SET = 22;
  /** RegularExpression Id. */
  int TO = 23;
  /** RegularExpression Id. */
  int GET = 24;
  /** RegularExpression Id. */
  int PUT = 25;
  /** RegularExpression Id. */
  int LOOP = 26;
  /** RegularExpression Id. */
  int WHILE = 27;
  /** RegularExpression Id. */
  int TRUE = 28;
  /** RegularExpression Id. */
  int FALSE = 29;
  /** RegularExpression Id. */
  int DO = 30;
  /** RegularExpression Id. */
  int LITERAL_CONST = 31;
  /** RegularExpression Id. */
  int OPEN_KEY = 32;
  /** RegularExpression Id. */
  int CLOSE_KEY = 33;
  /** RegularExpression Id. */
  int DOT = 34;
  /** RegularExpression Id. */
  int COMMA = 35;
  /** RegularExpression Id. */
  int PLUS = 36;
  /** RegularExpression Id. */
  int MINUS = 37;
  /** RegularExpression Id. */
  int MULTIPLY = 38;
  /** RegularExpression Id. */
  int DIVIDER = 39;
  /** RegularExpression Id. */
  int PERCENT = 40;
  /** RegularExpression Id. */
  int MOD = 41;
  /** RegularExpression Id. */
  int POW = 42;
  /** RegularExpression Id. */
  int EQUALS = 43;
  /** RegularExpression Id. */
  int EXCLAMATION = 44;
  /** RegularExpression Id. */
  int LESS = 45;
  /** RegularExpression Id. */
  int LESS_EQUAL = 46;
  /** RegularExpression Id. */
  int GREATER = 47;
  /** RegularExpression Id. */
  int GREATER_EQUAL = 48;
  /** RegularExpression Id. */
  int DIFFERENT = 49;
  /** RegularExpression Id. */
  int AMPERSAND = 50;
  /** RegularExpression Id. */
  int PIPE = 51;
  /** RegularExpression Id. */
  int OPEN_PARENTHESIS = 52;
  /** RegularExpression Id. */
  int CLOSE_PARENTHESIS = 53;
  /** RegularExpression Id. */
  int DIGIT = 54;
  /** RegularExpression Id. */
  int DECIMAL_CONST = 55;
  /** RegularExpression Id. */
  int INTEGER_CONST = 56;
  /** RegularExpression Id. */
  int REAL_CONST = 57;
  /** RegularExpression Id. */
  int IDENTIFICATOR = 58;
  /** RegularExpression Id. */
  int SP = 59;
  /** RegularExpression Id. */
  int LETTER = 60;
  /** RegularExpression Id. */
  int UPL = 61;

  /** Lexical state. */
  int DEFAULT = 0;
  /** Lexical state. */
  int multilinecomment = 1;
  /** Lexical state. */
  int singlelinecomment = 2;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\t\"",
    "\"\\n\"",
    "\"\\r\"",
    "\"\\f\"",
    "\":--\"",
    "\":-\"",
    "\"--:\"",
    "<token of kind 9>",
    "<token of kind 10>",
    "<token of kind 11>",
    "\"program\"",
    "\"define\"",
    "\"not\"",
    "\"variable\"",
    "\"is\"",
    "\"natural\"",
    "\"real\"",
    "\"char\"",
    "\"boolean\"",
    "\"execute\"",
    "\"set\"",
    "\"to\"",
    "\"get\"",
    "\"put\"",
    "\"loop\"",
    "\"while\"",
    "\"true\"",
    "\"false\"",
    "\"do\"",
    "<LITERAL_CONST>",
    "\"{\"",
    "\"}\"",
    "\".\"",
    "\",\"",
    "\"+\"",
    "\"-\"",
    "\"*\"",
    "\"/\"",
    "\"%\"",
    "\"%%\"",
    "\"**\"",
    "\"==\"",
    "\"!\"",
    "\"<\"",
    "\"<=\"",
    "\">\"",
    "\">=\"",
    "\"!=\"",
    "\"&\"",
    "\"|\"",
    "\"(\"",
    "\")\"",
    "<DIGIT>",
    "<DECIMAL_CONST>",
    "<INTEGER_CONST>",
    "<REAL_CONST>",
    "<IDENTIFICATOR>",
    "\"_\"",
    "<LETTER>",
    "<UPL>",
  };

}
