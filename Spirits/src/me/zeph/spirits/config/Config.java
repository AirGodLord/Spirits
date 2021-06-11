package me.zeph.spirits.config;

import me.zeph.spirits.config.ConfigFile;

import org.bukkit.configuration.file.FileConfiguration;

import com.projectkorra.projectkorra.configuration.ConfigManager;

import me.zeph.spirits.Spirits;

public class Config {

    private static ConfigFile main;
    static Spirits plugin;

    public Config(Spirits plugin) {
        Config.plugin = plugin;
        main = new ConfigFile("config");
        loadConfig();
    }

    public static FileConfiguration getConfig() {
        return main.getConfig();
    }

    public void loadConfig() {
        FileConfiguration config = Spirits.plugin.getConfig();
        FileConfiguration rankConfig = ConfigManager.languageConfig.get();
        FileConfiguration langConfig = config;

        //Language configuration
        rankConfig.addDefault("Chat.Colors.Spirit", "BLUE");
        rankConfig.addDefault("Chat.Colors.SpiritSub", "DARK_BLUE");
        rankConfig.addDefault("Chat.Colors.LightSpirit", "WHITE");
        rankConfig.addDefault("Chat.Colors.LightSpiritSub", "YELLOW");
        rankConfig.addDefault("Chat.Colors.DarkSpirit", "DARK_GRAY");
        rankConfig.addDefault("Chat.Colors.DarkSpiritSub", "DARK_PURPLE");
        rankConfig.addDefault("Chat.Prefixes.Spirit", "[Spirit]");
        rankConfig.addDefault("Chat.Prefixes.LightSpirit", "[LightSpirit]");
        rankConfig.addDefault("Chat.Prefixes.DarkSpirit", "[DarkSpirit]");

        
        
        
        
        //Ability configuration
        //Neutral
		config.addDefault("Spirit.Neutral.Pulse.Cooldown", Long.valueOf(1000));
		config.addDefault("Spirit.Neutral.Pulse.ChargeTime", Long.valueOf(2000));
		config.addDefault("Spirit.Neutral.Pulse.Damage", Double.valueOf(0.5D));
		config.addDefault("Spirit.Neutral.Pulse.EndRadius", Double.valueOf(4.0));
		config.addDefault("Spirit.Neutral.Pulse.MaxSpins", Integer.valueOf(5));
		config.addDefault("Spirit.Neutral.Pulse.Speed", Double.valueOf(0.3));
		config.addDefault("Spirit.Neutral.Pulse.Radius", Double.valueOf(0.5));
		config.addDefault("Spirit.Neutral.Pulse.Points", Integer.valueOf(20));
		config.addDefault("Spirit.Neutral.Pulse.Hitbox", Double.valueOf(1));
	

        config.addDefault("Spirit.Neutral.Rummage.Speed", 1);
        config.addDefault("Spirit.Neutral.Rummage.Range", 20);
        config.addDefault("Spirit.Neutral.Rummage.Cooldown", 5000);
        config.addDefault("Spirit.Neutral.Rummage.Damage", 2);
        config.addDefault("Spirit.Neutral.Rummage.Hitbox", 1);
        config.addDefault("Spirit.Neutral.Rummage.Width", 3);

        /*
         * this.speed = Spirits.plugin.getConfig().getDouble("Spirit.Neutral.Compress.Speed");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Neutral.Compress.Cooldown");
		this.damage = Spirits.plugin.getConfig().getDouble("Spirit.Neutral.Compress.Damage");
		this.endradius = Spirits.plugin.getConfig().getDouble("Spirit.Neutral.Compress.EndRadius");
		this.chargetime = Spirits.plugin.getConfig().getLong("Spirit.Neutral.Compress.ChargeTime");
		this.points = Spirits.plugin.getConfig().getInt("Spirit.Neutral.Compress.Points");
		this.hitbox = Spirits.plugin.getConfig().getDouble("Spirit.Neutral.Compress.Hitbox");
         */
        config.addDefault("Spirit.Neutral.Compress.Speed", 0.001);
        config.addDefault("Spirit.Neutral.Compress.EndRadius", 10);
        config.addDefault("Spirit.Neutral.Compress.Cooldown", 5000);
        config.addDefault("Spirit.Neutral.Compress.ChargeTime", 2000);
        config.addDefault("Spirit.Neutral.Compress.Damage", 2);
        config.addDefault("Spirit.Neutral.Compress.Points", 30);
        config.addDefault("Spirit.Neutral.Compress.Knockback", 3);
        
        /*
         * this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Neutral.Dash.Cooldown");
		this.speed = Spirits.plugin.getConfig().getDouble("Spirit.Neutral.Dash.Speed");
		this.uses = Spirits.plugin.getConfig().getInt("Spirit.Neutral.Dash.Uses");
         */
        config.addDefault("Spirit.Neutral.Dash.Speed", 1);
        config.addDefault("Spirit.Neutral.Dash.Cooldown", 2000);

        /*
         * this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Neutral.Soar.Cooldown");
		this.speed = Spirits.plugin.getConfig().getDouble("Spirit.Neutral.Soar.Speed");
		this.duration = Spirits.plugin.getConfig().getInt("Spirit.Neutral.Soar.Duration");
         */
        
        config.addDefault("Spirit.Neutral.Soar.Speed", 1);
        config.addDefault("Spirit.Neutral.Soar.Cooldown", 5000);
        config.addDefault("Spirit.Neutral.Soar.Duration", 2000);
        
       
        /*
         * this.points = Spirits.plugin.getConfig().getInt("Spirit.Spirit.Enrage.Points");
		this.radius = Spirits.plugin.getConfig().getDouble("Spirit.Spirit.Enrage.Radius");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Spirit.Enrage.Cooldown");
		this.duration = Spirits.plugin.getConfig().getInt("Spirit.Spirit.Enrage.Duration");
         */
        config.addDefault("Spirit.Neutral.Enrage.Points", 20);
        config.addDefault("Spirit.Neutral.Enrage.Cooldown", 5000);
        config.addDefault("Spirit.Neutral.Enrage.Radius", 2);
        config.addDefault("Spirit.Neutral.Enrage.Duration", 5000);

      
        /*
         * 	this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Neutral.Possess.Cooldown");
		this.duration = Spirits.plugin.getConfig().getLong("Spirit.Neutral.Possess.Duration");
		this.damage = Spirits.plugin.getConfig().getDouble("Spirit.Neutral.Possess.Damage");
		this.range = Spirits.plugin.getConfig().getDouble("Spirit.Neutral.Possess.Range");
		this.hitbox = Spirits.plugin.getConfig().getDouble("Spirit.Neutral.Possess.Hitbox");
         */
        
        config.addDefault("Spirit.Neutral.Possess.Duration", 3000);
        config.addDefault("Spirit.Neutral.Possess.Range", 20);
        config.addDefault("Spirit.Neutral.Possess.Cooldown", 5000);
        config.addDefault("Spirit.Neutral.Possess.Damage", 2);
        config.addDefault("Spirit.Neutral.Possess.Hitbox", 1);
     
    	/*this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Neutral.Leap.Cooldown");
		this.speed = Spirits.plugin.getConfig().getDouble("Spirit.Neutral.Leap.Speed");
		this.grabrange = Spirits.plugin.getConfig().getDouble("Spirit.Neutral.Leap.GrabRange");*/
        
        config.addDefault("Spirit.Neutral.Leap.Speed", 4);
        config.addDefault("Spirit.Neutral.Leap.Cooldown", 2000);
        config.addDefault("Spirit.Neutral.Leap.GrabRange", 3);
        
        
        //Raava
        
        config.addDefault("Spirit.Light.Raava.Purify.Duration", 3000);
        config.addDefault("Spirit.Light.Raava.Purify.Range", 20);
        config.addDefault("Spirit.Light.Raava.Purify.Cooldown", 5000);
        config.addDefault("Spirit.Light.Raava.Purify.Damage", 2);
        config.addDefault("Spirit.Light.Raava.Purify.Hitbox", 1);
        
        /*
        this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Light.Raava.Purify.Cooldown");
		this.duration = Spirits.plugin.getConfig().getLong("Spirit.Light.Raava.Purify.Duration");
		this.damage = Spirits.plugin.getConfig().getDouble("Spirit.Light.Raava.Purify.Damage");
		this.range = Spirits.plugin.getConfig().getDouble("Spirit.Light.Raava.Purify.Range");
		this.hitbox = Spirits.plugin.getConfig().getDouble("Spirit.Light.Raava.Purify.Hitbox");*/
        
        
        config.addDefault("Spirit.Light.Raava.AuraHeal.Points", 20);
        config.addDefault("Spirit.Light.Raava.AuraHeal.Cooldown", 5000);
        config.addDefault("Spirit.Light.Raava.AuraHeal.Radius", 2);
        config.addDefault("Spirit.Light.Raava.AuraHeal.Duration", 5000);
       
        
       /* 
		this.points = Spirits.plugin.getConfig().getInt("Spirit.Light.Raava.AuraHeal.Points");
		this.radius = Spirits.plugin.getConfig().getDouble("Spirit.Light.Raava.AuraHeal.Radius");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Light.Raava.AuraHeal.Cooldown");
		this.duration = Spirits.plugin.getConfig().getInt("Spirit.Light.Raava.AuraHeal.Duration");*/
        

        config.addDefault("Spirit.Light.Raava.Ascend.Speed", 4);
        config.addDefault("Spirit.Light.Raava.Ascend.Cooldown", 3000);
        config.addDefault("Spirit.Light.Raava.Ascend.GrabRange", 3);
        config.addDefault("Spirit.Light.Raava.Ascend.Duration",20000);
        config.addDefault("Spirit.Light.Raava.Ascend.DashRange",2);
        config.addDefault("Spirit.Light.Raava.Ascend.DashCooldown",2000);
        
        /*
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Light.Raava.Ascend.Cooldown");
		this.speed = Spirits.plugin.getConfig().getDouble("Spirit.Light.Raava.Ascend.Speed");
		this.grabrange = Spirits.plugin.getConfig().getDouble("Spirit.Light.Raava.Ascend.GrabRange");*/
		
		
		
       
        //Light
        config.addDefault("Spirit.Light.Raava.Radius", 2);
        config.addDefault("Spirit.Light.Raava.Cooldown", 10000);
        config.addDefault("Spirit.Light.Raava.Duration", 20000);
        config.addDefault("Spirit.Light.Raava.Points", 10);
        
        config.addDefault("Spirit.Light.Catalyse.Damage", 2);
        config.addDefault("Spirit.Light.Catalyse.Hitbox", 1);
        config.addDefault("Spirit.Light.Catalyse.ChargeTime", 3000);
        config.addDefault("Spirit.Light.Catalyse.Points", 10);
        config.addDefault("Spirit.Light.Catalyse.Radius", 7);
        config.addDefault("Spirit.Light.Catalyse.Cooldown", 5000);
        config.addDefault("Spirit.Light.Catalyse.Speed", 0.05);
       
        /*this.points = Spirits.plugin.getConfig().getInt("Spirit.Light.Catalyse.Points");
		this.radius = Spirits.plugin.getConfig().getDouble("Spirit.Light.Catalyse.Radius");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Light.Catalyse.Cooldown");
		this.damage = Spirits.plugin.getConfig().getDouble("Spirit.Light.Catalyse.Damage");
		this.hitbox = Spirits.plugin.getConfig().getDouble("Spirit.Light.Catalyse.Hitbox");
		this.chargetime = Spirits.plugin.getConfig().getLong("Spirit.Light.Catalyse.ChargeTime");*/
        
        config.addDefault("Spirit.Light.Eclipse.Damage", 2);
        config.addDefault("Spirit.Light.Eclipse.ExplosionRadius", 2);
        config.addDefault("Spirit.Light.Eclipse.ExplosionParticles", 10);
        config.addDefault("Spirit.Light.Eclipse.ChargeTime", 3000);
        config.addDefault("Spirit.Light.Eclipse.Points", 10);
        config.addDefault("Spirit.Light.Eclipse.Radius", 3);
        config.addDefault("Spirit.Light.Eclipse.Cooldown", 5000);
        config.addDefault("Spirit.Light.Eclipse.Duration", 5000);
        config.addDefault("Spirit.Light.Eclipse.Speed", 1);
       
		/*this.chargetime = Spirits.plugin.getConfig().getLong("Spirit.Light.Eclipse.ChargeTime");
		this.radius = Spirits.plugin.getConfig().getDouble("Spirit.Light.Eclipse.Radius");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Light.Eclipse.Cooldown");
		this.duration = Spirits.plugin.getConfig().getLong("Spirit.Light.Eclipse.Duration");
		this.damage = Spirits.plugin.getConfig().getDouble("Spirit.Light.Eclipse.Damage");
		this.explosionradius = Spirits.plugin.getConfig().getDouble("Spirit.Light.Eclipse.ExplosionRadius");
		this.explosionparticles = Spirits.plugin.getConfig().getInt("Spirit.Light.Eclipse.ExplosionParticles");*/
		
		config.addDefault("Spirit.Light.Enthrall.Damage", 2);
        config.addDefault("Spirit.Light.Enthrall.Radius", 2);
        config.addDefault("Spirit.Light.Enthrall.ShiftRange", 20);
        config.addDefault("Spirit.Light.Enthrall.ShiftDamage", 3);
        config.addDefault("Spirit.Light.Enthrall.ShiftHitbox", 1);
        config.addDefault("Spirit.Light.Enthrall.ShiftSpeed", 1);
        config.addDefault("Spirit.Light.Enthrall.Cooldown", 5000);
        config.addDefault("Spirit.Light.Enthrall.Speed", 1);
        config.addDefault("Spirit.Light.Enthrall.Hitbox", 1);
        config.addDefault("Spirit.Light.Enthrall.Duration", 3000);
        config.addDefault("Spirit.Light.Enthrall.SlowDuration", 3000);

		/*this.damage = Spirits.plugin.getConfig().getDouble("Spirit.Light.Enthrall.Damage");
		this.shiftrange = Spirits.plugin.getConfig().getDouble("Spirit.Light.Enthrall.ShiftRange");
		this.shiftdamage = Spirits.plugin.getConfig().getDouble("Spirit.Light.Enthrall.ShiftDamage");
		this.shifthitbox = Spirits.plugin.getConfig().getDouble("Spirit.Light.Enthrall.ShiftHitbox");
		this.radius = Spirits.plugin.getConfig().getDouble("Spirit.Light.Enthrall.Range");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Light.Enthrall.Cooldown");
		this.speed = Spirits.plugin.getConfig().getDouble("Spirit.Light.Enthrall.Speed");
		this.hitbox = Spirits.plugin.getConfig().getDouble("Spirit.Light.Enthrall.Hitbox");
		this.duration = Spirits.plugin.getConfig().getInt("Spirit.Light.Enthrall.Duration");
		this.shiftspeed = Spirits.plugin.getConfig().getDouble("Spirit.Light.Enthrall.ShiftSpeed");*/
		
       
        config.addDefault("Spirit.Light.Halo.Damage", 2);
        config.addDefault("Spirit.Light.Halo.Speed", 1);
        config.addDefault("Spirit.Light.Halo.Range", 20);
        config.addDefault("Spirit.Light.Halo.Points", 20);
        config.addDefault("Spirit.Light.Halo.Radius", 1);
        config.addDefault("Spirit.Light.Halo.Cooldown", 5000);
        config.addDefault("Spirit.Light.Halo.LevitationDuration", 3000);
       
		/*this.speed = Spirits.plugin.getConfig().getDouble("Spirit.Light.Halo.Speed");
		this.damage = Spirits.plugin.getConfig().getDouble("Spirit.Light.Halo.Damage");
		this.points = Spirits.plugin.getConfig().getInt("Spirit.Light.Halo.Points");
		this.radius = Spirits.plugin.getConfig().getDouble("Spirit.Light.Halo.Radius");
		this.range = Spirits.plugin.getConfig().getDouble("Spirit.Light.Halo.Range");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Light.Halo.Cooldown");*/
		
		
        config.addDefault("Spirit.Light.Lull.Range", 20);
        config.addDefault("Spirit.Light.Lull.Speed", 1);
        config.addDefault("Spirit.Light.Lull.Cooldown", 5000);
        config.addDefault("Spirit.Light.Lull.Arc", 30);
		/*this.points = Spirits.plugin.getConfig().getInt("Spirit.Light.Lull.Points");
		this.arc = Spirits.plugin.getConfig().getDouble("Spirit.Light.Lull.Arc");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Light.Lull.Cooldown");
		this.duration = Spirits.plugin.getConfig().getLong("Spirit.Light.Lull.Duration");*/
        
        
        //Enchant
        config.addDefault("Spirit.Light.Enchant.HeightSpeed", 0.1);
        config.addDefault("Spirit.Light.Enchant.ChargeTime", 3000);
        config.addDefault("Spirit.Light.Enchant.Cooldown", 10000);
        
        config.addDefault("Spirit.Light.Enchant.FloatDuration", 5000);
        config.addDefault("Spirit.Light.Enchant.HealDuration", 5000);
        config.addDefault("Spirit.Light.Enchant.InvisDuration", 5000);
        config.addDefault("Spirit.Light.Enchant.ResistanceDuration", 5000);
        config.addDefault("Spirit.Light.Enchant.SpeedDuration", 5000);
     
        
        
        
        //Vaatu
        
        
        config.addDefault("Spirit.Dark.Vaatu.DarkFlash.Speed", 5);
        config.addDefault("Spirit.Dark.Vaatu.DarkFlash.Cooldown", 2000);
 
        
       /* 
    	this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Dark.Vaatu.DarkFlash.Cooldown");
		this.speed = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Vaatu.DarkFlash.Speed");
		this.uses = Spirits.plugin.getConfig().getInt("Spirit.Dark.Vaatu.DarkFlash.Uses");*/
		
        
        config.addDefault("Spirit.Dark.Vaatu.Maul.Speed", 1.5);
        config.addDefault("Spirit.Dark.Vaatu.Maul.Cooldown", 5000);
        config.addDefault("Spirit.Dark.Vaatu.Maul.Duration", 2000);
        
        /*
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Dark.Vaatu.Maul.Cooldown");
		this.speed = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Vaatu.Maul.Speed");
		this.duration = Spirits.plugin.getConfig().getInt("Spirit.Dark.Vaatu.Maul.Duration");*/
		
        config.addDefault("Spirit.Dark.Vaatu.Corrupt.Duration", 3000);
        config.addDefault("Spirit.Dark.Vaatu.Corrupt.Range", 20);
        config.addDefault("Spirit.Dark.Vaatu.Corrupt.Cooldown", 5000);
        config.addDefault("Spirit.Dark.Vaatu.Corrupt.Damage", 2);
        config.addDefault("Spirit.Dark.Vaatu.Corrupt.Hitbox", 1);
        
        /*
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Dark.Vaatu.Corrupt.Cooldown");
		this.duration = Spirits.plugin.getConfig().getLong("Spirit.Dark.Vaatu.Corrupt.Duration");
		this.damage = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Vaatu.Corrupt.Damage");
		this.range = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Vaatu.Corrupt.Range");
		this.hitbox = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Vaatu.Corrupt.Hitbox");*/
        
        
        
        
        
        
        
        
        //Dark
        config.addDefault("Spirit.Dark.Vaatu.Radius", 2);
        config.addDefault("Spirit.Dark.Vaatu.Cooldown", 10000);
        config.addDefault("Spirit.Dark.Vaatu.Duration", 20000);
        config.addDefault("Spirit.Dark.Vaatu.Points", 10);
        
        config.addDefault("Spirit.Dark.SoulPunch.Damage", 2);
        config.addDefault("Spirit.Dark.SoulPunch.Range", 20);
        config.addDefault("Spirit.Dark.SoulPunch.Cooldown", 3000);
        config.addDefault("Spirit.Dark.SoulPunch.Speed", 1);
        config.addDefault("Spirit.Dark.SoulPunch.Hitbox", 1);
        config.addDefault("Spirit.Dark.SoulPunch.BlindDuration", 1000);
        config.addDefault("Spirit.Dark.SoulPunch.TeleportBlocks", 3);

        config.addDefault("Spirit.Dark.DarkTether.Damage", 2);
        config.addDefault("Spirit.Dark.DarkTether.Range", 20);
        config.addDefault("Spirit.Dark.DarkTether.Cooldown", 3000);
        config.addDefault("Spirit.Dark.DarkTether.Speed", 1);
        config.addDefault("Spirit.Dark.DarkTether.Hitbox", 1);
       
        config.addDefault("Spirit.Dark.HallowedGround.Duration", 10000);
        config.addDefault("Spirit.Dark.HallowedGround.FireDuration", 2000);
        config.addDefault("Spirit.Dark.HallowedGround.Cooldown", 5000);
        config.addDefault("Spirit.Dark.HallowedGround.Radius", 3.0);
        
        config.addDefault("Spirit.Dark.Condemn.Damage", 2);
        config.addDefault("Spirit.Dark.Condemn.Range", 20);
        config.addDefault("Spirit.Dark.Condemn.Cooldown", 3000);
        config.addDefault("Spirit.Dark.Condemn.Speed", 1);
        config.addDefault("Spirit.Dark.Condemn.Hitbox", 1);
        config.addDefault("Spirit.Dark.Condemn.Duration", 10000);
        config.addDefault("Spirit.Dark.Condemn.Radius", 3.0);
        
        config.addDefault("Spirit.Dark.Desecrate.Damage", 2);
        config.addDefault("Spirit.Dark.Desecrate.Range", 20);
        config.addDefault("Spirit.Dark.Desecrate.Cooldown", 3000);
        config.addDefault("Spirit.Dark.Desecrate.Speed", 1);
        config.addDefault("Spirit.Dark.Desecrate.Hitbox", 1);
        config.addDefault("Spirit.Dark.Desecrate.Duration", 10000);
        config.addDefault("Spirit.Dark.Desecrate.Radius", 2.0);
        
        config.addDefault("Spirit.Dark.DesecrateProjectiles.Damage", 2);
        config.addDefault("Spirit.Dark.DesecrateProjectiles.Range", 20);
        config.addDefault("Spirit.Dark.DesecrateProjectiles.Cooldown", 3000);
        config.addDefault("Spirit.Dark.DesecrateProjectiles.Speed", 1);
        config.addDefault("Spirit.Dark.DesecrateProjectiles.Hitbox", 1);
        config.addDefault("Spirit.Dark.DesecrateProjectiles.BlindDuration", 1000);

        config.addDefault("Spirit.Dark.Reave.Damage", 2);
        config.addDefault("Spirit.Dark.Reave.Range", 20);
        config.addDefault("Spirit.Dark.Reave.Cooldown", 3000);
        config.addDefault("Spirit.Dark.Reave.Speed", 1);
        config.addDefault("Spirit.Dark.Reave.Hitbox", 1);
        config.addDefault("Spirit.Dark.Reave.Duration", 1000);
        config.addDefault("Spirit.Dark.Reave.BlindDuration", 1000);
        
        /*
         * 	this.arc = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Exonerate.Arc");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Dark.Exonerate.Cooldown");
		this.range = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Exonerate.Range");
		this.speed =  Spirits.plugin.getConfig().getDouble("Spirit.Dark.Exonerate.Speed");
         */
        
        config.addDefault("Spirit.Dark.Exonerate.Range", 20);
        config.addDefault("Spirit.Dark.Exonerate.Speed", 1);
        config.addDefault("Spirit.Dark.Exonerate.Cooldown", 5000);
        config.addDefault("Spirit.Dark.Exonerate.Arc", 50);
        config.addDefault("Spirit.Dark.Exonerate.FallThreshold",10);
        
        
        
		/*
		 * this.damage = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Infiltrate.Damage");
		this.range = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Infiltrate.Range");
		this.cooldown = Spirits.plugin.getConfig().getLong("Spirit.Dark.Infiltrate.Cooldown");
		this.speed = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Infiltrate.Speed");
		this.hitbox = Spirits.plugin.getConfig().getDouble("Spirit.Dark.Infiltrate.Hitbox");
		this.chargespeed = Spirits.plugin.getConfig().getLong("Spirit.Dark.Infiltrate.ChargeSpeed");
		 */
        
        config.addDefault("Spirit.Dark.Infiltrate.Damage", 2);
        config.addDefault("Spirit.Dark.Infiltrate.Range", 20);
        config.addDefault("Spirit.Dark.Infiltrate.Cooldown", 3000);
        config.addDefault("Spirit.Dark.Infiltrate.Speed", 1);
        config.addDefault("Spirit.Dark.Infiltrate.Hitbox", 1);
        config.addDefault("Spirit.Dark.Infiltrate.ChargeSpeed", 2000);
        config.addDefault("Spirit.Dark.Infiltrate.BlindDuration", 1000);
        
        //ToxicLash
        config.addDefault("Spirit.Dark.ToxicLash.Length", 4);
        config.addDefault("Spirit.Dark.ToxicLash.Cooldown", 10000);
        
        config.addDefault("Spirit.Dark.ToxicLash.StrikeReach", 3);
        config.addDefault("Spirit.Dark.ToxicLash.StrikeDamage", 2);
        //config.addDefault("Spirit.Dark.ToxicLash.StrikeUses", 5);
        config.addDefault("Spirit.Dark.ToxicLash.StrikeCooldown", 1000);
        
        /* root removed
        config.addDefault("Spirit.Dark.ToxicLash.RootDuration", 3000);
        config.addDefault("Spirit.Dark.ToxicLash.RootRadius", 1);
        */
        
        /*
         * 		this.leachgain = Spirits.plugin.getConfig().getInt("Spirit.Dark.ToxicLash.LeachGain");
		this.leachloss = Spirits.plugin.getConfig().getDouble("Spirit.Dark.ToxicLash.LeachLoss");
		this.leachslow = Spirits.plugin.getConfig().getInt("Spirit.Dark.ToxicLash.LeachSlow");
		this.leachreach = Spirits.plugin.getConfig().getDouble("Spirit.Dark.ToxicLash.LeachReach");
		this.leachcooldown = Spirits.plugin.getConfig().getLong("Spirit.Dark.ToxicLash.LeachCooldown");
		this.leachduration = Spirits.plugin.getConfig().getLong("Spirit.Dark.ToxicLash.LeachDuration");
		
         */
        config.addDefault("Spirit.Dark.ToxicLash.LeachGain", 1);
        config.addDefault("Spirit.Dark.ToxicLash.LeachLoss", 1);
        config.addDefault("Spirit.Dark.ToxicLash.LeachSlow", 1);
        config.addDefault("Spirit.Dark.ToxicLash.LeachReach", 3);
        config.addDefault("Spirit.Dark.ToxicLash.LeachCooldown", 2000);
        config.addDefault("Spirit.Dark.ToxicLash.LeachCooldown", 5000);
        
        config.addDefault("Spirit.Dark.ToxicLash.TendrilRange", 3);
        //config.addDefault("Spirit.Dark.ToxicLash.TendrilUses", 10);
        config.addDefault("Spirit.Dark.ToxicLash.TendrilCooldown", 1000);
        
        /*
        this.slamreach = Spirits.plugin.getConfig().getDouble("Spirit.Dark.ToxicLash.SlamReach");
		this.slamdamage = Spirits.plugin.getConfig().getDouble("Spirit.Dark.ToxicLash.SlamDamage");
		this.slamcooldown = Spirits.plugin.getConfig().getLong("Spirit.Dark.ToxicLash.SlamCooldown");
		this.slamthrowinterval = Spirits.plugin.getConfig().getLong("Spirit.Dark.ToxicLash.SlamThrowInterval");
		this.slamthrowstrength = Spirits.plugin.getConfig().getDouble("Spirit.Dark.ToxicLash.SlamThrowStrength");
		*/
        
        config.addDefault("Spirit.Dark.ToxicLash.SlamReach", 3);
        config.addDefault("Spirit.Dark.ToxicLash.SlamDamage", 2);
        config.addDefault("Spirit.Dark.ToxicLash.SlamCooldown", 5000);
        config.addDefault("Spirit.Dark.ToxicLash.SlamThrowInterval", 1000);
        config.addDefault("Spirit.Dark.ToxicLash.SlamThrowStrength", 5);
        
        
        ConfigManager.languageConfig.save();
        config.options().copyDefaults(true);
        plugin.saveConfig();
    }
}











































