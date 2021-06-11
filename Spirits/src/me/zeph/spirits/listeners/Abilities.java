package me.zeph.spirits.listeners;

import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityCombustByBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import com.jedk1.jedcore.scoreboard.BendingBoard;
import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.Element.SubElement;
import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ability.CoreAbility;
import com.projectkorra.projectkorra.ability.util.MultiAbilityManager;
import com.projectkorra.projectkorra.util.TempBlock;

import me.zeph.spirits.SpiritElement;
import me.zeph.spirits.ability.dark.DarkTether;
import me.zeph.spirits.ability.dark.Exonerate;
import me.zeph.spirits.ability.dark.HallowedGround;
import me.zeph.spirits.ability.dark.Infiltrate;
import me.zeph.spirits.ability.dark.Reave;
import me.zeph.spirits.ability.dark.SoulPunch;

import me.zeph.spirits.ability.dark.Vaatu;

import me.zeph.spirits.ability.dark.vaatu.Corrupt;
import me.zeph.spirits.ability.dark.vaatu.DarkFlash;
import me.zeph.spirits.ability.light.Catalyse;
import me.zeph.spirits.ability.light.Eclipse;
import me.zeph.spirits.ability.light.Enthrall;
import me.zeph.spirits.ability.light.Halo;
import me.zeph.spirits.ability.light.Lull;
import me.zeph.spirits.ability.light.Raava;
import me.zeph.spirits.ability.light.combo.Enchant;
import me.zeph.spirits.ability.light.multiability.FloatMove;
import me.zeph.spirits.ability.light.multiability.Heal;
import me.zeph.spirits.ability.light.multiability.Invis;
import me.zeph.spirits.ability.light.multiability.Resistance;
import me.zeph.spirits.ability.light.multiability.Speed;
import me.zeph.spirits.ability.light.raava.Ascend;
import me.zeph.spirits.ability.light.raava.AuraHeal;
import me.zeph.spirits.ability.light.raava.Purify;
import me.zeph.spirits.ability.spirit.Compress;
import me.zeph.spirits.ability.spirit.Dash;
import me.zeph.spirits.ability.spirit.Enrage;
import me.zeph.spirits.ability.spirit.Leap;
import me.zeph.spirits.ability.spirit.Possess;
import me.zeph.spirits.ability.spirit.Pulse;


public class Abilities implements Listener {


	@EventHandler
	public void onSwing(PlayerAnimationEvent event) {

		Player player = event.getPlayer();
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);

		if (event.isCancelled() || bPlayer == null) {
			return;

		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase(null)) {
			return;

			//neutral
		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Pulse")) {
			if (!CoreAbility.hasAbility(player, Pulse.class)) {
				return;
			}
			else {
				CoreAbility.getAbility(player, Pulse.class).clickFunction();
			}


		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Dash")) {
			new Dash(player);

		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Enrage")) {
			new Enrage(player);

		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Compress")) {
			if (!CoreAbility.hasAbility(player, Compress.class)) {
				return;
			}
			else {
				CoreAbility.getAbility(player, Compress.class).onClick();
			}

		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Leap")) {
			if (!CoreAbility.hasAbility(player, Leap.class)) {
				return;
			}
			else {
				CoreAbility.getAbility(player, Leap.class).onClick();
			}


			//Raava

		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("AuraHeal")) {
			new AuraHeal(player);

		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Ascend")) {
			if (!CoreAbility.hasAbility(player, Ascend.class)) {
				return;
			}
			else {
				CoreAbility.getAbility(player, Ascend.class).onClick();
			}





			//light
		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Raava")) {
			new Raava(player);

		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Lull")) {
			new Lull(player);


		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Enthrall")) {
			new Enthrall(player);

		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Eclipse")) {
			if (!CoreAbility.hasAbility(player, Eclipse.class)) {
				return;
			}
			else {
				CoreAbility.getAbility(player, Eclipse.class).onClick();
			}


			//Vaatu

		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("DarkFlash")) {
			new DarkFlash(player);

			//dark
		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Vaatu")) {
			new Vaatu(player);

		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("SoulPunch")) {
			new SoulPunch(player);

		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("DarkTether")) {
			new DarkTether(player);

		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Infiltrate")) {
			CoreAbility.getAbility(player, Infiltrate.class).onClick();

		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("HallowedGround")) {
			if (!CoreAbility.hasAbility(player, HallowedGround.class)) {
				return;
			}
			else {
				CoreAbility.getAbility(player, HallowedGround.class).onClick();
			}

			/*
        } else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Phantom")) {
            CoreAbility.getAbility(player, Phantom.class).onClick();
			 */ 

		} 
		/*
		if (MultiAbilityManager.getBoundMultiAbility(player) == ("ToxicLash")) {
			int slot = player.getInventory().getHeldItemSlot();
			if (slot == 0) {
				new Strike(player);
			}
			else if (slot == 1) {
				new Slam(player);
			}
			else if (slot == 2) {
				new Leach(player);
			}
			else if (slot == 3) {
				new Tendril(player);
			}
			else if (slot == 4) {
				MultiAbilityManager.unbindMultiAbility(player);
				ToxicLash.removeAll();
				bPlayer.addCooldown(CoreAbility.getAbility(ToxicLash.class));
			}
		}
		*/

