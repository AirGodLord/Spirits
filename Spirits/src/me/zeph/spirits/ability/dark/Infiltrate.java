package me.zeph.spirits.ability.dark;

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


public class Infiltrate extends DarkAbility{

	//Config variables
	private double range;
	private long cooldown;
	private double damage;
	private double speed;
	private double hitbox;
	private long chargespeed;
	private int blindduration;

	//Set variables
	private Location loc;
	private Location loc2;
	private Vector dir;
	private Location origin;
	private long starttime;
	private Boolean ischarged;
	private Boolean hasclicked;
	private Boolean startedprojectile;
	private double angle;

	public Infiltrate(Player player) {
		super(player);
		// TODO Auto-generated constructor stub

		if (bPlayer.isOnCooldown(this)) {
			return;
		}


		setFields();


		start();

	}

	private void setFields() {


		this.damage = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Infiltrate.Damage");
		this.range = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Infiltrate.Range");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Dark.Infiltrate.Cooldown");
		this.speed = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Infiltrate.Speed");
		this.hitbox = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Infiltrate.Hitbox");
		this.chargespeed = Spirits.plugin.getConfig().getLong("Spirit.Dark.Infiltrate.ChargeSpeed");
		this.blindduration = Spirits.plugin.getConfig().getInt("Spirit.Dark.Infiltrate.BlindDuration");

		this.loc = player.getLocation().add(0,1,0);
		this.starttime = System.currentTimeMillis();
		this.ischarged = false;
		this.hasclicked = false;
		this.startedprojectile = false;
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
		return "Infiltrate";
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

		if (System.currentTimeMillis() > starttime + chargespeed) {
			ischarged = true;
		}

		if (!player.isSneaking() && !ischarged) {
			remove();
			return;
		}

		if (ischarged && !hasclicked){
			displayCharged();
		}

		if (hasclicked) {
			if (!startedprojectile) {
				this.origin = player.getLocation().add(0,1,0);
				this.loc2 = player.getLocation().add(0,1,0);
				this.dir = loc2.getDirection().normalize();
				startedprojectile = true;
			}
		
			loc2.add(dir.multiply(speed));
			double radians = Math.toRadians(angle);
			loc = loc2.clone().add(new Vector (0,0.5,0).rotateAroundAxis(dir, radians));
			Methods.playParticles(loc, 3, Spirit.DARK, Usage.BALL2);
			Entity e = Methods.getAffected(loc, hitbox, player);
			if (e!=null) {
				DamageHandler.damageEntity(e, damage, this);
				Methods.playParticles(player.getLocation(), 10, Spirit.DARK, Usage.TELEPORT);
				player.teleport(e.getLocation());
				Methods.playParticles(player.getLocation(), 10, Spirit.DARK, Usage.TELEPORT);
				Methods.applyPotion(e, PotionEffectType.BLINDNESS,blindduration);
			}

			if (GeneralMethods.isSolid(loc.getBlock())) {
				remove();
				bPlayer.addCooldown(this);
				return;
			}

			if (loc.distance(origin) > range) {
				remove();
				bPlayer.addCooldown(this);
				return;
			}
		}
		angle+=40;
	}

	public void onClick() {
		if (ischarged && !hasclicked) {
			this.hasclicked = true;
		}
		else {
			return;
		}
	}


	// TODO Auto-generated method stub

	public void displayCharged() {
		Methods.playParticles(GeneralMethods.getMainHandLocation(player).add(player.getLocation().getDirection().normalize()), 1, Spirit.DARK, Usage.CHARGED);
	}

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
		return "Keep your friends close and your enemies closer."; 
	}
	
	@Override
	public String getInstructions() {
		return "Hold shift to charge, once charged you may release shift and left click to fire, upon hitting an opponent you will teleport to them."; 
	}
}


