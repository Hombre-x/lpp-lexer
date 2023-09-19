package com.graphene
package domain.regex

import scala.util.matching.Regex

import domain.token.Name

type RegexPair = (Regex, Name)
type RegexList = List[RegexPair]
