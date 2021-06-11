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


public class Strike extends DarkAbility{

	//Config variables
	private static double strikereach;
	private double strikedamage;
	private long strikecooldown;
	//private int strikeuses;

	//Set variables
	private Location loc;
	private Location loc2;
	private Vector dir;
	private Location currentloc;
	private Boolean hasdamaged;
	private static Entity e;
	private Boolean fullextension;
	private static double currentextension;

	public Strike(Player player) {
		super(player);
		// TODO Auto-generated constructor stub

		if (bPlayer.isOnCooldown(this)) {
			return;
		}

		if (CoreAbility.hasAbility(player, Strike.class)) {
			return;
		}

		setFields();


		start();

	}

	private void setFields() {


		this.strikereach = Spirits.plugin.getConfig().getDouble("Spirit.Dark.ToxicLash.StrikeReach");
		this.strikedamage = Spirits.plugin.getConfig().getDouble("Spirit.Dark.ToxicLash.StrikeDamage");
		this.strikecooldown = Spirits.plugin.getConfig().getLong("Spirit.Dark.ToxicLash.StrikeCooldown");
		//this.strikeuses = Spirits.plugin.getConfig().getInt("Spirit.Dark.ToxicLash.StrikeUses");

		this.loc2 = ToxicLash.getTip();
		this.fullextension = false;
		this.hasdamaged = false;
		this.currentextension = 0;
	}		

	public long getCooldown() {
		// TODO Auto-generated method stub
		return strikecooldown;
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

		if (currentextension<0 && e == null) {
			this.remove();
			bPlayer.addCooldown(this);
			return;
		}

		this.loc = GeneralMethods.getMainHandLocation(player);
		this.dir = loc.getDirection().normalize();

			if (!fullextension) {
				currentextension+=0.01;
			}
			else if (fullextension && currentextension>0) {
				currentextension+=-0.01;
			}
			if (currentextension>strikereach) {
				fullextension = true;
			}
		
		currentloc = loc2.clone().add(dir.clone().multiply(currentextension));

		if (!hasdamaged) {
			e = Methods.getAffected(currentloc, 1, player);
			if (e!=null) {
				DamageHandler.damageEntity(e, strikedamage, this);
				hasdamaged = true;
			}
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

