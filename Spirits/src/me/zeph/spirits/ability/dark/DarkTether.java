package me.zeph.spirits.ability.dark;

import java.util.ArrayList;
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


public class DarkTether extends DarkAbility{

	//Config variables
	private double range;
	private long cooldown;
	//private double damage;
	private double speed;
	private double hitbox;

	//Set variables
	private PotionEffect mining;
	private Location loc;
	private Vector dir;
	private List<Location>locs;
	private double progress;
	private Location endloc;
	private Location origin;
	
	public DarkTether(Player player) {
		super(player);
		// TODO Auto-generated constructor stub

		if (bPlayer.isOnCooldown(this)) {
			return;
		}
		if (hasAbility(player,DarkTether.class)) {
			return;
		}

		bPlayer.addCooldown(this);
		setFields();

		start();

	}

	private void setFields() {

		
		//this.damage = Spirits.plugin.getConfig().getDouble("Spirit.Dark.DarkTether.Damage");
		this.range = Spirits.plugin.getConfig().getDouble("Spirit.Dark.DarkTether.Range");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Dark.DarkTether.Cooldown");
		this.speed = Spirits.plugin.getConfig().getDouble("Spirit.Dark.DarkTether.Speed");
		this.hitbox = Spirits.plugin.getConfig().getDouble("Spirit.Dark.DarkTether.Hitbox");

		
		this.mining = new PotionEffect(PotionEffectType.SLOW_DIGGING, 20, 100, true, false, false);
		player.addPotionEffect(mining);
		
		this.origin = player.getLocation().add(0,1,0);
		this.dir = player.getLocation().add(0,1,0).getDirection().normalize();
		this.progress = 0;
		this.locs = new ArrayList<Location>();
		
		
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
		return "DarkTether";
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
		
		this.loc = player.getLocation().add(0,1,0);
		
		locs.clear();
		progress+=speed;
		endloc = origin.clone().add(dir.clone().multiply(progress));
	
		
		for (double i = 0; i<progress; i+=0.1) {
			Location temploc = loc.clone().add(endloc.clone().subtract(loc.clone()).toVector().normalize().multiply(i));
			locs.add(temploc);
		}
		
		for (Location location: locs) {
			Methods.playParticles(location, 1, Spirit.DARK, Usage.FLAME);
		}
		
		
		Entity e = Methods.getAffected(endloc, hitbox, player);
		if (e!=null) {
			e.setVelocity((e.getLocation().subtract(loc)).toVector().multiply(-1));
			remove();
			bPlayer.addCooldown(this);
			return;
		}
		
		if (GeneralMethods.isSolid(endloc.getBlock())) {
			player.setVelocity((endloc.clone().subtract(loc.clone())).toVector().normalize());
			if (loc.distance(endloc) <1 || GeneralMethods.isObstructed(loc, endloc)){
				remove();
				bPlayer.addCooldown(this);
				return;
			}
		}
		
		if (progress>range) {
			remove();
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
		return "Shoot out a tether to towards a location, or pull an entity."; 
	}
	
	@Override
	public String getInstructions() {
		return "Left click at a surface or mob."; 
	}
}


