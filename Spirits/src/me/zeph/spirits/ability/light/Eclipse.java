package me.zeph.spirits.ability.light;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.util.DamageHandler;

import me.zeph.spirits.Methods;
import me.zeph.spirits.Methods.Spirit;
import me.zeph.spirits.Methods.Usage;
import me.zeph.spirits.Spirits;
import me.zeph.spirits.ability.api.LightAbility;


public class Eclipse extends LightAbility{
	
	//Config variables
	private double radius;
	private long duration;
	private long cooldown;
	private long chargetime;
	private double damage;
	private double explosionradius;
	private int explosionparticles;
	private double speed;

	//Set variables
	private Long starttime;
	private Long timeleft;
	private Boolean ischarged;
	private Boolean hasclicked;
	private Boolean hasshot;
	
	private Location orbloc;
	
	public Eclipse(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
		
		if (bPlayer.isOnCooldown(this)) {
			return;
		}
		
		
		setFields();
		
		
		start();
		
	}
		
	private void setFields() {
		
		this.chargetime = Spirits.plugin.getConfig().getLong("Spirit.Light.Eclipse.ChargeTime");
		this.radius = Spirits.plugin.getConfig().getDouble("Spirit.Light.Eclipse.Radius");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Light.Eclipse.Cooldown");
		this.duration = Spirits.plugin.getConfig().getLong("Spirit.Light.Eclipse.Duration");
		this.damage = Spirits.plugin.getConfig().getDouble("Spirit.Light.Eclipse.Damage");
		this.explosionradius = Spirits.plugin.getConfig().getDouble("Spirit.Light.Eclipse.ExplosionRadius");
		this.explosionparticles = Spirits.plugin.getConfig().getInt("Spirit.Light.Eclipse.ExplosionParticles");
		this.speed = Spirits.plugin.getConfig().getDouble("Spirit.Light.Eclipse.Speed");
		
		this.ischarged = false;
		this.hasclicked = false;
		this.hasshot = false;
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
		return "Eclipse";
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
			return;
		}
		
		 timeleft = (starttime+chargetime) - System.currentTimeMillis();
		
		if (timeleft<0) {
			if (!player.isSneaking()) {
				ischarged = true;
			}
		}
		
		if (!player.isSneaking() && !ischarged) {
			remove();
			return;
		}
		
		if (timeleft<0 && player.isSneaking()) {
			Methods.playParticles(player.getEyeLocation().add(player.getEyeLocation().getDirection()), 3, Spirit.LIGHT, Usage.CHARGED);
		}
		
		if (ischarged && !hasclicked) {
			
			if (!hasshot) {
				hasshot = true;
				orbloc = player.getLocation().add(0,1,0).add(player.getLocation().getDirection().normalize());
			}
			orbloc.add(player.getLocation().getDirection().normalize().multiply(speed));
			Methods.playParticles(orbloc, 5, Spirit.LIGHT, Usage.AURA);
		}
		
		if(hasshot) {
			if (GeneralMethods.isSolid(orbloc.getBlock())){
				explode();
			}
		}
	}
		
	public void onClick() {
		if (!ischarged || hasclicked) {
			return;
		}
		else {
			hasclicked = true;
			explode();
		}
	}
		// TODO Auto-generated method stub

	public void explode() {
		for (Location loc : Methods.getSphere(orbloc, explosionradius, explosionparticles)) {
			Methods.playParticles(loc, 1, Spirit.LIGHT, Usage.SINGLE);
		}
		for (Entity entity : GeneralMethods.getEntitiesAroundPoint(orbloc, explosionradius)) {
			if ((entity instanceof LivingEntity) && entity.getUniqueId() != player.getUniqueId()) {
				DamageHandler.damageEntity(entity, damage, this);
			}
		}
		remove();
		bPlayer.addCooldown(this);
		return;
		
	}
	
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
		return "Punish opponents for approaching your orb."; 
	}
	
	@Override
	public String getInstructions() {
		return "Hold shift and release to create an orb. Left click to explode it."; 
	}
		
	}


	