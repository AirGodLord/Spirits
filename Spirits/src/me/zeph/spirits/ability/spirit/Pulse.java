package me.zeph.spirits.ability.spirit;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.util.DamageHandler;

import me.zeph.spirits.Methods;
import me.zeph.spirits.Methods.Spirit;
import me.zeph.spirits.Methods.Usage;
import me.zeph.spirits.SpiritElement;
import me.zeph.spirits.Spirits;
import me.zeph.spirits.ability.api.SpiritAbility;


public class Pulse extends SpiritAbility{

	//Config variables
	private double speed;
	private long cooldown;
	private double damage;	
	private long chargetime;
	private double endradius;
	private int maxspins;
	private int points;
	private double hitbox;


	//Set variables
	private Boolean charged;
	private long starttime;
	private int hasclicked;
	private Entity e;
	private Element type;
	private int currentspins;
	private double currentradius;
	private List<Location>locations;



	public Pulse(Player player) {
		super(player);
		// TODO Auto-generated constructor stub

		if (bPlayer.isOnCooldown(this)) {
			return;
		}


		setFields();


		start();

	}

	private void setFields() {
		this.speed = Spirits.plugin.getConfig().getDouble("Spirit.Neutral.Pulse.Speed");
		this.maxspins = Spirits.plugin.getConfig().getInt("Spirit.Neutral.Pulse.MaxSpins");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Neutral.Pulse.Cooldown");
		this.damage = Spirits.plugin.getConfig().getDouble("Spirit.Neutral.Pulse.Damage");
		this.endradius = Spirits.plugin.getConfig().getDouble("Spirit.Neutral.Pulse.EndRadius");
		this.chargetime = Spirits.plugin.getConfig().getLong("Spirit.Neutral.Pulse.ChargeTime");
		this.points = Spirits.plugin.getConfig().getInt("Spirit.Neutral.Pulse.Points");
		this.hitbox = Spirits.plugin.getConfig().getDouble("Spirit.Neutral.Pulse.Hitbox");

		this.starttime = System.currentTimeMillis();
		this.charged = false;
		this.hasclicked = 1;
		this.currentspins = 0;
		this.currentradius = 0.1;
		this.type = Methods.getType(player);
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
		return "Pulse";
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

		if (currentspins == maxspins) {
			bPlayer.addCooldown(this);
			remove();
			return;
		}

		if (!player.isSneaking() && !charged) {
			remove();
			return;
		}


		if (System.currentTimeMillis() > starttime + chargetime) {
			charged = true;
		}
		
		if (charged) {
			locations = Methods.getCircle(player.getLocation(), currentradius, points);
			for (int i = 0; i < locations.size();i++) {
				if (hasclicked == 1) {
					//Neutral				
					e = Methods.getAffected(locations.get(i), hitbox, player);
					Methods.playParticles(locations.get(i), 1, Spirit.NEUTRAL, Usage.BALL);
					if (e!=null) {
						DamageHandler.damageEntity(e, damage, this);
					}
				}

				else if (hasclicked == 2) {
					if (type == SpiritElement.LIGHT) {
						e = Methods.getAffected(locations.get(i), hitbox, player);
						Methods.playParticles(locations.get(i), 1, Spirit.LIGHT, Usage.FLAME);
						player.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE.createEffect(20, 1));
					}

					else if (type == SpiritElement.DARK) {
						e = Methods.getAffected(locations.get(i), hitbox, player);
						Methods.playParticles(locations.get(i), 1, Spirit.DARK, Usage.FLAME);
						if (e!= null) {
							((LivingEntity) e).addPotionEffect(PotionEffectType.SLOW.createEffect(20, 1));
						}
					}
				}
			}
			if (currentradius<endradius) {
				currentradius+=speed;
			}
			else if (currentradius>=endradius) {
				currentradius = 0.1;
				currentspins++;
			}
		}
	}

		public void clickFunction() {	
			if (hasclicked == 2) {
				hasclicked = 1;
			}
			else {
				hasclicked++;
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
			return "Release radial waves with a toggleable mode between neutral and light/dark depending on what you have chosen."; 
		}
		
		@Override
		public String getInstructions() {
			return "Hold shift until charged and release, then left click to toggle modes."; 
		}

	}


