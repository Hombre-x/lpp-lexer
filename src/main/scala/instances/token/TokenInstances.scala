package com.graphene
package instances.token

import cats.Show
import domain.token.*

given TokenShow: Show[TokenPos] = Show.show:
  case TokenPos(Token(name, lexeme), position) =>
    name match
      case NameToken(nameTkn)    => 
        s"<$nameTkn,$lexeme,${position.line},${position.column}>"
        
      case KeywordToken(nameTkn) => 
        s"<$nameTkn,${position.line},${position.column}>"

      case IdentifierToken       =>
        s"<id,$lexeme,${position.line},${position.column}>"

      case StringToken           =>
        s"<tkn_str,${lexeme.replaceAll("^\"|\"$", "")},${position.line},${position.column}>"

      case CharToken             =>
        s"<tkn_char,${lexeme.replaceAll("^\'|\'$", "")},${position.line},${position.column}>"
        
      case ErrorToken            => 
        s">>> Error lexico (linea: ${position.line}, posicion: ${position.column})"
        
      case _ => 
        s"<$name,$lexeme,${position.line},${position.column}>"
  