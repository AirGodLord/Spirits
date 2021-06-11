package me.zeph.spirits.ability.dark;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Location;

import org.bukkit.entity.Player;

import com.jedk1.jedcore.scoreboard.BendingBoard;
import com.projectkorra.projectkorra.ability.CoreAbility;

import me.zeph.spirits.Methods;
import me.zeph.spirits.SpiritElement;
import me.zeph.spirits.Methods.Spirit;
import me.zeph.spirits.Methods.Usage;
import me.zeph.spirits.Spirits;
import me.zeph.spirits.ability.api.DarkAbility;


public class Vaatu extends DarkAbility{
	
	//Config variables
	private double radius;
	private long duration;
	private long cooldown;
	private int points;

	//Set variables
	private Long starttime;
	private Long timeleft;
	private double i;
	private HashMap<Integer, String>abilities;

	public Vaatu(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
		
		if (bPlayer.isOnCooldown(this)) {
			return;
		}
		
		if (CoreAbility.hasAbility(player, Vaatu.class)) {
			return;
		}
		
		bPlayer.addSubElement(SpiritElement.VAATU);
		abilities = bPlayer.getAbilities();
		HashMap<Integer, String> newabilities = (HashMap<Integer, String>) abilities.clone();
		
		for (int i = 0; i < 10 ; i++) {
			newabilities.replace(i, "Possess", "Corrupt");
			newabilities.replace(i, "Dash", "DarkFlash");
		}
		bPlayer.setAbilities(newabilities);
		BendingBoard.update(player);
		setFields();
		
		
		start();
		
	}
		
	private void setFields() {
		
		this.points = Spirits.plugin.getConfig().getInt("Spirit.Dark.Vaatu.Points");
		this.radius = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Vaatu.Radius");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Dark.Vaatu.Cooldown");
		this.duration = Spirits.plugin.getConfig().getLong("Spirit.Dark.Vaatu.Duration");
		
		this.starttime = System.currentTimeMillis();
		this.i = 0;
		
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
		return "Vaatu";
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
			bPlayer.addCooldown(this);
			return;
		}
		 timeleft = (starttime+duration) - System.currentTimeMillis();
		
		if (timeleft<0) {
			bPlayer.setAbilities(abilities);
			BendingBoard.update(player);
			remove();
			bPlayer.addCooldown(this);
			return;
		}
		
		Location loc = player.getLocation();
		double x = Math.sin(i) * radius;
		double z = Math.cos(i) * radius;
		loc.add(x,0,z);
		Methods.playParticles(loc, 1, Spirit.DARK, Usage.AURA);
		loc.subtract(x,0,z);
		
		loc.add(-x,0,z);
		Methods.playParticles(loc, 1, Spirit.DARK, Usage.AURA);
		loc.subtract(-x,0,z);
		
		loc.add(x,0,-z);
		Methods.playParticles(loc, 1, Spirit.DARK, Usage.AURA);
		loc.subtract(x,0,-z);
		
		loc.add(-x,0,-z);
		Methods.playParticles(loc, 1, Spirit.DARK, Usage.AURA);
		loc.subtract(-x,0,-z);
		i += (Math.PI /2)/ points;
		if (i > Math.PI /2) {
			i = 0;
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
		return "10,000 years of darkness."; 
	}
	
	@Override
	public String getInstructions() {
		return "Left click to morph possess, dash and soar into more sinister versions of themselves."; 
	}
	}


	