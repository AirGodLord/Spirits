package me.zeph.spirits;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ability.Ability;
import com.projectkorra.projectkorra.util.ParticleEffect;

public class Methods {



	/*
	 * The types of spirits in the plugin.
	 */
	public enum Spirit {
		NEUTRAL, LIGHT, DARK
	}

	public enum Usage {
		BALL, BALL2, FLAME, AURA, SINGLE, SINGLE2, MOVE, AMBIENT, TELEPORT, CHARGED, POSSESS
	}


	//add 0,1,0 to location
	//radii is a factor of a unit circle
	public static List<Location> getSphere(Location loc, double radii, int density) {
		final List<Location> sphere = new ArrayList<Location>();
		for (double i = 0; i <= Math.PI; i += Math.PI / density) {
			double radius = Math.sin(i) * radii;
			double y = Math.cos(i) * radii;
			for (double a = 0; a < Math.PI * 2; a+= Math.PI*2 / density) {
				double x = Math.cos(a) * radius;
				double z = Math.sin(a) * radius;
				sphere.add(loc.clone().add(x,y,z));    
			}
		}
		return sphere;
	}

	public static List<Location> getCircle(Location loc, double radii, int points) {
		final List<Location> circle = new ArrayList<Location>();
		for (double i = 0; i < Math.PI *2; i+= Math.PI*2 / points) {
			double x = Math.sin(i) * radii;
			double z = Math.cos(i) * radii;
			Location location = loc.clone().add(x,0,z);
			circle.add(location);    
		}
		return circle;
	}

	public static void playCircle(Location loc, double radii, int points, Spirit type, Usage shape) {
		for (double i = 0; i < Math.PI *2; i+= Math.PI*2 / points) {
			double x = Math.sin(i) * radii;
			double z = Math.cos(i) * radii;
			Location location = loc.clone().add(x,0,z);
			Methods.playParticles(location, 1, type, shape);
		}
	}
	
	public static Entity getAffected(Location loc, double radii, Player player) {
		Entity e = null;
		for (Entity entity : GeneralMethods.getEntitiesAroundPoint(loc, radii)) {
			if ((entity instanceof LivingEntity) && entity.getUniqueId() != player.getUniqueId()) {
				e = entity;
			}
		}
		return e;
	}

	public static Element getType(Player player) {
		BendingPlayer type = BendingPlayer.getBendingPlayer(player);
		Element element = null;
		if (type.hasElement(SpiritElement.DARK)) {
			element = SpiritElement.DARK;
		}
		else if (type.hasElement(SpiritElement.LIGHT)) {
			element = SpiritElement.LIGHT;
		}
		return element;
	}

	public static double getWave(Location loc, Location origin, double wavelength, double amplitude) {
		final double distance = loc.distance(origin) % wavelength;
		double y = Math.cos(distance)*amplitude;
		return y;
	}



	public static List<Location> getPolygon(Location loc, double radii, int points){
		final List<Location> polygon = new ArrayList<Location>();
		for (int i = 0; i < points; i++) {
			double angle = 360.0 / points * i;
			double nextAngle = 360.0 / points * (i + 1); // the angle for the next point.
			angle = Math.toRadians(angle);
			nextAngle = Math.toRadians(nextAngle); // convert to radians.
			double x = Math.cos(angle);
			double z = Math.sin(angle);
			double x2 = Math.cos(nextAngle);
			double z2 = Math.sin(nextAngle);
			double deltaX = x2 - x; // get the x-difference between the points.
			double deltaZ = z2 - z; // get the z-difference between the points.
			double distance = Math.sqrt((deltaX - x) * (deltaX - x) + (deltaZ - z) * (deltaZ - z));
			for (double d = 0; d < distance - .1; d += .1) { // we subtract .1 from the distance because otherwise it would make 1 step too many.
				loc.add(x + deltaX * d, 0, z + deltaZ * d);
				polygon.add(loc);
				loc.subtract(x + deltaX * d, 0, z + deltaZ * d);
			}
		}
		return polygon;
	}	

