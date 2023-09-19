package com.graphene
package core.matching

/**
 * Important: The order of the regexes matters,
 * if the order is changed the longest-chain rule could broke
 */
import domain.regex.{RegexList, RegexPair}
import domain.token.*
import java.util.regex.Pattern.quote


object Operators:
  val operators: List[RegexPair] =
    List(
      "<-" -> "tkn_assign",
      "<>" -> "tkn_neq",
      "<=" -> "tkn_leq",
      ">=" -> "tkn_geq",
      "<"  -> "tkn_less",
      ">"  -> "tkn_greater",
      "."  -> "tkn_period",
      ","  -> "tkn_comma",
      ":"  -> "tkn_colon",
      "]"  -> "tkn_closing_bra",
      "["  -> "tkn_opening_bra",
      ")"  -> "tkn_closing_par",
      "("  -> "tkn_opening_par",
      "+"  -> "tkn_plus",
      "-"  -> "tkn_minus",
      "*"  -> "tkn_times",
      "/"  -> "tkn_div",
      "^"  -> "tkn_power",
      "="  -> "tkn_equal"
    )
      .sortBy(_._1.length)(using Ordering.Int.reverse).map:
        case (operator, name) => s"^${quote(operator)}".r -> KeywordToken(name)
      
end Operators

object ReservedWords:
  val reservedWords: List[String] =
    List(
      "inicio",
      "escriba",
      "fin",
      "entero",
      "real",
      "booleano",
      "caracter",
      "cadena",
      "verdadero",
      "falso",
      "div",
      "mod",
      "lea",
      "llamar",
      "nueva_linea",
      "si",
      "entonces",
      "sino",
      "y",
      "o",
      "caso",
      "mientras",
      "haga",
      "repita",
      "hasta",
      "para",
      "procedimiento",
      "var",
      "retorne",
      "funcion",
      "registro",
      "arreglo",
      "de"
    ).sortBy(_.length)(using Ordering.Int.reverse)
    
  val reservedWordsRegex: List[RegexPair] =
      reservedWords.map(word => s"""^(?i)$word""".r -> KeywordToken(word))
    
end ReservedWords

object Regexers:
  
  import Operators.operators, ReservedWords.{reservedWords, reservedWordsRegex}
  
  private val whitespace: RegexPair = """^\s+""".r                 -> WhitespaceToken
  
  private val id: RegexPair      =
    val words = reservedWords.mkString("|")
    s"""^(?!\\b(?i)($words)\\b)[a-zA-Z_][\\w_]*""".r               -> IdentifierToken
  
  private val comment: RegexPair = """^((/\*[\s\S]*\*/)|//.*)""".r -> CommentToken
  
  private val integer: RegexPair = """^-?\d+""".r                  -> NameToken("tkn_integer")
  
  private val real: RegexPair    = """^-?\d+\.\d+""".r             -> NameToken("tkn_real")
  
  private val boolean: RegexPair = """^(?i)(verdadero|falso)""".r  -> NameToken("tkn_boolean")
  
  private val char: RegexPair    = """^('.')""".r                  -> CharToken
  
  private val string: RegexPair  = """^(".*?")""".r                 -> StringToken
  
  private val nonReservedWords: RegexList =
    // Longest to smallest
    List(whitespace, real, integer, boolean, char, string)

  //TODO: Maybe add operators to nonReservedWords
  val dictionary: RegexList =  comment :: operators ::: (id :: reservedWordsRegex) ::: nonReservedWords
 
end Regexers
