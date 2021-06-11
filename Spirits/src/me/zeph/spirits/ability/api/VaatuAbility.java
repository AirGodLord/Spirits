package me.zeph.spirits.ability.api;

import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.Element.SubElement;
import com.projectkorra.projectkorra.ability.ElementalAbility;
import me.zeph.spirits.SpiritElement;
import me.zeph.spirits.Spirits;

import org.bukkit.entity.Player;

public abstract class VaatuAbility extends ElementalAbility {

    public VaatuAbility(Player player) {
        super(player);
    }

    public SubElement getSubElement() {
    	return SpiritElement.VAATU;
    }
}
