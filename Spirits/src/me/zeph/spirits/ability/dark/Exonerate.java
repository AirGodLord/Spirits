package me.zeph.spirits.ability.dark;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// CALMING WAVE SLOWS ENEMIES UNTIL THEY FALL INTO A SLUMBER. ENEMIES WOKEN BY DAMAGE WILL BE CONFUSED AND DISORIENTED. SHORT-TERM AMNESIA MEANS ALL WAKING ENEMIES FORGET ANYTHING THAT HAPPENED BEFORE THE Exonerate
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.util.Vector;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ProjectKorra;
import com.projectkorra.projectkorra.util.DamageHandler;

import me.zeph.spirits.Methods;
import me.zeph.spirits.Methods.Spirit;
import me.zeph.spirits.Methods.Usage;
import me.zeph.spirits.Spirits;
import me.zeph.spirits.ability.api.DarkAbility;


public class Exonerate extends DarkAbility{
	
	//Config variables
	private double range;
	private long cooldown;
	private double arc;
	private double speed;
	private double fallthreshold;

	//Set variables
	private Location loc;
	private Vector dir;
	private Location origin;
	private List<Vector>dirs;
	private List<Location>locs;
	private FallingBlock fb;

	
	public Exonerate(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
		
		if (bPlayer.isOnCooldown(this)) {
			return;
		}
		
		this.fallthreshold =  Spirits.plugin.getConfig().getDouble("Spirit.Dark.Exonerate.FallThreshold");
		if (player.getFallDistance() < fallthreshold) {
			return;
		}
		
		else {
			setFields();
			start();
		}
		
	}
		
	private void setFields() {
		
		this.arc = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Exonerate.Arc");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Dark.Exonerate.Cooldown");
		this.range = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Exonerate.Range");
		this.speed =  Spirits.plugin.getConfig().getDouble("Spirit.Dark.Exonerate.Speed");
		
		
		this.loc = player.getLocation().add(0,1,0);
		this.origin = loc.clone();
		this.dir = loc.getDirection().normalize();
		this.dirs = new ArrayList<Vector>();
		
		for (double i = -(0.5*arc); i<(0.5*arc);i++) {
			Vector tempdir = dir.clone().setY(0);
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
		return "Exonerate";
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
			bPlayer.addCooldown(this);
			return;
		}
		
		loc.add((dir).multiply(speed));
		
		for (int i = 0; i<dirs.size(); i++) {
			locs.get(i).add(dirs.get(i).multiply(speed));
		}
		
		for (Location temploc : locs) {
			Random random = new Random();  
			boolean val = random.nextBoolean();  
			if (val) {
				fb = GeneralMethods.spawnFallingBlock(temploc, Material.SOUL_SOIL, Material.SOUL_SOIL.createBlockData());
			}
			fb.setDropItem(false);
			fb.setMetadata("Exonerate", new FixedMetadataValue(ProjectKorra.plugin, this));
			Entity e = Methods.getAffected(temploc, 1, player);
			if (e!=null) {
				DamageHandler.damageEntity(e, 1, this);
		}
		}
		
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
		return "Shake the ground in front of you."; 
	}
	
	@Override
	public String getInstructions() {
		return "Fall from a large height to activate.."; 
	}
	}


	