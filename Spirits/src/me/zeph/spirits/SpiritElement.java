package me.zeph.spirits;

import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.Element.SubElement;
import com.projectkorra.projectkorra.ProjectKorra;

public class SpiritElement {
    public static final Element SPIRIT = new Element("Spirit", null, ProjectKorra.plugin);
    public static final Element DARK = new Element("DarkSpirit", null, ProjectKorra.plugin);
    public static final Element LIGHT = new Element("LightSpirit", null, ProjectKorra.plugin);
    
    public static final SubElement VAATU = new SubElement("Vaatu", DARK);
    public static final SubElement RAAVA = new SubElement("Raava", LIGHT);
}

