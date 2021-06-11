package me.zeph.spirits.ability.light.combo;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ability.ComboAbility;
import com.projectkorra.projectkorra.ability.CoreAbility;
import com.projectkorra.projectkorra.ability.MultiAbility;
import com.projectkorra.projectkorra.ability.util.ComboManager.AbilityInformation;
import com.projectkorra.projectkorra.ability.util.MultiAbilityManager;
import com.projectkorra.projectkorra.ability.util.MultiAbilityManager.MultiAbilityInfoSub;
import com.projectkorra.projectkorra.util.ClickType;
import com.projectkorra.projectkorra.util.DamageHandler;

import me.zeph.spirits.Methods;
import me.zeph.spirits.Methods.Spirit;
import me.zeph.spirits.Methods.Usage;
import me.zeph.spirits.SpiritElement;
import me.zeph.spirits.Spirits;
import me.zeph.spirits.ability.api.DarkAbility;
import me.zeph.spirits.ability.api.LightAbility;
import me.zeph.spirits.ability.dark.combo.Desecrate;
import me.zeph.spirits.ability.light.multiability.FloatMove;
import me.zeph.spirits.ability.light.multiability.Heal;
import me.zeph.spirits.ability.light.multiability.Invis;
import me.zeph.spirits.ability.light.multiability.Resistance;
import me.zeph.spirits.ability.light.multiability.Speed;
import me.zeph.spirits.config.Config;


public class Enchant extends LightAbility implements MultiAbility, ComboAbility{

	private enum EnchantMode {
		FLOAT, HEAL, INVIS, RESISTANCE, SPEED
	}


	//base variables
	private Location loc;
	private Location loc2;
	private Location toploc;
	private Location toploc2;
	private Location bottomloc;
	private Location bottomloc2;
	private Vector dir;
	private Vector dir2;
	private double angle;
	private double y;
	private double radians;
	private long starttime;
	private static Boolean charged;
	private Boolean haspeaked;
	//Config
	private long cooldown;
	private long chargetime;
	private double heightspeed;
	


	public Enchant(Player player) {
		super(player);

		// TODO Auto-generated constructor stub

		if (bPlayer.isOnCooldown(this)) {
			return;
		}
	
		setFields();

		start();
	
		
	}

	private void setFields() {
		MultiAbilityManager.bindMultiAbility(player, "Enchant");
		this.heightspeed = Spirits.plugin.getConfig().getDouble("Spirit.Light.Enchant.HeightSpeed");
		this.chargetime = Spirits.plugin.getConfig().getLong("Spirit.Light.Enchant.ChargeTime");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Light.Enchant.Cooldown");
		this.angle = 0;
		this.y = 0;
		this.starttime = System.currentTimeMillis();
		this.charged = false;
		this.haspeaked = false;
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
		return "Enchant";
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
		
		if (!player.isSneaking()) {
			remove();
			MultiAbilityManager.unbindMultiAbility(player);
			return;
		}
		
		if (y >= 1) {
			haspeaked = true;
		}
		else if (y<=0){
			haspeaked = false;
		}
		if (System.currentTimeMillis() > chargetime + starttime) {
			charged = true;
		}
		
		
		World world = player.getWorld();
		radians = Math.toRadians(angle);
		this.loc = player.getLocation().add(0,1,0);
		this.loc2 = loc.clone();
		this.dir = loc.getDirection().setY(0).normalize().rotateAroundY(radians);
		this.dir2 = loc.getDirection().setY(0).normalize().rotateAroundY(-radians);
		this.loc.add(dir);
		this.loc2.add(dir2);
		toploc = loc.clone().add(0,y,0);
		toploc2 = loc2.clone().add(0,y,0);
		bottomloc = loc.clone().subtract(0,y,0);
		bottomloc2 = loc2.clone().subtract(0,y,0);
		incrementY();
		angle+=30;
		if (!charged) {
			Methods.playParticles(toploc, 1, Spirit.LIGHT, Usage.SINGLE);
			Methods.playParticles(toploc2, 1, Spirit.LIGHT, Usage.SINGLE);
			Methods.playParticles(bottomloc, 1, Spirit.LIGHT, Usage.SINGLE);
			Methods.playParticles(bottomloc2, 1, Spirit.LIGHT, Usage.SINGLE);
		}
		else {
			Methods.playParticles(toploc, 1, Spirit.LIGHT, Usage.SINGLE2);
			Methods.playParticles(toploc2, 1, Spirit.LIGHT, Usage.SINGLE2);
			Methods.playParticles(bottomloc, 1, Spirit.LIGHT, Usage.SINGLE2);
			Methods.playParticles(bottomloc2, 1, Spirit.LIGHT, Usage.SINGLE2);
		}
	}
		
		
	public void incrementY() {
		if (!haspeaked) {
			y+=heightspeed;
		}
		else if (haspeaked){
			y = y-heightspeed;
		}
	}
	
	public static Boolean charged() {
		return charged;
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
		abils.add(new MultiAbilityInfoSub("Float", SpiritElement.LIGHT));
		abils.add(new MultiAbilityInfoSub("Heal", SpiritElement.LIGHT));
		abils.add(new MultiAbilityInfoSub("Invis", SpiritElement.LIGHT));
		abils.add(new MultiAbilityInfoSub("Resistance", SpiritElement.LIGHT));
		abils.add(new MultiAbilityInfoSub("Speed", SpiritElement.LIGHT));
		return abils;
	}

	@Override
	public Object createNewComboInstance(Player player) {
		return new Enchant(player);
	}

	@Override
	public ArrayList<AbilityInformation> getCombination() {
		final ArrayList<AbilityInformation> combo = new ArrayList<>();
		combo.add(new AbilityInformation("Catalyse", ClickType.LEFT_CLICK));
		combo.add(new AbilityInformation("Lull", ClickType.SHIFT_DOWN));
		return combo;
	}

	@Override
	public String getDescription() {
		return "Apply a choice of buffs to yourself."; 
	}
	
	@Override
	public String getInstructions() {
		return "Catalyse (Left click) >  Lull (Hold shift) and left click on a slot to activate enchant."; 
	}
	
}


