/*
package me.zeph.spirits.ability.dark.multiability;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ability.CoreAbility;
import com.projectkorra.projectkorra.ability.util.MultiAbilityManager;
import com.projectkorra.projectkorra.ability.util.MultiAbilityManager.MultiAbilityInfoSub;
import com.projectkorra.projectkorra.util.DamageHandler;

import me.zeph.spirits.Methods;
import me.zeph.spirits.Methods.Spirit;
import me.zeph.spirits.Methods.Usage;
import me.zeph.spirits.Spirits;
import me.zeph.spirits.ability.api.DarkAbility;
import me.zeph.spirits.ability.dark.ToxicLash;


public class Slam extends DarkAbility{

	//Config variables
	private static double slamreach;
	private double slamdamage;
	private long slamcooldown;
	private long slamthrowinterval;
	private double slamthrowstrength;
	//private int slamuses;

	//Set variables
	private Location loc;
	private Location loc2;
	private Vector dir;
	private Location currentloc;
	private Boolean hasdamaged;
	private static Entity e;
	private Boolean fullextension;
	private static double currentextension;
	private long starttime;
	
	public Slam(Player player) {
		super(player);
		// TODO Auto-generated constructor stub

		if (bPlayer.isOnCooldown(this)) {
			return;
		}

		if (CoreAbility.hasAbility(player, Slam.class)) {
			return;
		}

		setFields();


		start();

	}

	private void setFields() {


		this.slamreach = Spirits.plugin.getConfig().getDouble("Spirit.Dark.ToxicLash.SlamReach");
		this.slamdamage = Spirits.plugin.getConfig().getDouble("Spirit.Dark.ToxicLash.SlamDamage");
		this.slamcooldown = Spirits.plugin.getConfig().getLong("Spirit.Dark.ToxicLash.SlamCooldown");
		this.slamthrowinterval = Spirits.plugin.getConfig().getLong("Spirit.Dark.ToxicLash.SlamThrowInterval");
		this.slamthrowstrength = Spirits.plugin.getConfig().getDouble("Spirit.Dark.ToxicLash.SlamThrowStrength");
		//this.Slamuses = Spirits.plugin.getConfig().getInt("Spirit.Dark.ToxicLash.SlamUses");

		this.loc2 = ToxicLash.getTip();
		this.fullextension = false;
		this.hasdamaged = false;
		this.currentextension = 0;
	}		

	public long getCooldown() {
		// TODO Auto-generated method stub
		return slamcooldown;
	}

	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "ToxicLash";
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
		if (!MultiAbilityManager.hasMultiAbilityBound(this.player, "ToxicLash")) {
			this.remove();
			return;
		}

		if (currentextension<0) {
			this.remove();
			bPlayer.addCooldown(this);
			return;
		}

		this.loc = GeneralMethods.getMainHandLocation(player);
		this.dir = loc.getDirection().normalize();

			if (!fullextension) {
				currentextension+=0.01;
			}
			else if (fullextension) {
				currentextension+=-0.01;
			}
			if (currentextension>slamreach) {
				fullextension = true;
			}
		
		currentloc = loc2.clone().add(dir.clone().multiply(currentextension));

		if (!hasdamaged) {
			e = Methods.getAffected(currentloc, 1, player);
			if (e!=null) {
				DamageHandler.damageEntity(e, slamdamage, this);
				hasdamaged = true;
				this.starttime = System.currentTimeMillis();
			}
		}
	
		if (e!=null && System.currentTimeMillis() > starttime + slamthrowinterval) {
			e.setVelocity(player.getLocation().getDirection().normalize().multiply(slamthrowstrength));
			e = null;
			bPlayer.addCooldown(this);
			this.remove();
			return;
		}
	}

	
	public static double extension() {
		return currentextension;
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

}

*/
