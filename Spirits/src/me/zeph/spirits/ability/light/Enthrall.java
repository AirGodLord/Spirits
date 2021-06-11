package me.zeph.spirits.ability.light;

import java.util.Arrays;
import java.util.List;

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
import me.zeph.spirits.ability.api.LightAbility;


public class Enthrall extends LightAbility{
	
	//Config variables
	private double shiftrange;
	private double shiftdamage;
	private double shiftspeed;
	private double shifthitbox;
	private long cooldown;
	private double damage;
	private double speed;
	private double hitbox;
	private int duration;
	private double radius;
	private int slowduration;

	//Set variables
	private Location loc;
	private Vector dir;
	private Location origin;
	private Long starttime;
	private PotionEffect invis;
	private PotionEffect blindness;
	private Boolean hasshifted;
	private Location shiftloc;
	private Vector shiftdir;
	private Location shiftorigin;
	private Location temploc;
	private Location temploc2;
	private double angle;
	
	
	
	public Enthrall(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
		
		if (bPlayer.isOnCooldown(this)) {
			return;
		}
		
		if (hasAbility(player,Enthrall.class)) {
			return;
		}
		setFields();
		
		
		start();
		
	}
		
	private void setFields() {
		
	
		this.damage = Spirits.plugin.getConfig().getDouble("Spirit.Light.Enthrall.Damage");
		this.shiftrange = Spirits.plugin.getConfig().getDouble("Spirit.Light.Enthrall.ShiftRange");
		this.shiftdamage = Spirits.plugin.getConfig().getDouble("Spirit.Light.Enthrall.ShiftDamage");
		this.shifthitbox = Spirits.plugin.getConfig().getDouble("Spirit.Light.Enthrall.ShiftHitbox");
		this.radius = Spirits.plugin.getConfig().getDouble("Spirit.Light.Enthrall.Range");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Light.Enthrall.Cooldown");
		this.speed = Spirits.plugin.getConfig().getDouble("Spirit.Light.Enthrall.Speed");
		this.hitbox = Spirits.plugin.getConfig().getDouble("Spirit.Light.Enthrall.Hitbox");
		this.duration = Spirits.plugin.getConfig().getInt("Spirit.Light.Enthrall.Duration");
		this.shiftspeed = Spirits.plugin.getConfig().getDouble("Spirit.Light.Enthrall.ShiftSpeed");
		this.slowduration = Spirits.plugin.getConfig().getInt("Spirit.Light.Enthrall.SlowDuration");
		
		this.loc = player.getLocation();
		this.starttime = System.currentTimeMillis();
		Methods.playPolygon(loc, 2, 5, Spirit.LIGHT, Usage.AURA);
		this.hasshifted = false;
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
		return "Enthrall";
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
		if (!hasshifted) {
		player.setVelocity(dir.multiply(speed));
		Methods.playParticles(player.getLocation().add(0,1,0), 4, Spirit.LIGHT, Usage.MOVE);
		
		Entity e = Methods.getAffected(player.getLocation().add(0,1,0), hitbox, player);
		if (e!=null) {
			DamageHandler.damageEntity(e, damage, this);
			Methods.applyPotion(e, PotionEffectType.SLOW,slowduration);
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
		if (hasshifted) {
			shiftloc.add(shiftdir.multiply(speed));
			temploc = shiftloc.clone().add(new Vector(0,0.5,0).rotateAroundAxis(shiftdir, Math.toRadians(angle)));
			temploc2 = shiftloc.clone().add(new Vector(0,0.5,0).rotateAroundAxis(shiftdir, Math.toRadians(-angle)));
			List<Location>locs = Arrays.asList(loc,temploc,temploc2);
			
			for (Location location : locs) {
				Methods.playParticles(location, 2, Spirit.LIGHT, Usage.SINGLE);
				Entity e = Methods.getAffected(loc, hitbox, player);
				if (e!=null) {
					DamageHandler.damageEntity(e, shiftdamage, this);
					remove();
					bPlayer.addCooldown(this);
					return;
				}
			
				if (GeneralMethods.isSolid(loc.getBlock())) {
					remove();
					bPlayer.addCooldown(this);
					return;
				}

				if (shiftloc.distance(shiftorigin)>shiftrange) {
					remove();
					bPlayer.addCooldown(this);
					return;
				}
			}
			angle+=45;
		}
	}
		
	public void onSneak() {
		if (!hasshifted) {
		hasshifted = true;
		shiftloc = player.getLocation().add(0,1,0);
		shiftdir = shiftloc.getDirection().normalize();
		shiftorigin = shiftloc.clone();
		Methods.playHollowPolygon(loc, 2, 5, Spirit.LIGHT, Usage.AURA);
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
		return "Fly gracefully through the air and crush all in your path, or rain down hell."; 
	}
	
	@Override
	public String getInstructions() {
		return "Left click to activate enthrall and tap shift to shoot a projectile.."; 
	}
	}


	