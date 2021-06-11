package me.zeph.spirits.ability.dark;

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


public class Reave extends DarkAbility{
	
	//Config variables
	private double range;
	private long cooldown;
	private double damage;
	private double speed;
	private double hitbox;
	private int duration;
	private int blindduration;

	//Set variables
	private Location loc;
	private Vector dir;
	private Location origin;
	private Long starttime;
	private PotionEffect invis;
	private PotionEffect blindness;
	
	public Reave(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
		
		if (bPlayer.isOnCooldown(this)) {
			return;
		}
		
		if (hasAbility(player,Reave.class)) {
			return;
		}
		setFields();
		
		
		start();
		
	}
		
	private void setFields() {
		
	
		this.damage = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Reave.Damage");
		this.range = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Reave.Range");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Dark.Reave.Cooldown");
		this.speed = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Reave.Speed");
		this.hitbox = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Reave.Hitbox");
		this.duration = Spirits.plugin.getConfig().getInt("Spirit.Dark.Reave.Duration");
		this.blindduration = Spirits.plugin.getConfig().getInt("Spirit.Dark.Reave.BlindDuration");
		
		this.loc = player.getLocation();
		this.starttime = System.currentTimeMillis();
		invis = new PotionEffect(PotionEffectType.INVISIBILITY,duration/1000*20, 1,true,false,false);
		player.addPotionEffect(invis);
		Methods.playPolygon(loc, 2, 5, Spirit.DARK, Usage.AURA);
		
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
		return "Reave";
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
		player.setVelocity(dir.multiply(speed));
		Methods.playParticles(player.getLocation().add(0,1,0), 4, Spirit.DARK, Usage.MOVE);
		
		Entity e = Methods.getAffected(player.getLocation().add(0,1,0), hitbox, player);
		if (e!=null) {
			DamageHandler.damageEntity(e, damage, this);
			Methods.applyPotion(e, PotionEffectType.BLINDNESS,blindduration);
			player.removePotionEffect(PotionEffectType.INVISIBILITY);
			remove();
			bPlayer.addCooldown(this);
			return;
		}
		
		
		if (System.currentTimeMillis() > starttime + (duration)) {
			remove();
			bPlayer.addCooldown(this);
			return;
		}
	}
		
	public void onSneak() {
		Methods.playParticles(player.getLocation().add(0,1,0), 1, Spirit.DARK, Usage.TELEPORT);
		player.teleport(loc);
		Methods.playParticles(player.getLocation().add(0,1,0), 1, Spirit.DARK, Usage.TELEPORT);
		Methods.playPolygon(loc, 2, 5, Spirit.DARK, Usage.AURA);
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
		return "Cut opponents down to size."; 
	}
	
	@Override
	public String getInstructions() {
		return "Click to fly forward into an opponent to deal damage, or tap shift to teleport to your original position."; 
	}
	}


	