package me.kotlia

import org.bukkit.plugin.java.JavaPlugin
import me.kotlia.webapi.WebAPI

class ServerMonitor : JavaPlugin() {

    override fun onEnable() {
        logger.info("Loading ServerMonitor...")
        instance = this
        WebAPI(4000)
    }

    override fun onDisable() {
        WebAPI.app.stop()
    }

    companion object {
        lateinit var instance: JavaPlugin
    }
}