	public static void playPolygon(Location loc, double radii, int points, Spirit type, Usage shape){
		for (int i = 0; i < points; i++) {
			double angle = 360.0 / points * i;
			double nextAngle = 360.0 / points * (i + 1); // the angle for the next point.
			angle = Math.toRadians(angle);
			nextAngle = Math.toRadians(nextAngle); // convert to radians.
			double x = Math.cos(angle);
			double z = Math.sin(angle);
			double x2 = Math.cos(nextAngle);
			double z2 = Math.sin(nextAngle);
			double deltaX = x2 - x; // get the x-difference between the points.
			double deltaZ = z2 - z; // get the z-difference between the points.
			double distance = Math.sqrt((deltaX - x) * (deltaX - x) + (deltaZ - z) * (deltaZ - z));
			for (double d = 0; d < distance - .1; d += .1) { // we subtract .1 from the distance because otherwise it would make 1 step too many.
				loc.add(x + deltaX * d, 0, z + deltaZ * d);
				playParticles(loc,5,type,shape);
				loc.subtract(x + deltaX * d, 0, z + deltaZ * d);
			}
		}
	}	

	public static List<Location> getHollowPolygon(Location loc, double radii, int points){
		final List<Location> hpolygon = new ArrayList<Location>();
		for (int i = 0; i < points; i++) {
			double angle = 360.0 / points * i;
			angle = Math.toRadians(angle);
			double x = Math.cos(angle);
			double z = Math.sin(angle);
		}
		return hpolygon;
	}	

	public static void playHollowPolygon(Location loc, double radii, int points, Spirit type, Usage shape){
		for (int i = 0; i < points; i++) {
			double angle = 360.0 / points * i;
			angle = Math.toRadians(angle);
			double x = Math.cos(angle);
			double z = Math.sin(angle);
			playParticles(loc.add(x,0,z),5,type,shape);
		}

	}


	public static void applyPotion(Entity e, PotionEffectType effect, int durationinms) {
		((LivingEntity) e).addPotionEffect(effect.createEffect(durationinms/1000*20, 1));
	}

	public static void applyPotionPlayer(Player player, PotionEffectType effect, int durationinms) {
		PotionEffect ef = new PotionEffect(effect, durationinms/1000*20, 1, false, false, false);
		player.addPotionEffect(ef);
	}
	
