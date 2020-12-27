package me.kotlia.webapi

import com.google.gson.JsonObject
import express.http.request.Request
import express.http.response.Response
import me.kotlia.util.Shell
import me.kotlia.webapi.WebAPI.Companion.app

object Sysinfo : WebAPIBase {
    override fun init() {
        app.get("/sysinfo") { _: Request?, res: Response ->
            val raw = Shell.exec("free")
            val args = raw.toLowerCase()
                .replace(Regex("[a-zA-Z]+"), "")
                .replace("/", "")
                .replace("\n", "")
                .replace(":", "")
                .split(' ')
                .toMutableList()
            args.removeIf { it == "" }
            val nargs = args.map { it.toDouble() }
            val sysinfo = JsonObject()
            sysinfo.addProperty("usedMemP", nargs[1]/nargs[0] * 100)
            sysinfo.addProperty("usedSwapP", nargs[7]/nargs[6] * 100)
            res.send(sysinfo.toString())
        }
    }
}