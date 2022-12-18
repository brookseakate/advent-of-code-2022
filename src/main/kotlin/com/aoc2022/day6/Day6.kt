package main.kotlin.com.aoc2022.day6

import main.kotlin.com.aoc2022.util.Utils.Companion.readFileAsMutableList

class Day6 {
  companion object {
    fun main() {
      println("hello Day 6")
      val signal = parseInput()

      println(findMarkerIndex(signal))
    }

    private fun parseInput(): String {
      return readFileAsMutableList("day6/Input").first()
    }

    private fun findMarkerIndex(signal: String): Int {
      var markerIndex: Int = 0

      for (i in 3 until signal.length) {
        if (signal.slice(i-3..i).toSet().size < 4) {
          continue
        }
        markerIndex = i
      }

      return markerIndex
    }
  }
}