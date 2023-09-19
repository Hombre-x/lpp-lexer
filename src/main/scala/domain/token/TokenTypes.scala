package com.graphene
package domain.token

import domain.program.Position

type Lexeme   = String
//type Name     = String

sealed abstract class Name
case class  NameToken(value: String) extends Name 
case class  KeywordToken(value: String) extends Name
case object IdentifierToken extends Name
case object StringToken extends Name
case object CharToken extends Name
case object WhitespaceToken extends Name
case object CommentToken extends Name
case object ErrorToken extends Name


case class Token(name: Name, lexeme: Lexeme)
case class TokenPos(token: Token, position: Position)
