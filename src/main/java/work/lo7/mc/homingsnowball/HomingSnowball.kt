package work.lo7.mc.homingsnowball

import org.bukkit.entity.*
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ProjectileLaunchEvent
import org.bukkit.plugin.java.JavaPlugin

class HomingSnowball : JavaPlugin() ,Listener{

    override fun onEnable() {
        // Plugin startup logic
        logger.info("Plugin Enabled")
        server.pluginManager.registerEvents(this,this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
        logger.info("Plugin Disabled")
    }

    @EventHandler fun onSnowballLaunchEvent(e : ProjectileLaunchEvent){
        if (e.entity is Snowball){
            val snowball = e.entity
            HomingActive(snowball,this)
        }
    }
}