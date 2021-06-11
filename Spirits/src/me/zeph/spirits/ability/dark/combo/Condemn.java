package me.zeph.spirits.ability.dark.combo;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ProjectKorra;
import com.projectkorra.projectkorra.ability.AddonAbility;
import com.projectkorra.projectkorra.ability.AirAbility;
import com.projectkorra.projectkorra.ability.ComboAbility;
import com.projectkorra.projectkorra.ability.util.ComboManager.AbilityInformation;
import com.projectkorra.projectkorra.util.ClickType;
import com.projectkorra.projectkorra.util.DamageHandler;
import com.projectkorra.projectkorra.util.ParticleEffect;

import me.zeph.spirits.Methods;
import me.zeph.spirits.Methods.Spirit;
import me.zeph.spirits.Methods.Usage;
import me.zeph.spirits.SpiritElement;
import me.zeph.spirits.Spirits;
import me.zeph.spirits.ability.api.DarkAbility;
import me.zeph.spirits.ability.api.SpiritAbility;


public class Condemn extends DarkAbility implements ComboAbility{
	
	//Config variables, to set
	private double speed;
	private double range;
	private long cooldown;
	private double damage;	
	private double hitbox;
	private double radius;
	private long duration;
	
	//Set variables
	private Location location;
	private Location eloc;
	private Location origin;
	private Entity e;
	private Vector direction;
	private Boolean hasspawned;
	private long circlestart;
	
	public Condemn(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
		
		if (bPlayer.isOnCooldown("Condemn")) {
			return;
		}
		
		setFields();
		
		start();
		
	}
		
	private void setFields() {
		//add to config below
		this.speed = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Condemn.Speed");
		this.range = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Condemn.Range");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Dark.Condemn.Cooldown");
		this.duration = Spirits.plugin.getConfig().getLong("Spirit.Dark.Condemn.Duration");
		this.damage = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Condemn.Damage");
		this.hitbox = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Condemn.Hitbox");
		this.radius = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Condemn.Radius");
		this.location = player.getLocation().add(0,1,0);
		this.direction = location.getDirection().normalize();
		
		this.origin = location.clone();
		this.hasspawned = false;
		
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
		return "Condemn";
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
		
		if (e==null) {
			location.add(direction.multiply(speed));
			Methods.playParticles(location, 1, Spirit.DARK, Usage.FLAME);
			e = Methods.getAffected(location, hitbox, player);
		}
		
		if (e!=null) {
			if (!hasspawned) {
				circlestart = System.currentTimeMillis();
				eloc = e.getLocation();
				hasspawned = true;
			}
			Methods.playPolygon(eloc, 3, 5, Spirit.DARK, Usage.FLAME);
			eloc.setPitch(e.getLocation().getPitch());
			eloc.setYaw(e.getLocation().getYaw());
			e.teleport(eloc);
		}
				
	if (location.distance(origin) > range) {
		remove();
		return;
	}
		
	if (hasspawned && System.currentTimeMillis() > circlestart + duration) {
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
	public Object createNewComboInstance(Player player) {
		return new Condemn(player);
	}

	@Override
	public ArrayList<AbilityInformation> getCombination() {
		final ArrayList<AbilityInformation> combo = new ArrayList<>();
		combo.add(new AbilityInformation("DarkTether", ClickType.SHIFT_DOWN));
		combo.add(new AbilityInformation("SoulPunch", ClickType.SHIFT_UP));
		return combo;
	}
	
	@Override
	public String getDescription() {
		return "Condemn opponents to a temporary hellish prison."; 
	}
	
	@Override
	public String getInstructions() {
		return "DarkTether (Hold shift) >  SoulPunch (Release shift)"; 
	}
		
	}


	