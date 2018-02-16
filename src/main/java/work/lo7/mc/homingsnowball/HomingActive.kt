package work.lo7.mc.homingsnowball

import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Projectile
import org.bukkit.entity.Snowball

fun HomingActive(snowball: Projectile?, homingSnowball: HomingSnowball) {
    var minAngle = 6.283185307179586
    var minEntity: Entity? = null
    if (snowball != null) {
        for (entity in snowball.getNearbyEntities(64.0,64.0,64.0)){
            if(entity is LivingEntity && !entity.isDead){
                val toTarget = entity.location.toVector().clone().subtract(snowball.location.toVector())
                val angle = snowball.velocity.angle(toTarget).toDouble()
                if (angle < minAngle){
                    minAngle = angle
                    minEntity = entity
                }
            }
        }
    }
    if (minEntity != null){
        HomingTask(snowball as Snowball, minEntity as LivingEntity, homingSnowball)
    }
}