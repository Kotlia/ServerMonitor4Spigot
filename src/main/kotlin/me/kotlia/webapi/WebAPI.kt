package me.kotlia.webapi

import express.Express
import express.middleware.CorsOptions
import express.middleware.Middleware

class WebAPI(port: Int) {
    init {
        app = Express()
        val option = CorsOptions()
        option.headers = arrayOf(
            "Access-Control-Allow-Origin", "*",
            "Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept"
        )
        app.use(Middleware.cors(option))
        app.listen(port)
        listOf(Root, Sysinfo, Server).forEach { it.init() }
    }

    companion object {
        lateinit var app: Express
    }
}