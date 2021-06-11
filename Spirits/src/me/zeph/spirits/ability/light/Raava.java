package me.zeph.spirits.ability.light;

import java.util.HashMap;

import org.bukkit.Location;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import com.jedk1.jedcore.scoreboard.BendingBoard;
import com.projectkorra.projectkorra.ability.CoreAbility;

import me.zeph.spirits.Methods;
import me.zeph.spirits.Methods.Spirit;
import me.zeph.spirits.Methods.Usage;
import me.zeph.spirits.SpiritElement;
import me.zeph.spirits.Spirits;
import me.zeph.spirits.ability.api.LightAbility;
import me.zeph.spirits.ability.dark.Vaatu;


public class Raava extends LightAbility{
	
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

		public Raava(Player player) {
			super(player);
			// TODO Auto-generated constructor stub
			
			if (bPlayer.isOnCooldown(this)) {
				return;
			}
			
			if (CoreAbility.hasAbility(player, Raava.class)) {
				return;
			}
			
			bPlayer.addSubElement(SpiritElement.RAAVA);
			abilities = bPlayer.getAbilities();
			HashMap<Integer, String> newabilities = (HashMap<Integer, String>) abilities.clone();
			
			for (int i = 0; i < 10 ; i++) {
				newabilities.replace(i, "Leap", "Ascend");
				newabilities.replace(i, "Enrage", "AuraHeal");
				newabilities.replace(i, "Possess", "Purify");
			}
			bPlayer.setAbilities(newabilities);
			BendingBoard.update(player);
			setFields();
			
			
			start();
			
		}
		
	private void setFields() {
		
		this.points = Spirits.plugin.getConfig().getInt("Spirit.Light.Raava.Points");
		this.radius = Spirits.plugin.getConfig().getDouble("Spirit.Light.Raava.Radius");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Light.Raava.Cooldown");
		this.duration = Spirits.plugin.getConfig().getLong("Spirit.Light.Raava.Duration");
		
		this.i = 0;
		this.starttime = System.currentTimeMillis();
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
		return "Raava";
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
			player.removePotionEffect(PotionEffectType.LEVITATION);
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
		Methods.playParticles(loc, 1, Spirit.LIGHT, Usage.AURA);
		loc.subtract(x,0,z);
		
		loc.add(-x,0,z);
		Methods.playParticles(loc, 1, Spirit.LIGHT, Usage.AURA);
		loc.subtract(-x,0,z);
		
		loc.add(x,0,-z);
		Methods.playParticles(loc, 1, Spirit.LIGHT, Usage.AURA);
		loc.subtract(x,0,-z);
		
		loc.add(-x,0,-z);
		Methods.playParticles(loc, 1, Spirit.LIGHT, Usage.AURA);
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
		return "Light and peace."; 
	}
	
	@Override
	public String getInstructions() {
		return "Left click to activate. Enhance leap, enrage and possess."; 
	}
	}


	