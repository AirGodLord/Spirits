/*package me.zeph.spirits.ability.spirit.combo;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ProjectKorra;
import com.projectkorra.projectkorra.ability.AddonAbility;
import com.projectkorra.projectkorra.ability.AirAbility;
import com.projectkorra.projectkorra.ability.ComboAbility;
import com.projectkorra.projectkorra.ability.CoreAbility;
import com.projectkorra.projectkorra.ability.util.ComboManager.AbilityInformation;
import com.projectkorra.projectkorra.airbending.AirSpout;
import com.projectkorra.projectkorra.util.ClickType;
import com.projectkorra.projectkorra.util.DamageHandler;
import com.projectkorra.projectkorra.util.ParticleEffect;

import me.zeph.spirits.Methods;
import me.zeph.spirits.Methods.Spirit;
import me.zeph.spirits.Methods.Usage;
import me.zeph.spirits.SpiritElement;
import me.zeph.spirits.Spirits;
import me.zeph.spirits.ability.api.SpiritAbility;


public class Rummage extends SpiritAbility implements ComboAbility{
	
	//Config variables, to set
	private double speed;
	private double range;
	private long cooldown;
	private double damage;	
	private double hitbox;
	private int width;
	
	//Set variables
	private Location location;
	private Location origin;
	private Entity e;
	private Vector direction;
	private Vector orthogonal;
	
	public Rummage(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
		
		if (bPlayer.isOnCooldown("Rummage")) {
			return;
		}
		
		setFields();
		
		start();
		
	}
		
	private void setFields() {
		//add to config below
		this.speed = Spirits.plugin.getConfig().getDouble("Spirit.Neutral.Rummage.Speed");
		this.range = Spirits.plugin.getConfig().getDouble("Spirit.Neutral.Rummage.Range");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Neutral.Rummage.Cooldown");
		this.damage = Spirits.plugin.getConfig().getDouble("Spirit.Neutral.Rummage.Damage");
		this.hitbox = Spirits.plugin.getConfig().getDouble("Spirit.Neutral.Rummage.Hitbox");
		this.width = Spirits.plugin.getConfig().getInt("Spirit.Neutral.Rummage.Width");
		this.location = player.getLocation().add(0,1,0);
		
		this.origin = location.clone();
		
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
		return "";
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
		
		direction = player.getLocation().getDirection().normalize();
		location.add(direction.multiply(speed));
		location.add(0,Methods.getWave(location, origin, 10, 1),0);
		
		for (double i =-width/2; i<width/2 ; i = i + 0.2) {
			orthogonal = new Vector(direction.getZ(), 0, -direction.getX()).normalize();
			Location temploc = location.clone().add(orthogonal.multiply(i));
			e = Methods.getAffected(temploc, hitbox, player);
			if (e instanceof Player) {
				Player target = (Player) e;
				if (CoreAbility.hasAbility(target, AirSpout.class)) {
					CoreAbility.getAbility(target, AirSpout.class).remove();
				}
			}
			Methods.playParticles(temploc, 5, Spirit.NEUTRAL, Usage.BALL2);
			DamageHandler.damageEntity(e, damage, this);
			}
		
	
	if (location.distance(origin) > range) {
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
		return new Rummage(player);
	}

	@Override
	public ArrayList<AbilityInformation> getCombination() {
		final ArrayList<AbilityInformation> combo = new ArrayList<>();
		combo.add(new AbilityInformation("Pulse", ClickType.SHIFT_DOWN));
		combo.add(new AbilityInformation("Pulse", ClickType.LEFT_CLICK));
		combo.add(new AbilityInformation("Pulse", ClickType.SHIFT_UP));
		return combo;
	}
	
	@Override
	public String getDescription() {
		return "Rip through your enemies."; 
	}
	
	@Override
	public String getInstructions() {
		return "SoulPunch (Hold shift) >  SoulPunch (Release shift) > SoulPunch (Hold shift) > Infiltrate (Release shift). Left click with infiltrate to fire shots."; 
	}
	}


	*/