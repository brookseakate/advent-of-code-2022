package main.kotlin.com.aoc2022.day5

import com.aoc2022.util.Utils.Companion.readFileAsMutableList

data class MoveInstruction(
  val quantity: Int,
  val from: Int,
  val to: Int,
)

class Day5 {
  companion object {
    private val stacks = mutableMapOf<Int, MutableList<String>>()
    private lateinit var moves: List<MoveInstruction>

    fun main() {
      println("hello Day 5")

      parseInput()
    }

    private fun parseInput() {
      val inputLines = readFileAsMutableList("day5/SampleInput")

      // partition the input
      val stackIdRowIndex = inputLines
        .indexOfFirst {
          it[0] == ' ' &&
            it.all { substring ->
              substring.toString().matches(
                Regex
                  ("[\\d\\s]")
              )
            }
        }

      val stackLines = inputLines.slice(0 until stackIdRowIndex)

      val stackIdLine = inputLines[stackIdRowIndex]

      val instructionLines = inputLines.slice(stackIdRowIndex + 2..inputLines.lastIndex)

      // parse the stacks
      for (char in stackIdLine.withIndex()) {
        if (char.value != ' ') {
          val crateStack = stackLines
            .reversed()
            .mapNotNull {
              try {
                it[char.index].toString()
              } catch (e: IndexOutOfBoundsException) {
                null
              }
            }
            .filterNot { it == " " }
            .toMutableList()

          stacks[char.value.code] = crateStack
        }
      }

      // parse the instructions
      moves = instructionLines
        .map { line ->
          val digits = line.split("").filter {
            it.matches(Regex("\\d"))
          }
          MoveInstruction(
            quantity = digits[0].toInt(),
            from = digits[1].toInt(),
            to = digits[2].toInt(),
          )
        }
      // TODO: remove
//      for (inputLine in inputLines) {
//        while (inputLine[0] != 'm') {
//          // TODO: add validations on expectations for characters in stack diagram
//          for (char in inputLine) {
//
//          }
//
//        }
//      }
    }
  }
}