package me.zeph.spirits.ability.light.raava;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.ability.CoreAbility;
import com.projectkorra.projectkorra.util.DamageHandler;

import me.zeph.spirits.Methods;
import me.zeph.spirits.SpiritElement;
import me.zeph.spirits.Methods.Spirit;
import me.zeph.spirits.Methods.Usage;
import me.zeph.spirits.Spirits;
import me.zeph.spirits.ability.api.DarkAbility;
import me.zeph.spirits.ability.api.LightAbility;
import me.zeph.spirits.ability.api.RaavaAbility;
import me.zeph.spirits.ability.api.SpiritAbility;
import me.zeph.spirits.ability.light.Raava;


public class AuraHeal extends RaavaAbility{
	
	//Config variables
	private double radius;
	private int duration;
	private long cooldown;
	private int points;

	//Set variables
	private Long starttime;
	private Long timeleft;
	private double i;
	private Entity e;

	public AuraHeal(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
		
		if (bPlayer.isOnCooldown(this)) {
			return;
		}
		
		if (!CoreAbility.hasAbility(player, Raava.class)) {
			player.sendMessage("You need Raava");
			return;
		}
		
		setFields();
		
		
		start();
		
	}
		
	private void setFields() {
		
		this.points = Spirits.plugin.getConfig().getInt("Spirit.Light.Raava.AuraHeal.Points");
		this.radius = Spirits.plugin.getConfig().getDouble("Spirit.Light.Raava.AuraHeal.Radius");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Light.Raava.AuraHeal.Cooldown");
		this.duration = Spirits.plugin.getConfig().getInt("Spirit.Light.Raava.AuraHeal.Duration");
		
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
		return "AuraHeal";
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
		
		e = Methods.getAffected(player.getLocation(), radius, player);
		if (e!=null) {
			Methods.applyPotionPlayer(player, PotionEffectType.REGENERATION, 0);
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
	public Element getElement() {
		return SpiritElement.RAAVA;
	}
		
	@Override
	public String getDescription() {
		return "Gain health whilst near other entities, and become immune to fire."; 
	}
	
	@Override
	public String getInstructions() {
		return "Left click to activate."; 
	}
	}


	