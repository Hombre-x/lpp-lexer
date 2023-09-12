package com.graphene
package app

import core.cats.Lexer
import cats.effect.{IO, IOApp}

import domain.token.Token
import instances.token.given
import syntax.parser.parse
import parser.words.CharParsers.{word, sat}


object App extends IOApp.Simple:
  
  def run: IO[Unit] =
    
    for
      _   <- IO.println("Starting parsing")
      res <- IO.unit
      _   <- IO.println(res)
      
    yield ()
