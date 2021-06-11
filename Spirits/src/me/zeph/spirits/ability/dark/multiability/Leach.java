/*
package me.zeph.spirits.ability.dark.multiability;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ability.CoreAbility;
import com.projectkorra.projectkorra.ability.util.MultiAbilityManager;
import com.projectkorra.projectkorra.util.DamageHandler;

import me.zeph.spirits.Methods;
import me.zeph.spirits.Methods.Spirit;
import me.zeph.spirits.Methods.Usage;
import me.zeph.spirits.Spirits;
import me.zeph.spirits.ability.api.DarkAbility;
import me.zeph.spirits.ability.dark.ToxicLash;


public class Leach extends DarkAbility{

	//Config variables
	private int leachgain;
	private double leachloss;
	private int leachslow;
	private double leachreach;
	private long leachcooldown;
	private long leachduration;
	private double length;
	
	//Set variables
	private Location loc;
	private Location loc2;
	private Vector dir;
	private Location origin;
	private Boolean hasgrabbed;
	private static Entity e;
	private long starttime;
	private Location currentloc;
	private Boolean hasdamaged;
	private Boolean fullextension;
	private static double currentextension;
	
	public Leach(Player player) {
		super(player);
		// TODO Auto-generated constructor stub

		if (bPlayer.isOnCooldown(this)) {
			return;
		}


		setFields();


		start();

	}

	private void setFields() {


		this.length = Spirits.plugin.getConfig().getDouble("Spirit.Dark.ToxicLash.Length");
		
		this.leachgain = Spirits.plugin.getConfig().getInt("Spirit.Dark.ToxicLash.LeachGain");
		this.leachloss = Spirits.plugin.getConfig().getDouble("Spirit.Dark.ToxicLash.LeachLoss");
		this.leachslow = Spirits.plugin.getConfig().getInt("Spirit.Dark.ToxicLash.LeachSlow");
		this.leachreach = Spirits.plugin.getConfig().getDouble("Spirit.Dark.ToxicLash.LeachReach");
		this.leachcooldown = Spirits.plugin.getConfig().getLong("Spirit.Dark.ToxicLash.LeachCooldown");
		this.leachduration = Spirits.plugin.getConfig().getLong("Spirit.Dark.ToxicLash.LeachDuration");
		
		this.loc = player.getLocation().add(0,1,0);
		this.dir = loc.getDirection().normalize();
		this.origin = loc.clone();
		this.starttime = System.currentTimeMillis();
		this.hasdamaged = false;
		this.hasgrabbed = false;
		this.fullextension = false;

	}		

	public long getCooldown() {
		// TODO Auto-generated method stub
		return 0;
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
		
		if (System.currentTimeMillis() > starttime + leachduration) {
			remove();
			bPlayer.addCooldown(this);
			return;
		}

		if (player.getInventory().getHeldItemSlot() != 2) {
			remove();
			return;
		}
		
		if (e!=null) {
			if (e.getLocation().distance(player.getLocation()) > length+leachreach) {
				this.remove();
				bPlayer.addCooldown(this);
				return;
			}
		}

		this.loc = GeneralMethods.getMainHandLocation(player);
		this.dir = loc.getDirection().normalize();

		if (!fullextension) {
			currentextension+=0.01;
		}
		else if (fullextension) {
			currentextension+=-0.01;
		}
		if (currentextension>leachreach) {
			fullextension = true;
		}

		currentloc = loc2.clone().add(dir.clone().multiply(currentextension));

		if (!hasdamaged) {
			e = Methods.getAffected(currentloc, 1, player);
			if (e!=null) {
				hasdamaged = true;
			}
		}

		if (e!=null) {
			((LivingEntity) e).addPotionEffect(PotionEffectType.SLOW.createEffect(20, leachslow));
			DamageHandler.damageEntity(e, leachloss, this);
			player.addPotionEffect(PotionEffectType.REGENERATION.createEffect(20, leachgain));
		}

	}


	public static double extension() {
		return currentextension;
	}
	
	public static Entity leachReturn() {
		return e;
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


