package me.kotlia.webapi

import com.google.gson.JsonObject
import express.http.request.Request
import express.http.response.Response
import me.kotlia.ServerMonitor

object Server : WebAPIBase {
    override fun init() {
        WebAPI.app.get("/server/plugins") { _: Request?, res: Response ->
            val plugins = JsonObject()
            val pluginData = JsonObject()
            ServerMonitor.instance.server.pluginManager.plugins.forEach {
                val detail = pluginData.apply {
                    addProperty("version", it.description.version)
                    addProperty("isEnabled", it.isEnabled)
                }
                plugins.addProperty(it.name, detail.toString())
            }
            res.send(plugins.toString())
        }
    }
}
