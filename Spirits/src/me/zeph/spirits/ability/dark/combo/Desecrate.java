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
import com.projectkorra.projectkorra.ability.CoreAbility;
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
import me.zeph.spirits.ability.dark.DarkTether;


public class Desecrate extends DarkAbility implements ComboAbility{

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
	private int point;
	private Vector direction;
	private Boolean hasspawned;
	private long start;
	private int shots;
	private Boolean checkincrement = false;
	private double yaw;
	
	public Desecrate(Player player) {
		super(player);
		// TODO Auto-generated constructor stub

		if (bPlayer.isOnCooldown("Desecrate")) {
			return;
		}
		
		if (hasAbility(player,Desecrate.class)) {
			return;
		}
		
		setFields();

		start();

	}

	private void setFields() {
		//add to config below
		this.speed = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Desecrate.Speed");
		this.range = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Desecrate.Range");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Dark.Desecrate.Cooldown");
		this.duration = Spirits.plugin.getConfig().getLong("Spirit.Dark.Desecrate.Duration");
		this.damage = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Desecrate.Damage");
		this.hitbox = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Desecrate.Hitbox");
		this.radius = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Desecrate.Radius");

		this.hasspawned = false;
		this.shots = 0;

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
		return "Desecrate";
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
		if (shots > 3) {
			remove();
			bPlayer.addCooldown(this);
			return;
		}
		
		//Methods.playCircle(player.getLocation().add(0,1,0), radius, 30, Spirit.DARK, Usage.BALL2);
		if (bPlayer.isOnCooldown("DesecrateShots") && !checkincrement) {
			shots++;
			checkincrement = true;
		}
		
		if (CoreAbility.hasAbility(player, DesecrateShots.class)) {
			checkincrement = false;
		}
		
		location = player.getLocation().subtract(0,1,0);
		direction = location.getDirection().normalize();
		playParticles();
		
		
	}
	
	private void playParticles() {
		Location fakeLoc = location.clone();
		fakeLoc.setPitch(0);
		fakeLoc.setYaw((float) (yaw += 40));
		Vector direction = fakeLoc.getDirection();

		point++;
		Location location = this.location.clone().add(0, 2, 0);
		for (int i = -180; i < 180; i += 30) {
			double angle = (i * Math.PI / 180);
			double xRotation = 3.141592653589793D / 3 * 2.1;
			Vector v = new Vector(Math.cos(angle + point), Math.sin(angle + point), 0.0D).multiply(2);
			Vector v1 = v.clone();
			rotateAroundAxisX(v, xRotation);
			rotateAroundAxisY(v, -((location.getYaw() * Math.PI / 180) - 1.575));
			rotateAroundAxisX(v1, -xRotation);
			rotateAroundAxisY(v1, -((location.getYaw() * Math.PI / 180) - 1.575));

			if (shots < 1) {
				Methods.playParticles(location.clone().add(v), 1, Spirit.DARK, Usage.SINGLE);
				//ParticleEffect.TOWN_AURA.display(location.clone().add(v), 3, 0, 0, 0);
				
			}
	

			if (shots < 2 ) {
				Methods.playParticles(location.clone().add(v1), 1, Spirit.DARK, Usage.SINGLE);
				//ParticleEffect.TOWN_AURA.display(location.clone().add(v1), 3, 0, 0, 0);
				
			}
		}
			
	}

	private Vector rotateAroundAxisX(Vector v, double angle) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		double y = v.getY() * cos - v.getZ() * sin;
		double z = v.getY() * sin + v.getZ() * cos;
		return v.setY(y).setZ(z);
	}

	private Vector rotateAroundAxisY(Vector v, double angle) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		double x = v.getX() * cos + v.getZ() * sin;
		double z = v.getX() * -sin + v.getZ() * cos;
		return v.setX(x).setZ(z);
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
		return new Desecrate(player);
	}

	@Override
	public ArrayList<AbilityInformation> getCombination() {
		final ArrayList<AbilityInformation> combo = new ArrayList<>();
		combo.add(new AbilityInformation("SoulPunch", ClickType.SHIFT_DOWN));
		combo.add(new AbilityInformation("SoulPunch", ClickType.SHIFT_UP));
		combo.add(new AbilityInformation("SoulPunch", ClickType.SHIFT_DOWN));
		combo.add(new AbilityInformation("Infiltrate", ClickType.SHIFT_UP));
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


