/*
package me.zeph.spirits.ability.dark.multiability;

import org.bukkit.Location;
import org.bukkit.block.Block;
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


public class Tendril extends DarkAbility{
	
	//Config variables
	private static double tendrilrange;
	private long tendrilcooldown;

	//Set variables
	private Location loc;
	private Location loc2;
	private Vector dir;
	private Location currentloc;
	private Boolean hasgrabbed;
	private Block block;
	private Boolean fullextension;
	private double currentextension;
	
	
	public Tendril(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
		
		if (bPlayer.isOnCooldown(this)) {
			return;
		}
		
		if (CoreAbility.hasAbility(player, Tendril.class)) {
			return;
		}
		
		if (CoreAbility.hasAbility(player, Strike.class)) {
			bPlayer.addCooldown(CoreAbility.getAbility(Strike.class));
		}
		setFields();
		
		
		start();
		
	}
		
	private void setFields() {
		
	
		this.tendrilrange = Spirits.plugin.getConfig().getDouble("Spirit.Dark.ToxicLash.TendrilRange");
		this.tendrilcooldown = Spirits.plugin.getConfig().getLong("Spirit.Dark.ToxicLash.TendrilCooldown");
		//this.Tendriluses = Spirits.plugin.getConfig().getInt("Spirit.Dark.ToxicLash.TendrilUses");
		
		this.loc2 = ToxicLash.getTip();
		this.fullextension = false;
		this.hasgrabbed = false;
		this.currentextension = 0;
	}		

	public long getCooldown() {
		// TODO Auto-generated method stub
		return 3000;
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
			return;
		}
		
		this.loc = GeneralMethods.getMainHandLocation(player);
		this.dir = loc.getDirection().normalize();
		
		for (double i = 0; i<currentextension;i++) {
			Methods.playParticles(loc2.clone().add(dir.clone().multiply(i)), 1, Spirit.DARK, Usage.SINGLE);
		}
		
		if (!fullextension) {
			currentextension+=0.01;
		}
		else if (fullextension) {
			currentextension+=-0.01;
		}
		if (currentextension>tendrilrange) {
			fullextension = true;
		}
		
		currentloc = loc2.clone().add(dir.clone().multiply(currentextension));
		
		if (!hasgrabbed) {
			block = currentloc.getBlock();
			if (GeneralMethods.isSolid(block)) {
				hasgrabbed = true;
			}
		}
	
		if (hasgrabbed) {
			player.setVelocity(dir);
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
		
	}

*/
	