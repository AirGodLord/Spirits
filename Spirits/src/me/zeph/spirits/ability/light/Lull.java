package me.zeph.spirits.ability.light;
import java.util.ArrayList;
import java.util.List;

// CALMING WAVE SLOWS ENEMIES UNTIL THEY FALL INTO A SLUMBER. ENEMIES WOKEN BY DAMAGE WILL BE CONFUSED AND DISORIENTED. SHORT-TERM AMNESIA MEANS ALL WAKING ENEMIES FORGET ANYTHING THAT HAPPENED BEFORE THE LULL
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.projectkorra.projectkorra.util.DamageHandler;

import me.zeph.spirits.Methods;
import me.zeph.spirits.Methods.Spirit;
import me.zeph.spirits.Methods.Usage;
import me.zeph.spirits.Spirits;
import me.zeph.spirits.ability.api.LightAbility;


public class Lull extends LightAbility{
	
	//Config variables
	private double range;
	private long cooldown;
	private double arc;
	private double speed;

	//Set variables
	private Location loc;
	private Vector dir;
	private Location origin;
	private List<Vector>dirs;
	private List<Location>locs;
	
	public Lull(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
		
		if (bPlayer.isOnCooldown(this)) {
			return;
		}
		
		
		setFields();
		start();
		bPlayer.addCooldown(this);
		
	}
		
	private void setFields() {
		
		this.arc = Spirits.plugin.getConfig().getDouble("Spirit.Light.Lull.Arc");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Light.Lull.Cooldown");
		this.range = Spirits.plugin.getConfig().getDouble("Spirit.Light.Lull.Range");
		this.speed =  Spirits.plugin.getConfig().getDouble("Spirit.Light.Lull.Speed");
		
		this.loc = player.getLocation().add(0,1,0);
		this.origin = loc.clone();
		this.dir = loc.getDirection().normalize();
		this.dirs = new ArrayList<Vector>();
		
		for (double i = -(0.5*arc); i<(0.5*arc);i++) {
			Vector tempdir = dir.clone();
			tempdir.rotateAroundY(Math.toRadians(i));
			dirs.add(tempdir);
			
			}
		
		this.locs = new ArrayList<Location>();
		for (int i = 0; i<dirs.size(); i++) {
			locs.add(i,loc.clone());
		}
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
		return "Lull";
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
		
		if (loc.distance(origin)>range) {
			this.remove();
			return;
		}
		
		loc.add((dir).multiply(speed));
		
		for (int i = 0; i<dirs.size(); i++) {
			locs.get(i).add(dirs.get(i).multiply(speed));
		}
		
		for (Location temploc : locs) {
			Methods.playParticles(temploc, 1, Spirit.LIGHT, Usage.AMBIENT);
			Entity e = Methods.getAffected(temploc, 1, player);
			if (e!=null) {
				DamageHandler.damageEntity(e, 1, this);
		}
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
		return "Lull your foes to sleep."; 
	}
	
	@Override
	public String getInstructions() {
		return "Left click."; 
	}
	}


	