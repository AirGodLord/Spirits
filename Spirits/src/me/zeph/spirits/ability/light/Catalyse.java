package me.zeph.spirits.ability.light;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ability.Ability;
import com.projectkorra.projectkorra.ability.CoreAbility;
import com.projectkorra.projectkorra.util.DamageHandler;

import me.zeph.spirits.Methods;
import me.zeph.spirits.Methods.Spirit;
import me.zeph.spirits.Methods.Usage;
import me.zeph.spirits.Spirits;
import me.zeph.spirits.ability.api.LightAbility;


public class Catalyse extends LightAbility{

	//Config variables
	private double radius;
	private long cooldown;
	private int points;
	private double damage;
	private double hitbox;
	private long chargetime;
	private double speed;

	//Set variables
	private double currentradius;
	private Location loc;
	private Entity e;
	private long starttime;
	private Boolean charged;
	private Boolean grabbedloc;

	public Catalyse(Player player) {
		super(player);
		// TODO Auto-generated constructor stub

		if (bPlayer.isOnCooldown(this)) {
			return;
		}


		setFields();


		start();

	}

	private void setFields() {

		this.points = Spirits.plugin.getConfig().getInt("Spirit.Light.Catalyse.Points");
		this.radius = Spirits.plugin.getConfig().getDouble("Spirit.Light.Catalyse.Radius");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Light.Catalyse.Cooldown");
		this.damage = Spirits.plugin.getConfig().getDouble("Spirit.Light.Catalyse.Damage");
		this.hitbox = Spirits.plugin.getConfig().getDouble("Spirit.Light.Catalyse.Hitbox");
		this.chargetime = Spirits.plugin.getConfig().getLong("Spirit.Light.Catalyse.ChargeTime");
		this.speed = Spirits.plugin.getConfig().getDouble("Spirit.Light.Catalyse.Speed");
		

		this.currentradius = 0;
		
		this.starttime = System.currentTimeMillis();
		this.charged = false;
		this.grabbedloc = false;
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
		return "Catalyse";
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

		if (!charged && !player.isSneaking()) {
			remove();
			return;
		}

		if (currentradius>radius) {
			remove();
			bPlayer.addCooldown(this);
			return;
		}
		if (System.currentTimeMillis() > starttime + chargetime) {
			charged = true;
			if (!grabbedloc) {
			Methods.playParticles(player.getLocation().add(0,1,0).add(player.getLocation().getDirection().normalize()), 1, Spirit.NEUTRAL, Usage.CHARGED);
		}

		if (charged && !player.isSneaking()) {
			if (!grabbedloc) {
				this.loc = player.getLocation();
				grabbedloc = true;
			}
			for (Location temploc : Methods.getSphere(loc, currentradius, points)) {
				Methods.playParticles(temploc, 1, Spirit.LIGHT, Usage.BALL2);
			}
			for (Entity e : GeneralMethods.getEntitiesAroundPoint(loc, currentradius)) {
					if (e instanceof Player) {
						BendingPlayer targeted = bPlayer.getBendingPlayer((Player) e);
						for (String abil : targeted.getAbilities().values()) {
							if (abil != null) {
								targeted.addCooldown(CoreAbility.getAbility(abil));
							}
						
					}
			}
				}
			}
			currentradius+=speed;
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
		return "Stop enemies from getting any closer.";
	}
	
	@Override
	public String getInstructions() {
		return "Hold shift until charged, then release."; 
	}
}


