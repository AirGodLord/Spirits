package me.zeph.spirits.ability.dark;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.util.DamageHandler;

import me.zeph.spirits.Methods;
import me.zeph.spirits.Methods.Spirit;
import me.zeph.spirits.Methods.Usage;
import me.zeph.spirits.Spirits;
import me.zeph.spirits.ability.api.DarkAbility;


public class SoulPunch extends DarkAbility{
	
	//Config variables
	private double range;
	private long cooldown;
	private double damage;
	private double speed;
	private double hitbox;
	private int blindduration;
	private double teleportblocks;

	//Set variables
	private Location loc;
	private Location origin;
	private Vector dir;
	private Location temploc;
	private Location temploc2;
	private double angle;
	
	
	public SoulPunch(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
		
		if (bPlayer.isOnCooldown(this)) {
			return;
		}
		
		
		setFields();
		bPlayer.addCooldown(this);
		
		start();
		
	}
		
	private void setFields() {
		
	
		this.damage = Spirits.plugin.getConfig().getDouble("Spirit.Dark.SoulPunch.Damage");
		this.range = Spirits.plugin.getConfig().getDouble("Spirit.Dark.SoulPunch.Range");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Dark.SoulPunch.Cooldown");
		this.speed = Spirits.plugin.getConfig().getDouble("Spirit.Dark.SoulPunch.Speed");
		this.hitbox = Spirits.plugin.getConfig().getDouble("Spirit.Dark.SoulPunch.Hitbox");
		this.blindduration = Spirits.plugin.getConfig().getInt("Spirit.Dark.SoulPunch.blindduration");
		this.teleportblocks = Spirits.plugin.getConfig().getDouble("Spirit.Dark.SoulPunch.TeleportBlocks");
		
		this.loc = player.getLocation().add(0,1,0);
		this.dir = loc.getDirection().normalize();
		this.origin = loc.clone();
		this.angle = 0;
		
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
		return "SoulPunch";
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
		
		dir = loc.getDirection().normalize();
		loc.add(dir.multiply(speed));
		temploc = loc.clone().add(new Vector(0,0.5,0).rotateAroundAxis(dir, Math.toRadians(angle)));
		temploc2 = loc.clone().add(new Vector(0,0.5,0).rotateAroundAxis(dir, Math.toRadians(-angle)));
		List<Location>locs = Arrays.asList(loc,temploc,temploc2);
		
		for (Location location : locs) {
			Methods.playParticles(location, 2, Spirit.DARK, Usage.SINGLE);
			Entity e = Methods.getAffected(loc, hitbox, player);
			if (e!=null) {
				DamageHandler.damageEntity(e, damage, this);
				Methods.applyPotion(e, PotionEffectType.BLINDNESS, blindduration);
			}
		
			if (GeneralMethods.isSolid(loc.getBlock())) {
				remove();
				bPlayer.addCooldown(this);
				return;
			}

			if (loc.distance(origin)>range) {
				remove();
				bPlayer.addCooldown(this);
				return;
			}
		}
		angle+=45;
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
		return "Hit so hard you tear through a persons soul."; 
	}
	
	@Override
	public String getInstructions() {
		return "Left click to fire a projectile and teleport closer to your opponent."; 
	}
		
	}


	