		if (MultiAbilityManager.getBoundMultiAbility(player) == ("Enchant") && Enchant.charged()) {
			int slot = player.getInventory().getHeldItemSlot();
			if (slot == 0) {
				new FloatMove(player);
			}
			else if (slot == 1) {
				new Heal(player);
			}
			else if (slot == 2) {
				new Invis(player);
			}
			else if (slot == 3) {
				new Resistance(player);
			}
			else if (slot == 4) {
				new Speed(player);
			}
			else if (slot == 5) {
				MultiAbilityManager.unbindMultiAbility(player);
				Enchant.removeAll();
				bPlayer.addCooldown(CoreAbility.getAbility(Enchant.class));
			}
		}

		else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Reave")) {
			new Reave(player);
		}

	}


	@EventHandler
	public void onSneak(PlayerToggleSneakEvent event) {

		Player player = event.getPlayer();
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);

		if (event.isCancelled() || bPlayer == null) {
			return;

		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase(null)) {
			return;

			//neutral
		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Pulse")) {
			new Pulse(player);

		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Compress")) {
			new Compress(player);

		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Possess")) {
			new Possess(player);

		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Leap")) {
			new Leap(player);


			//Raava

		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Purify")) {
			new Purify(player);

		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Ascend")) {
			new Ascend(player);

			//light
		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Catalyse")) {
			new Catalyse(player);     

		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Eclipse")) {
			new Eclipse(player);

		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Halo")) {
			new Halo(player);

		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Enthrall")) {
			if (!CoreAbility.hasAbility(player, Enthrall.class)) {
				return;
			}
			else {
				CoreAbility.getAbility(player, Enthrall.class).onSneak();
			}


			//Vaatu

		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Corrupt")) {
			new Corrupt(player);



			//dark


		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Reave")) {
			if (!CoreAbility.hasAbility(player, Reave.class)) {
				return;
			}
			else {
				CoreAbility.getAbility(player, Reave.class).onSneak();
			}

			/*
        } else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Phantom")) {
            new Phantom(player);
			 */
		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("HallowedGround")) {
			new HallowedGround(player);
		}
		/*
		else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("ToxicLash")) {
			new ToxicLash(player);
		}
		*/
		else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Infiltrate")) {
			new Infiltrate(player);
		}
	}



	@EventHandler
	public void onFall(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
			if (bPlayer.hasElement(SpiritElement.DARK) && event.getCause() == DamageCause.FALL) {
				if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Exonerate")) {
					new Exonerate(player);
				}
			}
		}
	}


	@EventHandler
	public void onEntityChangeBlock(EntityChangeBlockEvent event) {
		Entity e = event.getEntity();

		if (e instanceof FallingBlock) {
			FallingBlock fb = (FallingBlock)e;
			if (fb.hasMetadata("Exonerate")) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void revertStance(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);

		if (event.isCancelled() || bPlayer == null) {
			return;
		}

		else if (! bPlayer.getElements().contains(SpiritElement.DARK) && !bPlayer.getElements().contains(SpiritElement.LIGHT)){
			return;
		}


		HashMap<Integer, String> abilities = bPlayer.getAbilities();
		
		if (bPlayer.hasElement(SpiritElement.VAATU) && !CoreAbility.hasAbility(player, Vaatu.class)) {
			for (int i = 0; i < 8 ; i++) {
				abilities.replace(i, "Corrupt", "Possess");
				abilities.replace(i, "DarkFlash", "Dash");
			}
	
		}	
		if (bPlayer.hasElement(SpiritElement.RAAVA) && !CoreAbility.hasAbility(player, Raava.class)){
			for (int i = 0; i < 8 ; i++) {
				abilities.replace(i, "Ascend", "Leap");
				abilities.replace(i, "AuraHeal", "Enrage");
				abilities.replace(i, "Purify", "Possess");
			}
		}	
		bPlayer.setAbilities(abilities);
		BendingBoard.update(player);
	}

	// AUTO-ADDS SPIRIT WHEN YOU CHOOSE LS OR DS
	@EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
        String msg = event.getMessage();
        if (msg.toLowerCase().contains("lightspirit") && msg.toLowerCase().contains("ch")){
        		player.sendMessage(ChatColor.GOLD+"Spirit"+ ChatColor.WHITE + " You are now a LightSpirit.");
        		bPlayer.setElement((SpiritElement.SPIRIT));
        		bPlayer.addElement(SpiritElement.LIGHT);
        		GeneralMethods.removeUnusableAbilities(player.getName());
        		GeneralMethods.saveElements(bPlayer);
        		BendingBoard.update(player);
        		event.setCancelled(true);
        		
        }
        else if (msg.toLowerCase().contains("darkspirit") && msg.toLowerCase().contains("ch")){
        	player.sendMessage(ChatColor.GOLD+"Spirit"+ ChatColor.LIGHT_PURPLE + " You are now a DarkSpirit.");
        	bPlayer.setElement((SpiritElement.SPIRIT));
    		bPlayer.addElement(SpiritElement.DARK);
    		GeneralMethods.removeUnusableAbilities(player.getName());
    		GeneralMethods.saveElements(bPlayer);
    		BendingBoard.update(player);
    		event.setCancelled(true);
        }
        
        else {
        	return;
        }
}

	
	@EventHandler
	public void onFire(EntityCombustByBlockEvent event) {
		if (!(event.getEntity() instanceof Player)){
			return;
		}
		Player player = (Player) event.getEntity();
		if (!TempBlock.isTempBlock(event.getCombuster())) {
			return;
		}
		event.setDuration(2);
	}
}


