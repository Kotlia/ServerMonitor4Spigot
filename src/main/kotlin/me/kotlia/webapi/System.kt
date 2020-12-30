package me.kotlia.webapi

import express.http.request.Request
import express.http.response.Response
import me.kotlia.ServerMonitor
import me.kotlia.webapi.WebAPI.Companion.app
import java.net.InetAddress

object System : WebAPIBase {
    override fun init() {
        app.post("/system/exec/:cmd") { req: Request?, res: Response ->
            println(req?.ip)
            println(InetAddress.getLocalHost().hostAddress)
            res.send("hello!")
        }
    }
}