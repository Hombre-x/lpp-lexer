package com.graphene
package core.position

import domain.program.Position
import domain.token.Lexeme


object Trackers:

  def track(regexed: Lexeme): Position =
    regexed.foldLeft(Position(0,0)): (position, current) =>
      current match
        case '\n' => Position(position.line + 1, 1)
        case _    => Position(position.line, position.column + 1)
      
  
 
end Trackers

