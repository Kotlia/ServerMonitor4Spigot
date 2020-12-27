package me.kotlia.util

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

object Shell {
    fun exec(cmd: String): String {
        val process = ProcessBuilder(cmd).start()
        val isr = InputStreamReader(process.inputStream, "UTF-8")
        val reader = BufferedReader(isr)
        val builder = StringBuilder()
        var c: Int
        while (reader.read().also { c = it } != -1) {
            builder.append(c.toChar())
        }
        return builder.toString()
    }
}