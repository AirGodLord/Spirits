package me.zeph.spirits.ability.dark.combo;
//this is what shoots Desecrate

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ability.ComboAbility;
import com.projectkorra.projectkorra.ability.CoreAbility;
import com.projectkorra.projectkorra.ability.util.ComboManager.AbilityInformation;
import com.projectkorra.projectkorra.util.ClickType;
import com.projectkorra.projectkorra.util.DamageHandler;

import me.zeph.spirits.Methods;
import me.zeph.spirits.Methods.Spirit;
import me.zeph.spirits.Methods.Usage;
import me.zeph.spirits.Spirits;
import me.zeph.spirits.ability.api.DarkAbility;
import me.zeph.spirits.ability.dark.combo.Desecrate;


public class DesecrateShots extends DarkAbility implements ComboAbility{

	//Config variables
	private double range;
	private long cooldown;
	private double damage;
	private double speed;
	private double hitbox;
	private int blindduration;

	//Set variables
	private Location loc;
	private Location origin;
	private Vector dir;
	private Location temploc;
	private Location temploc2;
	private double angle;


	public DesecrateShots(Player player) {
		super(player);
		// TODO Auto-generated constructor stub

		if ((CoreAbility.hasAbility(player, Desecrate.class))) {
			setFields();
			start();
		}
		else {
			return;
		}

	}

	private void setFields() {


		this.damage = Spirits.plugin.getConfig().getDouble("Spirit.Dark.DesecrateProjectiles.Damage");
		this.range = Spirits.plugin.getConfig().getDouble("Spirit.Dark.DesecrateProjectiles.Range");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Dark.DesecrateProjectiles.Cooldown");
		this.speed = Spirits.plugin.getConfig().getDouble("Spirit.Dark.DesecrateProjectiles.Speed");
		this.hitbox = Spirits.plugin.getConfig().getDouble("Spirit.Dark.DesecrateProjectiles.Hitbox");
		this.blindduration = Spirits.plugin.getConfig().getInt("Spirit.Dark.DesecrateProjectiles.BlindDuration");

		this.loc = player.getLocation().add(0,1,0);
		this.origin = loc.clone();
		this.angle = 0;

		

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
		return "DesecrateShots";
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

		
		dir = loc.getDirection().normalize();
		loc.add(dir.multiply(speed));
		temploc = loc.clone().add(new Vector(0,0.5,0).rotateAroundAxis(dir, Math.toRadians(angle)));
		temploc2 = loc.clone().add(new Vector(0,0.5,0).rotateAroundAxis(dir, Math.toRadians(-angle)));
		List<Location>locs = Arrays.asList(loc,temploc,temploc2);
		
		for (Location location : locs) {
			Methods.playParticles(location, 2, Spirit.DARK, Usage.SINGLE);
			Entity e = Methods.getAffected(loc, hitbox, player);
			if (e!=null) {
				DamageHandler.damageEntity(e, damage, this);
				Methods.applyPotion(e, PotionEffectType.BLINDNESS, blindduration);
			}
		
			if (GeneralMethods.isSolid(loc.getBlock())) {
				remove();
				bPlayer.addCooldown(this);
				return;
			}

			if (loc.distance(origin)>range) {
				remove();
				bPlayer.addCooldown(this);
				return;
			}
		}
		angle+=45;
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
		return new DesecrateShots(player);
	}

	@Override
	public ArrayList<AbilityInformation> getCombination() {
		final ArrayList<AbilityInformation> combo = new ArrayList<>();
		combo.add(new AbilityInformation("Infiltrate", ClickType.LEFT_CLICK));
		return combo;
	}

	@Override
	public String getDescription() {
		return "Shows the shots for desecrate.."; 
	}
	
	@Override
	public String getInstructions() {
		return "Left click with infiltrate to fire shots."; 
	}
}


