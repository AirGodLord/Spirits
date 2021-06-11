package me.zeph.spirits.ability.spirit.combo;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ability.ComboAbility;
import com.projectkorra.projectkorra.ability.util.ComboManager.AbilityInformation;
import com.projectkorra.projectkorra.util.ClickType;
import com.projectkorra.projectkorra.util.DamageHandler;

import me.zeph.spirits.Methods;
import me.zeph.spirits.Methods.Spirit;
import me.zeph.spirits.Methods.Usage;
import me.zeph.spirits.Spirits;
import me.zeph.spirits.ability.api.DarkAbility;
import me.zeph.spirits.ability.api.SpiritAbility;


public class Soar extends SpiritAbility implements ComboAbility{
	
	//Config variables
	private long cooldown;
	private double speed;
	private int duration;

	//Set variables
	private Location loc;
	private Vector dir;
	private Location origin;
	private Long starttime;
	
	public Soar(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
		
		if (bPlayer.isOnCooldown(this)) {
			return;
		}
		
		if (hasAbility(player,Soar.class)) {
			return;
		}
		setFields();
		
		
		start();
		
	}
		
	private void setFields() {
		
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Neutral.Soar.Cooldown");
		this.speed = Spirits.plugin.getConfig().getDouble("Spirit.Neutral.Soar.Speed");
		this.duration = Spirits.plugin.getConfig().getInt("Spirit.Neutral.Soar.Duration");
		
		this.loc = player.getLocation();
		this.starttime = System.currentTimeMillis();
		Methods.playCircle(loc, 2, 5, Spirit.NEUTRAL, Usage.AURA);
		
	}		

	@Override
	public long getCooldown() {
		// TODO Auto-generated method stub
		return cooldown;
	}

	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Soar";
	}

	@Override
	public boolean isHarmlessAbility() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSneakAbility() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void progress() {
		// TODO Auto-generated method stub
		
		if (player.isDead() || !player.isOnline()) {
			remove();
			return;
		}
		
		dir = player.getLocation().add(0,1,0).getDirection().normalize();
		player.setVelocity(dir.multiply(speed));
		Methods.playParticles(player.getLocation().add(0,1,0), 4, Spirit.NEUTRAL, Usage.MOVE);
		
		
		if (System.currentTimeMillis() > starttime + (duration)) {
			remove();
			bPlayer.addCooldown(this);
			return;
		}
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
	public Object createNewComboInstance(Player player) {
		return new Soar(player);
	}

	@Override
	public ArrayList<AbilityInformation> getCombination() {
		final ArrayList<AbilityInformation> combo = new ArrayList<>();
		combo.add(new AbilityInformation("Dash", ClickType.SHIFT_DOWN));
		return combo;
	}
		
	@Override
	public String getDescription() {
		return "Soar through the air.";
	}
	
	@Override
	public String getInstructions() {
		return "Tap shift with dash."; 
	}
	}


	