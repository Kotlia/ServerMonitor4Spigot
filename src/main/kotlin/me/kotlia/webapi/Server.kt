package me.kotlia.webapi

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import express.http.request.Request
import express.http.response.Response
import me.kotlia.ServerMonitor

object Server : WebAPIBase {
    override fun init() {
        WebAPI.app.get("/server/plugins") { _: Request?, res: Response ->
            val jArray = JsonArray()
            ServerMonitor.instance.server.pluginManager.plugins.forEach {
                jArray.add(JsonObject().apply {
                    addProperty("name", it.name)
                    addProperty("version", it.description.version)
                    addProperty("isEnabled", it.isEnabled)
                })
            }
            res.send(jArray.toString())
        }
    }
}
