package me.zeph.spirits.ability.dark;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ability.CoreAbility;
import com.projectkorra.projectkorra.util.TempBlock;

import me.zeph.spirits.Methods;
import me.zeph.spirits.Methods.Spirit;
import me.zeph.spirits.Methods.Usage;
import me.zeph.spirits.Spirits;
import me.zeph.spirits.ability.api.DarkAbility;


public class HallowedGround extends DarkAbility{

	//Config variables
	private double radius;
	private long duration;
	private long cooldown;
	private Boolean hasclicked;
	private long fireduration;

	//Set variables
	private long starttime;
	private List<Block>tempblocks;
	private long timeleft;
	private List<Block>alltempblocks;
	private long firestarttime;


	public HallowedGround(Player player) {
		super(player);
		// TODO Auto-generated constructor stub

		if (bPlayer.isOnCooldown(this) || CoreAbility.hasAbility(player, HallowedGround.class)) {
			return;
		}

		setFields();

		start();

	}

	private void setFields() {

		this.radius = Spirits.plugin.getConfig().getDouble("Spirit.Dark.HallowedGround.Radius");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Dark.HallowedGround.Cooldown");
		this.duration = Spirits.plugin.getConfig().getLong("Spirit.Dark.HallowedGround.Duration");
		this.fireduration = Spirits.plugin.getConfig().getLong("Spirit.Dark.HallowedGround.FireDuration");

		this.starttime = System.currentTimeMillis();
		this.timeleft = duration;
		this.alltempblocks = new ArrayList<Block>();
		this.hasclicked = false;

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
		return "HallowedGround";
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

		if (System.currentTimeMillis() > starttime+duration) {
			remove();
			bPlayer.addCooldown(this);
			return;
		}
		
		timeleft = System.currentTimeMillis() - starttime+duration;
		
		if (!hasclicked) {
			tempblocks = GeneralMethods.getBlocksAroundPoint(player.getLocation().subtract(0,1,0).subtract(player.getLocation().getDirection().normalize().multiply(radius/2 +1)), radius);
			
			for (Block i : tempblocks) {
				if (GeneralMethods.isSolid(i) && i.getLocation().add(0,1,0).getBlock().getType().equals(Material.AIR)) {
					TempBlock tempblock = new TempBlock(i, Material.SOUL_SOIL);
					alltempblocks.add(i);
					tempblock.setRevertTime(duration);
				}
				else if (i.getType().equals(Material.GRASS) || i.getType().equals(Material.TALL_GRASS)) {
					TempBlock tempblock = new TempBlock(i, Material.AIR);
					tempblock.setRevertTime(duration);
				}
			}
		}
		
		if (player.getLocation().subtract(0,1,0).getBlock().getType().equals(Material.SOUL_SOIL)){
			player.addPotionEffect(PotionEffectType.SPEED.createEffect(20, 2));
			player.setFireTicks(0);
		}

		if (hasclicked) {
			if (System.currentTimeMillis() > firestarttime+fireduration) {
				remove();
				bPlayer.addCooldown(this);
				return;
			}
		}

	}

	public void onClick() {
		if (hasclicked) {
			return;
		}
		hasclicked = true;
		firestarttime = System.currentTimeMillis();
		for (Block b : alltempblocks) {
			Location temploc = b.getLocation().add(0,1,0);
			TempBlock tempblock = new TempBlock(temploc.getBlock(), Material.SOUL_FIRE);
			tempblock.setRevertTime(fireduration);
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
		return "Corrupt the ground around you, giving you a speed boost. Then burn it all..."; 
	}
	
	@Override
	public String getInstructions() {
		return "Tap shift to activate, and click to set the ground alight."; 
	}
}


