package me.zeph.spirits.listeners;

import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetEvent.TargetReason;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;

import me.zeph.spirits.Methods.Spirit;
import me.zeph.spirits.SpiritElement;
import me.zeph.spirits.Spirits;

public class Passives implements Listener {

    @EventHandler
    public void onFallDamage(EntityDamageEvent event) {

        if (event.getEntity() instanceof Player) {
            Element spirit = Element.getElement("Spirit");
            Player player = (Player) event.getEntity();
            BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);

            if (event.isCancelled() || bPlayer == null || bPlayer.hasElement(Element.AIR) || bPlayer.hasElement(Element.EARTH)) {
                return;

            } else if (bPlayer.hasElement(spirit) && event.getCause() == DamageCause.FALL) {
                event.setDamage(0D);
                event.setCancelled(true);

            }
        }
    }
    
    @EventHandler
    public void darkMobTarget(EntityTargetEvent event) {
    	if (! (event.getTarget() instanceof Player) || !(event.getReason() == TargetReason.CLOSEST_PLAYER)) {
    		return;
    	}
    	Player player = (Player) event.getTarget();
        BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
        if (bPlayer.hasElement(SpiritElement.DARK)) {
        	event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onFire(EntityDamageEvent event) {
    	if (! (event.getEntity() instanceof Player) || !(event.getCause().equals(DamageCause.FIRE))) {
    		return;
    	}
    	Player player = (Player) event.getEntity();
        BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
        if (bPlayer.hasElement(SpiritElement.DARK)) {
        	event.setCancelled(true);
    }
    }
}