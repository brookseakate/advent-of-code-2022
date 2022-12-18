package main.kotlin.com.aoc2022.day6

import main.kotlin.com.aoc2022.util.Utils.Companion.readFileAsMutableList

class Day6 {
  companion object {
    fun main() {
      println("hello Day 6")
      val signal = parseInput()

      println(findMarkerIndex(signal))
//      println(findMarkerIndex("mjqjpqmgbljsphdztnvjfqwrcgsmlb"))
//      println("should be 7")
//      println(findMarkerIndex("bvwbjplbgvbhsrlpgdmjqwftvncz"))
//      println("should be 5")
//      println(findMarkerIndex("nppdvjthqldpwncqszvftbrmjlhg"))
//      println("should be 6")
//      println(findMarkerIndex("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"))
//      println("should be 10")
//      println(findMarkerIndex("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"))
//      println("should be 11")
      println(findMessageIndex(signal))
      println(findMessageIndex("mjqjpqmgbljsphdztnvjfqwrcgsmlb"))
      println("should be 19")
      println(findMessageIndex("bvwbjplbgvbhsrlpgdmjqwftvncz"))
      println("should be 23")
      println(findMessageIndex("nppdvjthqldpwncqszvftbrmjlhg"))
      println("should be 23")
      println(findMessageIndex("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"))
      println("should be 29")
      println(findMessageIndex("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"))
      println("should be 26")

    }

    private fun parseInput(): String {
      return readFileAsMutableList("day6/Input").first()
    }

    private fun findIndexForDistinctSize(
      signal: String,
      size: Int
    ): Int {
      var markerIndex = 0

      for (i in size-1 until signal.length) {
        if (signal.slice(i-(size-1)..i).toSet().size < size) {
          continue
        }
        markerIndex = i+1
        break
      }

      return markerIndex
    }

    private fun findMarkerIndex(signal: String): Int {
      return findIndexForDistinctSize(
        signal = signal,
        size = 4,
      )
    }

    private fun findMessageIndex(signal: String): Int {
      return findIndexForDistinctSize(
        signal = signal,
        size = 14,
      )
    }
  }
}