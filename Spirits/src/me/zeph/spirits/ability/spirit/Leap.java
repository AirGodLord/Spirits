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


public class Leap extends SpiritAbility{
	
	//Config variables
	private long cooldown;
	private double speed;
	private double grabrange;

	//Set variables
	private Location loc;
	private Vector dir;
	private Boolean started;
	private Boolean grabbed;
	private Entity e;
	
	public Leap(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
		
		if (bPlayer.isOnCooldown(this)) {
			return;
		}
		
		if (hasAbility(player,Leap.class)) {
			return;
		}
		
		if (!player.isOnGround()) {
			return;
		}
		
		setFields();
		
		
		start();
		
	}
		
	private void setFields() {
		
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Neutral.Leap.Cooldown");
		this.speed = Spirits.plugin.getConfig().getDouble("Spirit.Neutral.Leap.Speed");
		this.grabrange = Spirits.plugin.getConfig().getDouble("Spirit.Neutral.Leap.GrabRange");
		
		this.loc = player.getLocation();
		Methods.playCircle(loc, 2, 5, Spirit.NEUTRAL, Usage.AURA);
		this.started = false;
		this.grabbed = false;
		
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
		return "Leap";
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
		
		if (!started) {
			dir = player.getLocation().add(0,1,0).getDirection().normalize();
			player.setVelocity(dir.multiply(speed));
			started = true;
		}
		
		Methods.playParticles(player.getLocation().add(0,1,0), 1, Spirit.NEUTRAL, Usage.MOVE);
		
		if (grabbed) {
			e.setVelocity(((player.getLocation().add(0,1,0).add(player.getLocation().getDirection().normalize())).subtract(e.getLocation())).toVector());
		}
		
		if (started && player.isOnGround()) {
			remove();
			bPlayer.addCooldown(this);
			return;
		}
	}
		
	public void onClick() {
		if (!grabbed && e==null) {
			e = GeneralMethods.getTargetedEntity(player, grabrange);
			if (e!=null) {
				grabbed = true;
			}
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
	public String getDescription() {
		return "Achieve great heights."; 
	}
	
	@Override
	public String getInstructions() {
		return "Tap shift whilst on the ground."; 
	}
		
	}


	