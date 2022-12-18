package main.kotlin.com.aoc2022.day1

import main.kotlin.com.aoc2022.util.Utils.Companion.readFileAsStream

class Day1 {
  companion object {
    fun main() {
      println("hello Day 1")

      println(findMaxCalories())
    }

    private fun findMaxCalories(): Int {
      val lineStream = readFileAsStream("day1/Input")

      var maxCals = 0
      var currentCals = 0

      for (line in lineStream) {
        if (line.isNotEmpty()) {
          currentCals += line.toInt()
          continue
        }

        if (currentCals > maxCals) {
          maxCals = currentCals
        }

        currentCals = 0
      }

      return maxCals
    }
  }
}