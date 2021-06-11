package me.zeph.spirits.ability.light.multiability;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import com.projectkorra.projectkorra.ability.CoreAbility;
import com.projectkorra.projectkorra.ability.util.MultiAbilityManager;
import me.zeph.spirits.Methods;
import me.zeph.spirits.Spirits;
import me.zeph.spirits.ability.api.LightAbility;
import me.zeph.spirits.ability.light.combo.Enchant;


public class FloatMove extends LightAbility{
	
	private int floatduration;
	
	public FloatMove(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
		
		if (bPlayer.isOnCooldown(this)) {
			return;
		}
		
		
		setFields();
		
		
		start();
		
	}
		
	private void setFields() {
		this.floatduration = Spirits.plugin.getConfig().getInt("Spirit.Light.Enchant.FloatDuration");
	
	}		

	public long getCooldown() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Enchant";
	}

	@Override
	public boolean isHarmlessAbility() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSneakAbility() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void progress() {
		// TODO Auto-generated method stub
		
		if (player.isDead() || !player.isOnline()) {
			remove();
			return;
		}
		if (!MultiAbilityManager.hasMultiAbilityBound(this.player, "Enchant")) {
			this.remove();
			return;
		}
		
		Methods.applyPotionPlayer(player, PotionEffectType.LEVITATION, floatduration);
		MultiAbilityManager.unbindMultiAbility(player);
		Enchant.removeAll();
		bPlayer.addCooldown(CoreAbility.getAbility(Enchant.class));
		return;
	}
		
	
		// TODO Auto-generated method stub

	@Override
	public boolean isExplosiveAbility() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isIgniteAbility() {
		// TODO Auto-generated method stub
		return false;
	}
		
	@Override
	public String getDescription() {
		return "Apply a choice of buffs to yourself."; 
	}
	
	@Override
	public String getInstructions() {
		return "Catalyse (Left click) >  Lull (Hold shift) and left click on a slot to activate enchant."; 
	}
	}


	