package main.kotlin.com.aoc2022.util

import java.nio.file.Files
import java.nio.file.Paths

class Utils {
  companion object {
    fun readFileAsMutableList(relFilePath: String): MutableList<String> {
      return Files
        .readAllLines(
          Paths.get("./src/main/kotlin/com/aoc2022/$relFilePath")
        )
        .toMutableList()
    }
  }
}