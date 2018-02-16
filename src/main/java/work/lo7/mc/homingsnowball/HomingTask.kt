package work.lo7.mc.homingsnowball

import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Snowball
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.util.Vector

class HomingTask(private var snowball: Snowball, private var target: LivingEntity, plugin: Plugin) : BukkitRunnable() {
    init {
        runTaskTimer(plugin, 1L,1L)
    }

    override fun run(){
        val speed = this.snowball.velocity.length()
        if (this.snowball.isOnGround || this.snowball.isDead || this.target.isDead){
            cancel()
            return
        }
        val toTarget = this.target.location.clone().add(Vector(0.0,0.5,0.0)).subtract(this.snowball.location).toVector()

        val dirVelocity = this.snowball.velocity.clone().normalize()
        val dirToTarget = toTarget.clone().normalize()
        val angle = dirVelocity.angle(dirToTarget).toDouble()
        val newSpeed = 0.8 * speed + 0.15

        val newVelocity: Vector
        newVelocity = if (angle < 0.12) {
            dirVelocity.clone().multiply(newSpeed)
        } else {
            val newDir = dirVelocity.clone().multiply((angle - 0.12) / angle).add(dirToTarget.clone().multiply(0.12 / angle))
            newDir.normalize()
            newDir.clone().multiply(newSpeed)
        }
        this.snowball.velocity = newVelocity.add(Vector(0.0, 0.03, 0.0))
    }
}