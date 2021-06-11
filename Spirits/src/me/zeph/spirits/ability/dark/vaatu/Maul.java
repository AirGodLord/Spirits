package me.zeph.spirits.ability.dark.vaatu;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ability.ComboAbility;
import com.projectkorra.projectkorra.ability.CoreAbility;
import com.projectkorra.projectkorra.ability.util.ComboManager.AbilityInformation;
import com.projectkorra.projectkorra.util.ClickType;
import com.projectkorra.projectkorra.util.DamageHandler;

import me.zeph.spirits.Methods;
import me.zeph.spirits.Methods.Spirit;
import me.zeph.spirits.Methods.Usage;
import me.zeph.spirits.SpiritElement;
import me.zeph.spirits.Spirits;
import me.zeph.spirits.ability.api.DarkAbility;
import me.zeph.spirits.ability.api.SpiritAbility;
import me.zeph.spirits.ability.api.VaatuAbility;
import me.zeph.spirits.ability.dark.Vaatu;


public class Maul extends VaatuAbility implements ComboAbility{
	
	//Config variables
	private long cooldown;
	private double speed;
	private int duration;

	//Set variables
	private Location loc;
	private Vector dir;
	private Location origin;
	private Long starttime;
	private Entity e;
	private Location front;
	
	public Maul(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
		
		if (bPlayer.isOnCooldown(this)) {
			return;
		}
		
		if (!CoreAbility.hasAbility(player, Vaatu.class)) {
			player.sendMessage("You need Vaatu");
			return;
		}
		
		if (hasAbility(player,Maul.class)) {
			return;
		}
		setFields();
		
		
		start();
		
	}
		
	private void setFields() {
		
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Dark.Vaatu.Maul.Cooldown");
		this.speed = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Vaatu.Maul.Speed");
		this.duration = Spirits.plugin.getConfig().getInt("Spirit.Dark.Vaatu.Maul.Duration");
		
		this.loc = player.getLocation();
		this.starttime = System.currentTimeMillis();
		Methods.playCircle(loc, 2, 5, Spirit.DARK, Usage.AURA);
	
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
		return "Maul";
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
		
		if (player.isDead() || !player.isOnline()) {
			remove();
			return;
		}
		
		dir = player.getLocation().add(0,1,0).getDirection().normalize();
		player.setVelocity(dir.multiply(speed));
		Methods.playParticles(player.getLocation().add(0,1,0), 4, Spirit.DARK, Usage.MOVE);
		
		front = (player.getLocation().add(0,1,0).add(dir));
		e = Methods.getAffected(front, 1, player);
			
		if (e!=null) {
			e.setVelocity(front.clone().subtract(e.getLocation().add(0,1,0)).toVector().normalize().multiply(speed));;
		}
		
		if (System.currentTimeMillis() > starttime + (duration)) {
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
		return new Maul(player);
	}

	@Override
	public ArrayList<AbilityInformation> getCombination() {
		final ArrayList<AbilityInformation> combo = new ArrayList<>();
		combo.add(new AbilityInformation("DarkFlash", ClickType.SHIFT_DOWN));
		return combo;
	}

	@Override
	public Element getElement() {
		return SpiritElement.VAATU;
	}
	
	@Override
	public String getDescription() {
		return "Send enemies, who get in your way, flying"; 
	}
	
	@Override
	public String getInstructions() {
		return "Tap shift to activate and fly through an entity"; 
	}
		
	}


	