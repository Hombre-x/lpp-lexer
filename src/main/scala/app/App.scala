package com.graphene
package app

import cats.syntax.show.*

import core.Lexer
import instances.token.TokenShow

import scala.jdk.CollectionConverters.*

object App:
  def lexifyToJava(program: String): java.util.List[String] = 
     Lexer.lexify(program).map(_.show).asJava
