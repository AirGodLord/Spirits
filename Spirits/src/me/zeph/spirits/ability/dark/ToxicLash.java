/*
package me.zeph.spirits.ability.dark;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ability.CoreAbility;
import com.projectkorra.projectkorra.ability.MultiAbility;
import com.projectkorra.projectkorra.ability.util.MultiAbilityManager;
import com.projectkorra.projectkorra.ability.util.MultiAbilityManager.MultiAbilityInfoSub;
import com.projectkorra.projectkorra.util.DamageHandler;

import me.zeph.spirits.Methods;
import me.zeph.spirits.Methods.Spirit;
import me.zeph.spirits.Methods.Usage;
import me.zeph.spirits.SpiritElement;
import me.zeph.spirits.Spirits;
import me.zeph.spirits.ability.api.DarkAbility;
import me.zeph.spirits.ability.dark.multiability.Leach;
import me.zeph.spirits.ability.dark.multiability.Slam;
import me.zeph.spirits.ability.dark.multiability.Strike;
import me.zeph.spirits.ability.dark.multiability.Tendril;


public class ToxicLash extends DarkAbility implements MultiAbility{

	private enum ToxicLashMode {
		STRIKE, ROOT, LEACH, TENDRIL, CANCEL
	}


	//Config variables
	//Base
	private double length;
	private long cooldown;

	//Strike
	private double strikereach;
	private double strikedamage;
	private int strikeuses;

	//Root
	private long rootduration;
	private double rootradius;

	//Leach
	private double leachgain;
	private double leachloss;
	private double leachslow;

	//Tendril
	private double tendrilrange;
	private int tendriluses;

	//Set variables
	private Location loc;
	private Boolean hasgrabbed;
	private static Location loc2;
	private Vector dir;
	private Location origin;
	private ToxicLashMode mode;
	private List<Location>locs;
	private Entity e;


	public ToxicLash(Player player) {
		super(player);
		// TODO Auto-generated constructor stub

		if (!bPlayer.canBend(this)) {
			return;
		}

		if (bPlayer.isOnCooldown(this)) {
			return;
		}
		
		e = null;
		
		MultiAbilityManager.bindMultiAbility(player, "ToxicLash");
		setFields();


		start();
	}

	private void setFields() {
		this.hasgrabbed = false;
		this.locs = new ArrayList<Location>();
		//Base
		this.length = Spirits.plugin.getConfig().getDouble("Spirit.Dark.ToxicLash.Length");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Dark.ToxicLash.Cooldown");

		
		//Strike
		this.strikereach = Spirits.plugin.getConfig().getDouble("Spirit.Dark.ToxicLash.StrikeReach");
		this.strikedamage = Spirits.plugin.getConfig().getDouble("Spirit.Dark.ToxicLash.StrikeDamage");
		this.strikeuses = Spirits.plugin.getConfig().getInt("Spirit.Dark.ToxicLash.StrikeUses");

		//Root
		this.rootduration = Spirits.plugin.getConfig().getLong("Spirit.Dark.ToxicLash.RootDuration");
		this.rootradius = Spirits.plugin.getConfig().getDouble("Spirit.Dark.ToxicLash.RootRadius");


		//Leach
		this.leachgain = Spirits.plugin.getConfig().getDouble("Spirit.Dark.ToxicLash.LeachGain");
		this.leachloss = Spirits.plugin.getConfig().getDouble("Spirit.Dark.ToxicLash.LeachLoss");
		this.leachslow = Spirits.plugin.getConfig().getDouble("Spirit.Dark.ToxicLash.LeachSlow");



		//Tendril
		this.tendrilrange = Spirits.plugin.getConfig().getDouble("Spirit.Dark.ToxicLash.TendrilRange");
		this.tendriluses = Spirits.plugin.getConfig().getInt("Spirit.Dark.ToxicLash.TendrilUses");
		 

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
		return true;
	}

	@Override
	public void progress() {
		
		if (player.isDead() || !player.isOnline()) {
			remove();
			return;
		}

		this.length = Spirits.plugin.getConfig().getDouble("Spirit.Dark.ToxicLash.Length");
		this.loc = GeneralMethods.getMainHandLocation(player);
		this.dir = loc.getDirection().normalize();
	
		loc2 = loc.clone().add(dir.clone().multiply(length+Strike.extension()));
		loc2 = loc.clone().add(dir.clone().multiply(length+Slam.extension()));
		loc2 = loc.clone().add(dir.clone().multiply(length+Leach.extension()));
	
		e = Leach.leachReturn();
		if (e != null) {
			loc2 = e.getLocation().add(0,1,0);
		}
		
		length = length+Strike.extension()+Leach.extension()+Slam.extension();

		for (double i = 0; i<length;i+=0.2) {
			Location temploc = loc.clone().add(dir.clone().multiply(i));
			locs.add(temploc);
		}
		
		for (Location temploc : locs) {
			Methods.playParticles(temploc, 1, Spirit.DARK, Usage.SINGLE);
		}
		
		locs.clear();
		}
		
	

	public static Location getTip() {
		return loc2;
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
	public ArrayList<MultiAbilityInfoSub> getMultiAbilities() {
		final ArrayList<MultiAbilityInfoSub> abils = new ArrayList<>();
		abils.add(new MultiAbilityInfoSub("Strike", SpiritElement.DARK));
		abils.add(new MultiAbilityInfoSub("Slam", SpiritElement.DARK));
		abils.add(new MultiAbilityInfoSub("Leach", SpiritElement.DARK));
		abils.add(new MultiAbilityInfoSub("Tendril", SpiritElement.DARK));
		abils.add(new MultiAbilityInfoSub("Cancel", SpiritElement.DARK));
		return abils;
	}

	@Override
	public String getDescription() {
		return "Toss around your feeble opponents or suck the life out of them."; 
	}
	
	@Override
	public String getInstructions() {
		return "Left click to use ToxicLash."; 
	}
}
*/