	public static void playParticles(Location loc, int amount, Spirit type, Usage shape) {
		if (type == Spirit.DARK) {
			if (shape == Usage.BALL) {
				List<Location>ball = new ArrayList<Location>();
				ball = Methods.getSphere(loc, 0.2, 10);
				for (Location i : ball) {
					ParticleEffect.TOWN_AURA.display(i, amount, 0, 0, 0);
				}
			}
			else if (shape == Usage.BALL2) {
				ParticleEffect.SPELL_WITCH.display(loc, amount);
			}
			else if (shape == Usage.FLAME) {
				ParticleEffect.SPELL_WITCH.display(loc, amount, 0, 0, 0, 999999999);
			}
			else if (shape == Usage.AURA) {
				ParticleEffect.SMOKE_NORMAL.display(loc, amount, 0, 0, 0);
			}
			else if (shape == Usage.SINGLE) {
				ParticleEffect.REDSTONE.display(loc, amount, 0, 0, 0, 0.005, new Particle.DustOptions(Color.fromRGB	(72,61,139), 1));
			}
			else if (shape == Usage.MOVE) {
				ParticleEffect.SPELL_WITCH.display(GeneralMethods.getLeftSide(loc,1), amount, 0.5, 0.5, 0.5);
				ParticleEffect.SPELL_WITCH.display(GeneralMethods.getRightSide(loc,1), amount, 0.5, 0.5, 0.5);
			}
			else if (shape == Usage.AMBIENT) {
				ParticleEffect.SPELL_MOB_AMBIENT.display(loc, 0);
			}
			else if (shape == Usage.TELEPORT) {
				ParticleEffect.PORTAL.display(loc, amount, 0, 0, 0, 1);
			}
			else if (shape == Usage.CHARGED) {
				ParticleEffect.SMOKE_NORMAL.display(loc, amount, 0.5, 0.5, 0.5);
			}
			else if (shape == Usage.POSSESS) {
				ParticleEffect.SOUL.display(loc, amount, 0.5, 0.5, 0.5);
			}
		}
		else if (type == Spirit.LIGHT) {
			if (shape == Usage.BALL) {
				List<Location>ball = new ArrayList<Location>();
				ball = Methods.getSphere(loc, 0.2, 10);
				for (Location i : ball) {
					//ParticleEffect.ENCHANTMENT_TABLE.display(i, 0, 0, 0, 0, 999999999);
					ParticleEffect.ENCHANTMENT_TABLE.display(i, 0, 0, 0, 0, 0);
				}
			}
			else if (shape == Usage.BALL2) {
				ParticleEffect.SPELL_INSTANT.display(loc, amount);
			}
			else if (shape == Usage.FLAME) {
				ParticleEffect.SPELL_INSTANT.display(loc, amount, 0, 0, 0, 999999999 );
			}
			else if (shape == Usage.AURA) {
				ParticleEffect.END_ROD.display(loc, amount, 0, 0, 0, 0);
			}
			else if (shape == Usage.SINGLE) {
				ParticleEffect.REDSTONE.display(loc, amount, 0, 0, 0, 0.005, new Particle.DustOptions(Color.fromRGB	(240,255,255), 1));
			}
			else if (shape == Usage.SINGLE2) {
				ParticleEffect.REDSTONE.display(loc, amount, 0, 0, 0, 0.005, new Particle.DustOptions(Color.fromRGB	(0,255,255), 1));
			}
			else if (shape == Usage.MOVE) {
				ParticleEffect.SPELL_INSTANT.display(GeneralMethods.getLeftSide(loc,1), amount, 0.5, 0.5, 0.5);
				ParticleEffect.SPELL_INSTANT.display(GeneralMethods.getRightSide(loc,1), amount, 0.5, 0.5, 0.5);
			}
			else if (shape == Usage.AMBIENT) {
				ParticleEffect.SPELL_MOB_AMBIENT.display(loc, amount, 0, 0, 0, 0.005, new Particle.DustOptions(Color.fromRGB(72,61,139), 1));
			}
			else if (shape == Usage.TELEPORT) {
				ParticleEffect.ENCHANTMENT_TABLE.display(loc, amount, 0, 0, 0, 0);
			}
			else if (shape == Usage.CHARGED) {
				ParticleEffect.ENCHANTMENT_TABLE.display(loc, amount, 0.5, 0.5, 0.5);
			}
			else if (shape == Usage.POSSESS) {
				ParticleEffect.END_ROD.display(loc, amount, 0.5, 0.5, 0.5);
			}
		}
		else if (type == Spirit.NEUTRAL) {
			if (shape == Usage.BALL) {
				ParticleEffect.CRIT_MAGIC.display(loc, amount, 0, 0, 0, 0.0001);
			}
			else if (shape == Usage.BALL2) {
				ParticleEffect.CRIT.display(loc, amount, 0, 0, 0,0);
			}
			else if (shape == Usage.AURA) {
				ParticleEffect.SPIT.display(loc, amount, 0, 0, 0,0);
			}
			else if (shape == Usage.MOVE) {
				ParticleEffect.CRIT.display(GeneralMethods.getLeftSide(loc,1), amount, 0.5, 0.5, 0.5);
				ParticleEffect.CRIT.display(GeneralMethods.getRightSide(loc,1), amount, 0.5, 0.5, 0.5);
			}
			else if (shape == Usage.CHARGED) {
				ParticleEffect.CRIT.display(loc, amount, 0.3, 0.3, 0.3);
			}
			else if (shape == Usage.POSSESS) {
				ParticleEffect.SNEEZE.display(loc, amount, 0.5, 0.5, 0.5);
			}
			
		}
	}


	/*
	 * Used to set the velocity of a player.
	 *
	 * player = The player which is being manipulated.
	 * isForward = Whether the velocity is going forward or backwards.
	 * speed = The speed at which the player is being sent.
	 * height = The height from their original location the player is shot.
	 */
	public static Vector setVelocity(LivingEntity target, float speed, double height) {
		Location location = target.getLocation();
		Vector direction = location.getDirection().normalize().multiply(speed);
		if (height != 0) {
			direction.setY(height);
		}
		return direction;
	}
}