/*
 * 
	@EventHandler
	public void revertStance(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);

		if (event.isCancelled() || bPlayer == null) {
			return;
		}

		else if (! bPlayer.getElements().contains(SpiritElement.DARK) && !bPlayer.getElements().contains(SpiritElement.LIGHT)){
			return;
		}


		HashMap<Integer, String> abilities = bPlayer.getAbilities();
		List<Element>elements = bPlayer.getElements();
		List<SubElement>subelements = bPlayer.getSubElements();
		
		if (bPlayer.hasElement(SpiritElement.VAATU) && !CoreAbility.hasAbility(player, Vaatu.class)) {
			for (int i = 0; i < 8 ; i++) {
				abilities.replace(i, "Corrupt", "Possess");
				abilities.replace(i, "DarkFlash", "Dash");
			}
			elements.remove(SpiritElement.DARK);
			bPlayer.setElement(SpiritElement.DARK);
			for (Element ele : elements) {
				bPlayer.addElement(ele);
			}
			subelements.remove(SpiritElement.VAATU);
			for (SubElement sub : subelements) {
				bPlayer.addSubElement(sub);
			}
		}	
		if (bPlayer.hasElement(SpiritElement.RAAVA) && !CoreAbility.hasAbility(player, Raava.class)){
			for (int i = 0; i < 8 ; i++) {
				abilities.replace(i, "Ascend", "Leap");
				abilities.replace(i, "AuraHeal", "Enrage");
				abilities.replace(i, "Purify", "Possess");
			}
			elements.remove(SpiritElement.LIGHT);
			bPlayer.setElement(SpiritElement.LIGHT);
			for (Element ele : elements) {
				bPlayer.addElement(ele);
			}
			subelements.remove(SpiritElement.RAAVA);
			for (SubElement sub : subelements) {
				bPlayer.addSubElement(sub);
			}
		}	
		bPlayer.setAbilities(abilities);
		BendingBoard.update(player);
	}

 */




/*   Cancels toggle glide  
    }
    @EventHandler
    public void onGrapple(EntityToggleGlideEvent event) {

    	Player player = (Player) event.getEntity();
    	BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
    	if (CoreAbility.hasAbility(player, DarkTether.class)) {
    		event.setCancelled(true);
    	}
    	else {
    		return;
    	}
    }
 */

