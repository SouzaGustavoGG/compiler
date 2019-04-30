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
  int VERIFY = 31;
  /** RegularExpression Id. */
  int LITERAL_CONST = 32;
  /** RegularExpression Id. */
  int OPEN_KEY = 33;
  /** RegularExpression Id. */
  int CLOSE_KEY = 34;
  /** RegularExpression Id. */
  int DOT = 35;
  /** RegularExpression Id. */
  int COMMA = 36;
  /** RegularExpression Id. */
  int PLUS = 37;
  /** RegularExpression Id. */
  int MINUS = 38;
  /** RegularExpression Id. */
  int MULTIPLY = 39;
  /** RegularExpression Id. */
  int DIVIDER = 40;
  /** RegularExpression Id. */
  int PERCENT = 41;
  /** RegularExpression Id. */
  int MOD = 42;
  /** RegularExpression Id. */
  int POW = 43;
  /** RegularExpression Id. */
  int EQUALS = 44;
  /** RegularExpression Id. */
  int EXCLAMATION = 45;
  /** RegularExpression Id. */
  int LESS = 46;
  /** RegularExpression Id. */
  int LESS_EQUAL = 47;
  /** RegularExpression Id. */
  int GREATER = 48;
  /** RegularExpression Id. */
  int GREATER_EQUAL = 49;
  /** RegularExpression Id. */
  int DIFFERENT = 50;
  /** RegularExpression Id. */
  int AMPERSAND = 51;
  /** RegularExpression Id. */
  int PIPE = 52;
  /** RegularExpression Id. */
  int OPEN_PARENTHESIS = 53;
  /** RegularExpression Id. */
  int CLOSE_PARENTHESIS = 54;
  /** RegularExpression Id. */
  int DIGIT = 55;
  /** RegularExpression Id. */
  int DECIMAL_NUMBER = 56;
  /** RegularExpression Id. */
  int NATURAL_NUMBER = 57;
  /** RegularExpression Id. */
  int REAL_NUMBER = 58;
  /** RegularExpression Id. */
  int LETTER = 59;
  /** RegularExpression Id. */
  int UNDER = 60;
  /** RegularExpression Id. */
  int IDENTIFIER = 61;
  /** RegularExpression Id. */
  int INVALID_SYMBOLS = 62;
  /** RegularExpression Id. */
  int INVALID_NATURAL_NUMBER = 63;
  /** RegularExpression Id. */
  int INVALID_REAL_NUMBER = 64;
  /** RegularExpression Id. */
  int INVALID_LITERAL_CONST = 65;
  /** RegularExpression Id. */
  int INVALID_IDENTIFIER = 66;

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
    "\"--:\"",
    "<token of kind 8>",
    "\":-\"",
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
    "\"verify\"",
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
    "<DECIMAL_NUMBER>",
    "<NATURAL_NUMBER>",
    "<REAL_NUMBER>",
    "<LETTER>",
    "\"_\"",
    "<IDENTIFIER>",
    "<INVALID_SYMBOLS>",
    "<INVALID_NATURAL_NUMBER>",
    "<INVALID_REAL_NUMBER>",
    "<INVALID_LITERAL_CONST>",
    "<INVALID_IDENTIFIER>",
  };

}
