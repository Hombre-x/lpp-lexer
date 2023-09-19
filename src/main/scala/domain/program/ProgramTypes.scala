package com.graphene
package domain.program

import cats.syntax.eq.*

type Program = String

case class Position(line: Int, column: Int):
  
  infix def add(newPos: Position): Position =
    if newPos.line === 0 then Position(line, column + newPos.column)
    else Position(line + newPos.line, newPos.column)

case class ProgramPos(program: Program, position: Position)


