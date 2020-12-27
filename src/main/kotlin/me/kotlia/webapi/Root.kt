package me.kotlia.webapi

import express.http.request.Request
import express.http.response.Response
import me.kotlia.webapi.WebAPI.Companion.app

object Root : WebAPIBase {
    override fun init() {
        app.get("/") { _: Request?, res: Response ->
            res.send("Test")
        }
    }
}