package main.kotlin.com.aoc2022.day4

import main.kotlin.com.aoc2022.util.Utils.Companion.readFileAsMutableList

class Day4 {
  companion object {
    private val assignments = mutableListOf<Pair<List<Int>,List<Int>>>()

    fun main() {
      println("hello Day 4")

      parseInput()
      println(findFullyContainingPairs().size)
      println(findOverlappingPairs().size)
    }

    private fun parseInput() {
      val lines = readFileAsMutableList("day4/Input")

      for (line in lines) {
        val splitStrings = line.split(",")

        val firstAssignmentRangeList = splitStrings[0].split("-")
        val firstAssignment = IntRange(firstAssignmentRangeList[0].toInt(), firstAssignmentRangeList[1].toInt())
          .toList()

        val secondAssignmentRangeList = splitStrings[1].split("-")
        val secondAssignment = IntRange(secondAssignmentRangeList[0].toInt(), secondAssignmentRangeList[1].toInt())
          .toList()

        assignments.add(Pair(firstAssignment, secondAssignment))
      }
    }

    private fun findFullyContainingPairs(): List<Pair<List<Int>, List<Int>>> {
      return assignments.filter {
        it.first.first() >= it.second.first() && it.first.last() <= it.second.last() ||
          it.first.first() <= it.second.first() && it.first.last() >= it.second.last()
      }
    }

    private fun findOverlappingPairs(): List<Pair<List<Int>, List<Int>>> {
      return assignments.filter {
        it.first.intersect(it.second.toSet()).isNotEmpty()
      }
    }
  }
}