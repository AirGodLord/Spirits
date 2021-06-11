package me.zeph.spirits.ability.light.raava;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ability.CoreAbility;
import com.projectkorra.projectkorra.util.DamageHandler;

import me.zeph.spirits.Methods;
import me.zeph.spirits.Methods.Spirit;
import me.zeph.spirits.Methods.Usage;
import me.zeph.spirits.SpiritElement;
import me.zeph.spirits.Spirits;
import me.zeph.spirits.ability.api.DarkAbility;
import me.zeph.spirits.ability.api.RaavaAbility;
import me.zeph.spirits.ability.api.SpiritAbility;
import me.zeph.spirits.ability.dark.Vaatu;
import me.zeph.spirits.ability.light.Raava;


public class Ascend extends RaavaAbility{
	
	//Config variables
	private long cooldown;
	private double speed;
	private double grabrange;
	private int duration;
	private double dashrange;
	private long dashcooldown;

	//Set variables
	private Location loc;
	private Vector dir;
	private Boolean started;
	private Boolean grabbed;
	private Entity e;
	private long starttime;
	private long lastdash;
	
	public Ascend(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
		
		if (bPlayer.isOnCooldown(this)) {
			return;
		}
		
		if (!CoreAbility.hasAbility(player, Raava.class)) {
			player.sendMessage("You need Raava");
			return;
		}
		
		if (hasAbility(player,Ascend.class)) {
			return;
		}
		
		setFields();
		
		
		start();
		
	}
		
	private void setFields() {
		
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Light.Raava.Ascend.Cooldown");
		this.speed = Spirits.plugin.getConfig().getDouble("Spirit.Light.Raava.Ascend.Speed");
		this.grabrange = Spirits.plugin.getConfig().getDouble("Spirit.Light.Raava.Ascend.GrabRange");
		this.duration = Spirits.plugin.getConfig().getInt("Spirit.Light.Raava.Ascend.Duration");
		this.dashrange = Spirits.plugin.getConfig().getDouble("Spirit.Light.Raava.Ascend.DashRange");
		this.dashcooldown = Spirits.plugin.getConfig().getLong("Spirit.Light.Raava.Ascend.DashCooldown");
		
		this.loc = player.getLocation();
		Methods.playCircle(loc, 2, 5, Spirit.LIGHT, Usage.AURA);
		this.started = false;
		this.grabbed = false;
		this.starttime = System.currentTimeMillis();
		Methods.applyPotionPlayer(player, PotionEffectType.LEVITATION, duration);
		
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
		return "Ascend";
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
		
		if (System.currentTimeMillis() > starttime+duration) {
			remove();
			bPlayer.addCooldown(this);
			return;
		}
	
		
		Methods.playParticles(player.getLocation().add(0,1,0), 1, Spirit.NEUTRAL, Usage.MOVE);
		
		
		if (started && player.isOnGround()) {
			remove();
			bPlayer.addCooldown(this);
			return;
		}
	}
		
	public void onClick() {
		if (System.currentTimeMillis() > lastdash + dashcooldown) {
			lastdash = System.currentTimeMillis();
			player.setVelocity(player.getLocation().getDirection().normalize().multiply(dashrange));
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
	public Element getElement() {
		return SpiritElement.RAAVA;
	}
		
	@Override
	public String getDescription() {
		return "Take control of the Heavens."; 
	}
	
	@Override
	public String getInstructions() {
		return "Tap shift to activate, left click to dash mid-air."; 
	}
	}


	