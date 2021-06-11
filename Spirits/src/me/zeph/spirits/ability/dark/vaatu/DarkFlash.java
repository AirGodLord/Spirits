package me.zeph.spirits.ability.dark.vaatu;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ability.CoreAbility;
import com.projectkorra.projectkorra.util.DamageHandler;

import me.zeph.spirits.Methods;
import me.zeph.spirits.SpiritElement;
import me.zeph.spirits.Methods.Spirit;
import me.zeph.spirits.Methods.Usage;
import me.zeph.spirits.Spirits;
import me.zeph.spirits.ability.api.DarkAbility;
import me.zeph.spirits.ability.api.SpiritAbility;
import me.zeph.spirits.ability.api.VaatuAbility;
import me.zeph.spirits.ability.dark.Vaatu;


public class DarkFlash extends VaatuAbility{
	
	//Config variables
	private long cooldown;
	private double speed;
	private int uses;

	//Set variables
	private Location loc;
	private Vector dir;
	private Location origin;
	private Long starttime;
	
	public DarkFlash(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
		
		if (bPlayer.isOnCooldown(this)) {
			return;
		}
		
		if (!CoreAbility.hasAbility(player, Vaatu.class)) {
			player.sendMessage("You need Vaatu");
			return;
		}
		
		if (hasAbility(player,DarkFlash.class)) {
			return;
		}
		setFields();
		
		
		start();
		
	}
		
	private void setFields() {
		
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Dark.Vaatu.DarkFlash.Cooldown");
		this.speed = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Vaatu.DarkFlash.Speed");
		
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
		return "DarkFlash";
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
		
		dir = player.getLocation().add(0,1,0).getDirection().normalize();
		Methods.playParticles(player.getLocation(), 1, Spirit.DARK, Usage.TELEPORT);
		Location endloc = loc.clone().add(dir.multiply(speed));
		if (GeneralMethods.isSolid(endloc.getBlock())) {
			dir.setY(0);
		}
		Location finalloc = loc.clone().add(dir.multiply(speed));
		player.teleport(finalloc);
		Methods.playParticles(player.getLocation(), 1, Spirit.DARK, Usage.TELEPORT);
		remove();
		bPlayer.addCooldown(this);
		return;
	
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
	public Element getElement() {
		return SpiritElement.VAATU;
	}
	
	@Override
	public String getDescription() {
		return "Dash forward with your demonic might."; 
	}
	
	@Override
	public String getInstructions() {
		return "Left click to teleport forward slightly."; 
	}
		
	}


	