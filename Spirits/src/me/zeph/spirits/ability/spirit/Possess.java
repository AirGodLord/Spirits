package me.zeph.spirits.ability.spirit;

import org.bukkit.GameMode;
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


public class Possess extends SpiritAbility{
	
	//Config variables
	private long cooldown;
	private int duration;
	private double damage;
	private double range;
	private double hitbox;

	//Set variables
	private Location loc;
	private Long starttime;
	private Location origin;
	private Entity e;
	private Boolean ischarged;
	
	public Possess(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
		
		if (bPlayer.isOnCooldown(this)) {
			return;
		}
		
		if (hasAbility(player,Possess.class)) {
			return;
		}
		
		this.range = Spirits.plugin.getConfig().getDouble("Spirit.Neutral.Possess.Range");
		e = GeneralMethods.getTargetedEntity(player, range);
		
		if (e!=null) {
			Methods.applyPotionPlayer(player, PotionEffectType.INVISIBILITY, duration);
			setFields();
			start();
		}
		else {
			return;
		}
		
	}
		
	private void setFields() {
		
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Neutral.Possess.Cooldown");
		this.duration = Spirits.plugin.getConfig().getInt("Spirit.Neutral.Possess.Duration");
		this.damage = Spirits.plugin.getConfig().getDouble("Spirit.Neutral.Possess.Damage");
	
		this.hitbox = Spirits.plugin.getConfig().getDouble("Spirit.Neutral.Possess.Hitbox");

		this.starttime = System.currentTimeMillis();
		this.ischarged = false;
		
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
		return "Possess";
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
			player.removePotionEffect(PotionEffectType.INVISIBILITY);
			remove();
			return;
		}
		
		if (e!=null) {
			Methods.playParticles(e.getLocation().add(0,1,0), 5, Spirit.NEUTRAL, Usage.POSSESS);
			player.teleport(e);
			player.setInvulnerable(true);
		}
		
		if (System.currentTimeMillis() > starttime+duration) {
			ischarged = true;
			DamageHandler.damageEntity(e, damage, this);
			player.setGameMode(GameMode.SURVIVAL);
			remove();
			player.removePotionEffect(PotionEffectType.INVISIBILITY);
			player.setInvulnerable(false);
			bPlayer.addCooldown(this);
			return;
		}
		
		if (!player.isSneaking() && !ischarged) {
			player.setGameMode(GameMode.SURVIVAL);
			player.removePotionEffect(PotionEffectType.INVISIBILITY);
			remove();
			player.setInvulnerable(false);
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
	public String getDescription() {
		return "Warp your opponents physiology."; 
	}
	
	@Override
	public String getInstructions() {
		return "Hold shift at an entity and release when they are damaged."; 
	}
		
	}


	