package main.kotlin.com.aoc2022.day1

import main.kotlin.com.aoc2022.util.Utils.Companion.readFileAsMutableList

class Day1 {
  companion object {
    fun main() {
      println("hello Day 1")

      println(findMaxCalories())
      println(findTopNCalories(3).sum())
    }

    private fun findMaxCalories(): Int {
      val lines = readFileAsMutableList("day1/Input")

      var maxCals = 0
      var currentCals = 0

      for (line in lines) {
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

    private fun findTopNCalories(n: Int): List<Int> {
      val lines = readFileAsMutableList("day1/Input")

      var maxCalList = MutableList(size = n, init = { 0 })
      var currentCals = 0

      for (line in lines) {
        if (line.isNotEmpty()) {
          currentCals += line.toInt()
          continue
        }

        maxCalList = updateMaxList(
          currentCals = currentCals,
          maxCalList = maxCalList,
          n = n,
        )

        currentCals = 0
      }

      // Consider last line too
      return updateMaxList(
        currentCals = currentCals,
        maxCalList = maxCalList,
        n = n,
      )
    }

    private fun updateMaxList(
      currentCals: Int,
      maxCalList: MutableList<Int>,
      n: Int,
    ): MutableList<Int> {
      return if (currentCals > maxCalList.first()) {
        val tempList = maxCalList.plus(currentCals)
        tempList.sorted().subList(1, n + 1).toMutableList()
      } else {
        maxCalList
      }
    }
  }
}