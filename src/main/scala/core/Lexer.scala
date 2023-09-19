package com.graphene
package core

import monocle.syntax.all.*

import domain.token.*
import domain.program.{Position, Program}

import matching.Matchers.*
import position.Trackers.*

import scala.annotation.tailrec

object Lexer:
  
  // (Program, Position) => (Token, Program, Position)
  def tokenize(program: String, position: Position): (TokenPos, String, Position) =
  
    val (remaining, regexed): (Program, Token) = rex(program)
    val tracked: Position = position add track(regexed.lexeme)

    (
      TokenPos(regexed, position),
      remaining,
      tracked
    )
    
  end tokenize
  
  def lexify(program: Program): List[TokenPos] =
    
    @tailrec
    def tailLexify(program: Program, currentPos: Position, accum: List[TokenPos]): List[TokenPos] =
      if program.isEmpty then accum
      else
        val (token, remaining, position) = tokenize(program, currentPos)
        token.token.name match
          case NameToken(_) => tailLexify(remaining, position, accum :+ token)
          case KeywordToken(_) => tailLexify(remaining, position, accum :+ token)
          case StringToken | CharToken => tailLexify(remaining, position, accum :+ token)
//          case CharToken => tailLexify(remaining, position, accum :+ token)
          case IdentifierToken => 
            tailLexify(remaining, position, accum :+ token.focus(_.token.lexeme).modify(_.toLowerCase))
          case ErrorToken => accum :+ token
          case _ => tailLexify(remaining, position, accum)
  
    tailLexify(program, Position(1, 1), List.empty)
  
  end lexify
  
  
end Lexer
