package me.zeph.spirits.ability.spirit;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.util.DamageHandler;

import me.zeph.spirits.Methods;
import me.zeph.spirits.Methods.Spirit;
import me.zeph.spirits.Methods.Usage;
import me.zeph.spirits.SpiritElement;
import me.zeph.spirits.Spirits;
import me.zeph.spirits.ability.api.SpiritAbility;


public class Compress extends SpiritAbility{

	//Config variables
	private double speed;
	private long cooldown;
	private double damage;	
	private long chargetime;
	private double endradius;
	private int points;
	private double hitbox;
	private double knockback;


	//Set variables
	private Boolean charged;
	private long starttime;
	private Boolean hasclicked;
	private Entity e;
	private double currentradius;
	private List<Location>locations;
	private Location origin;



	public Compress(Player player) {
		super(player);
		// TODO Auto-generated constructor stub

		if (bPlayer.isOnCooldown(this)) {
			return;
		}


		setFields();
		start();

	}

	private void setFields() {
		this.speed = Spirits.plugin.getConfig().getDouble("Spirit.Neutral.Compress.Speed");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Neutral.Compress.Cooldown");
		this.damage = Spirits.plugin.getConfig().getDouble("Spirit.Neutral.Compress.Damage");
		this.endradius = Spirits.plugin.getConfig().getDouble("Spirit.Neutral.Compress.EndRadius");
		this.chargetime = Spirits.plugin.getConfig().getLong("Spirit.Neutral.Compress.ChargeTime");
		this.points = Spirits.plugin.getConfig().getInt("Spirit.Neutral.Compress.Points");
		this.hitbox = Spirits.plugin.getConfig().getDouble("Spirit.Neutral.Compress.Hitbox");
		this.knockback = Spirits.plugin.getConfig().getDouble("Spirit.Neutral.Compress.Knockback");
		
		this.starttime = System.currentTimeMillis();
		this.charged = false;
		this.hasclicked = false;
		this.currentradius = 0.1;
		this.locations = new ArrayList<Location>();

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
		return "Compress";
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

		if (!player.isSneaking() && !charged) {
			remove();
			return;
		}

		if (!charged) {
			Methods.playParticles(player.getEyeLocation().add(player.getEyeLocation().getDirection()), 3, Spirit.NEUTRAL, Usage.CHARGED);
		}
		
		if (System.currentTimeMillis() > starttime + chargetime) {
			charged = true;
			Methods.playParticles(player.getLocation().add(0,1,0).add(player.getLocation().getDirection().normalize()), 1, Spirit.NEUTRAL, Usage.CHARGED);
		}

		if (charged && hasclicked) {
			locations = Methods.getCircle(player.getLocation().add(0,1,0), currentradius, points);
			for (int i = 0; i < locations.size();i++) {
				if (hasclicked) {
					//Neutral
					e = Methods.getAffected(locations.get(i).add(0, Methods.getWave(locations.get(i), origin, 2, 1), 0), hitbox, player);
					Methods.playParticles(locations.get(i).add(0, Methods.getWave(locations.get(i), origin, 2, 1), 0), 1, Spirit.NEUTRAL, Usage.BALL);
					if (e!=null) {
						DamageHandler.damageEntity(e, damage, this);
						e.setVelocity(locations.get(i).getDirection().normalize().multiply(knockback));
						remove();
						bPlayer.addCooldown(this);
						return;
						
					}
				}
					
			if (currentradius<endradius) {
				currentradius+=speed;
			}
			else if (currentradius>=endradius) {
				remove();
				bPlayer.addCooldown(this);
				return;
			}
	}
		}
	}

		public void onClick() {	
			if (!hasclicked) {
				hasclicked = true;
				origin = player.getLocation().add(0,1,0);
			}
			else {
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
		public String getDescription() {
			return "Send enemies flying away from you."; 
		}
		
		@Override
		public String getInstructions() {
			return "Hold shift and left click"; 
		}
	}


