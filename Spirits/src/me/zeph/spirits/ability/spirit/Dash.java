package me.zeph.spirits.ability.spirit;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.util.DamageHandler;

import me.zeph.spirits.Methods;
import me.zeph.spirits.Methods.Spirit;
import me.zeph.spirits.Methods.Usage;
import me.zeph.spirits.Spirits;
import me.zeph.spirits.ability.api.DarkAbility;
import me.zeph.spirits.ability.api.SpiritAbility;


public class Dash extends SpiritAbility{
	
	//Config variables
	private long cooldown;
	private double speed;
	private int uses;

	//Set variables
	private Location loc;
	private Vector dir;
	private Location origin;
	private Long starttime;
	
	public Dash(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
		
		if (bPlayer.isOnCooldown(this)) {
			return;
		}
		
		if (hasAbility(player,Dash.class)) {
			return;
		}
		setFields();
		
		
		start();
		
	}
		
	private void setFields() {
		
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Neutral.Dash.Cooldown");
		this.speed = Spirits.plugin.getConfig().getDouble("Spirit.Neutral.Dash.Speed");
		
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
		return "Dash";
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
		
		dir = player.getLocation().add(0,1,0).getDirection().normalize();
		Methods.playParticles(player.getLocation().add(0,1,0), 1, Spirit.NEUTRAL, Usage.MOVE);
		player.setVelocity(dir.multiply(speed));
		remove();
		bPlayer.addCooldown(this);
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
		return "Move forward."; 
	}
	
	@Override
	public String getInstructions() {
		return "Left click."; 
	}
		
	}


	