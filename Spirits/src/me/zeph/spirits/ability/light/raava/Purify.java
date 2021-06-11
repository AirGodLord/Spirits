package me.zeph.spirits.ability.light.raava;

import org.bukkit.GameMode;
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
import me.zeph.spirits.ability.api.RaavaAbility;
import me.zeph.spirits.ability.api.SpiritAbility;
import me.zeph.spirits.ability.light.Raava;


public class Purify extends RaavaAbility{
	
	//Config variables
	private long cooldown;
	private int duration;
	private double damage;
	private double range;
	private double hitbox;

	//Set variables
	private Location loc;
	private Long starttime;
	private Location origin;
	private Entity e;
	private Boolean ischarged;
	
	public Purify(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
		
		if (bPlayer.isOnCooldown(this)) {
			return;
		}
		
		if (!CoreAbility.hasAbility(player, Raava.class)) {
			player.sendMessage("You need Raava");
			return;
		}
		
		if (hasAbility(player,Purify.class)) {
			return;
		}
		
		this.range = Spirits.plugin.getConfig().getDouble("Spirit.Light.Raava.Purify.Range");
		e = GeneralMethods.getTargetedEntity(player, range);
		
		this.duration = Spirits.plugin.getConfig().getInt("Spirit.Light.Raava.Purify.Duration");
		if (e!=null) {
			Methods.applyPotionPlayer(player, PotionEffectType.INVISIBILITY, duration);
			Methods.applyPotion(e, PotionEffectType.LEVITATION, duration);
			setFields();
			start();
		}
		else {
			return;
		}
		
	}
		
	private void setFields() {
		
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Light.Raava.Purify.Cooldown");
		
		this.damage = Spirits.plugin.getConfig().getDouble("Spirit.Light.Raava.Purify.Damage");
	
		this.hitbox = Spirits.plugin.getConfig().getDouble("Spirit.Light.Raava.Purify.Hitbox");

		this.starttime = System.currentTimeMillis();
		this.ischarged = false;
		
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
		return "Purify";
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
			player.removePotionEffect(PotionEffectType.INVISIBILITY);
			remove();
			return;
		}
		
		if (e!=null) {
			Methods.playParticles(e.getLocation().add(0,1,0), 5, Spirit.LIGHT, Usage.POSSESS);
			player.teleport(e);
			player.setInvulnerable(true);
		}
		
		if (System.currentTimeMillis() > starttime+duration) {
			ischarged = true;
			DamageHandler.damageEntity(e, damage, this);
			player.setGameMode(GameMode.SURVIVAL);
			remove();
			player.setInvulnerable(false);
			player.removePotionEffect(PotionEffectType.INVISIBILITY);
			bPlayer.addCooldown(this);
			return;
		}
		
		if (!player.isSneaking() && !ischarged) {
			player.setGameMode(GameMode.SURVIVAL);
			remove();
			player.setInvulnerable(false);
			player.removePotionEffect(PotionEffectType.INVISIBILITY);
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
	public Element getElement() {
		return SpiritElement.RAAVA;
	}
	
	@Override
	public String getDescription() {
		return "Show your enemies the error in their ways - by invading their bodies and dropping them from a height."; 
	}
	
	@Override
	public String getInstructions() {
		return "Hold shift on an entity and release when they are damaged."; 
	}
		
	}


	