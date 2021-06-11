package me.zeph.spirits.ability.spirit;

import org.bukkit.Location;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.zeph.spirits.Methods;
import me.zeph.spirits.Methods.Spirit;
import me.zeph.spirits.Methods.Usage;
import me.zeph.spirits.Spirits;
import me.zeph.spirits.ability.api.DarkAbility;
import me.zeph.spirits.ability.api.SpiritAbility;


public class Enrage extends SpiritAbility{
	
	//Config variables
	private double radius;
	private int duration;
	private long cooldown;
	private int points;

	//Set variables
	private Long starttime;
	private Long timeleft;
	private double i;

	public Enrage(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
		
		if (bPlayer.isOnCooldown(this)) {
			return;
		}
		
		
		setFields();
		
		
		start();
		
	}
		
	private void setFields() {
		
		this.points = Spirits.plugin.getConfig().getInt("Spirit.Neutral.Enrage.Points");
		this.radius = Spirits.plugin.getConfig().getDouble("Spirit.Neutral.Enrage.Radius");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Neutral.Enrage.Cooldown");
		this.duration = Spirits.plugin.getConfig().getInt("Spirit.Neutral.Enrage.Duration");
		
		this.starttime = System.currentTimeMillis();
		this.i = 0;
		PotionEffect glowing = new PotionEffect(PotionEffectType.GLOWING,duration/1000*20, 1,false,false,false);
		player.addPotionEffect(glowing);
		PotionEffect speed = new PotionEffect(PotionEffectType.SPEED,duration/1000*20, 1,false,false,false);
		player.addPotionEffect(speed);
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
		return "Enrage";
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
		
		if (player.isDead() || !player.isOnline() ) {
			remove();
			return;
		}
		
		if (System.currentTimeMillis() > starttime+duration) {
			bPlayer.addCooldown(this);
			remove();
			return;
		}
		player.setFireTicks(0);
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
		return "Temporarily become faster."; 
	}
	
	@Override
	public String getInstructions() {
		return "Left click."; 
	}
		
	}


	