package me.zeph.spirits.ability.light;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ability.CoreAbility;
import com.projectkorra.projectkorra.util.DamageHandler;

import me.zeph.spirits.Methods;
import me.zeph.spirits.Methods.Spirit;
import me.zeph.spirits.Methods.Usage;
import me.zeph.spirits.Spirits;
import me.zeph.spirits.ability.api.LightAbility;


public class Halo extends LightAbility{
	
	//Config variables
	private double radius;
	private double range;
	private long cooldown;
	private int points;
	private double speed;
	private double damage;
	private int levitationduration;

	//Set variables
	private Location loc;
	private Vector dir;
	private Location origin;
	private Entity e;
	private List<Location>circle;
	private long halostart;
	private Boolean hasdamaged;
	private Boolean haschosenentity;


	public Halo(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
		
		if (bPlayer.isOnCooldown(this)) {
			return;
		}
		
		if (CoreAbility.hasAbility(player, Halo.class)) {
			return;
		}
		
		setFields();
		
		
		start();
		
	}
		
	private void setFields() {
		
		this.speed = Spirits.plugin.getConfig().getDouble("Spirit.Light.Halo.Speed");
		this.damage = Spirits.plugin.getConfig().getDouble("Spirit.Light.Halo.Damage");
		this.points = Spirits.plugin.getConfig().getInt("Spirit.Light.Halo.Points");
		this.radius = Spirits.plugin.getConfig().getDouble("Spirit.Light.Halo.Radius");
		this.range = Spirits.plugin.getConfig().getDouble("Spirit.Light.Halo.Range");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Light.Halo.Cooldown");
		this.levitationduration = Spirits.plugin.getConfig().getInt("Spirit.Light.Halo.LevitationDuration");
		
		this.loc = player.getLocation().add(0,1,0);
		this.origin = loc.clone();
		this.hasdamaged = false;
		this.haschosenentity = false;
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
		return "Halo";
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
		
		if (player.isDead() || !player.isOnline() ) {
			remove();
			return;
		}
		
		this.origin = player.getEyeLocation();
		
		if ((System.currentTimeMillis() > levitationduration+halostart) && haschosenentity){
			remove();
			bPlayer.addCooldown(this);
			return;
		}
		
		if (GeneralMethods.isSolid(loc.getBlock()) || (loc.distance(origin)>range && e==null)) {
			remove();
			bPlayer.addCooldown(this);
			return;
		}
		
		this.dir = player.getLocation().add(0,1,0).getDirection().normalize();
		
		if (!player.isSneaking() && e==null) {
			loc.add((dir).multiply(speed));
		}
		else if (player.isSneaking() && e==null) {
			loc.add(((player.getLocation().add(0,1,0).add(dir)).subtract(loc)).toVector().normalize().multiply(speed) );
		}
		
		circle = Methods.getCircle(loc, radius, points);
		
		for (Location temploc : circle){
			Methods.playParticles(temploc, 1, Spirit.LIGHT, Usage.SINGLE);
		}
		
		if (!haschosenentity) {
			e = Methods.getAffected(loc, radius, player);
			if (e!=null) {
				haschosenentity = true;
			}
			else {
				return;
			}
		}
		if (e!=null) {
			if (!hasdamaged) {
				halostart = System.currentTimeMillis();
				Methods.applyPotion(e, PotionEffectType.LEVITATION, levitationduration);
				DamageHandler.damageEntity(e, damage, this);
				hasdamaged = true;
			}
			loc = e.getLocation().add(0,2,0);
			
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
		return "Does it hurt to fall from Heaven?"; 
	}
	
	@Override
	public String getInstructions() {
		return "Tap shift and release to shoot a halo, hold shift to return it to you. Upon hitting an opponent it will cause them to levitate."; 
	}
	}


	