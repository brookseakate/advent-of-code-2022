package main.kotlin.com.aoc2022.day5

import main.kotlin.com.aoc2022.util.Utils.Companion.readFileAsMutableList

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
      rearrange9001()
      println(getTops().joinToString(""))
    }

    private fun parseInput() {
      val inputLines = readFileAsMutableList("day5/Input")

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

          stacks[char.value.toString().toInt()] = crateStack
        }
      }

      // parse the instructions
      moves = instructionLines
        .map { line ->
          val digits = line.split(" ").filter {
            it.matches(Regex("\\d+"))
          }
          MoveInstruction(
            quantity = digits[0].toInt(),
            from = digits[1].toInt(),
            to = digits[2].toInt(),
          )
        }
    }

    private fun rearrange9000() {
      for (move in moves) {
        for (i in 0 until move.quantity) {
          val crate = stacks[move.from]!!.removeLast()
          stacks[move.to]!!.add(crate)
        }
      }
    }

    private fun getTops(): List<String> {
      return stacks.map { it.value.last() }.toList()
    }

    private fun rearrange9001() {
      for (move in moves) {
        val (remainingFromStack, moveStack) = stacks[move.from]!!
          .withIndex()
          .partition { it.index < stacks[move.from]!!.size - move.quantity }

        stacks[move.from] = remainingFromStack.map { it.value }.toMutableList()
        stacks[move.to]!!.addAll(moveStack.map { it.value })
      }
    }
  }
}