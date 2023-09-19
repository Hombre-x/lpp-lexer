package com.graphene
package core.matching

import domain.program.Program
import domain.token.*

import core.matching.Regexers.dictionary

object Matchers:
  
  def matchFirstIn(input: String): Option[Token] =
    dictionary.collectFirst:
      case (regex, name) if regex.findFirstIn(input).isDefined =>
        regex.findFirstIn(input) match
          case Some(lexeme) => Token(name, lexeme)
            
  def rex(program: Program): (Program, Token) =
    val matched = matchFirstIn(program).getOrElse(Token(ErrorToken, "Token not found"))
    (program.drop(matched.lexeme.length), matched)
    

//    val matched = matchFirstIn(program) 

    
    
 
end Matchers
