package me.NodeDigital.OlympicHeroes;

import java.util.HashMap;

public class Variables {
	public static final int IRON_XP = 30;
	public static final int GOLD_XP = 60;
	public static final int DIAMOND_XP = 140;
	public static final int EMERALD_XP = 140;
	public static final int[] LEVEL_CUTOFFS = {1000, 2500, 4500, 8000};
	
	public static final long PRAYER_COOLDOWN = 144000L;
	public static final long ZEUS_LIGHTNING_COOLDOWN = 200L;
	public static final long ARES_RAGE_COOLDOWN = 200L;
	public static final long APHRODITE_RES_COOLDOWN = 6000L;
	public static final long HERA_BESTOW_COOLDOWN = 200L;
	public static final long DIONYSUS_POISON_COOLDOWN = 200L;
	public static final long DEMETER_BONEMEAL_COOLDOWN = 200L;
	public static final long POSEIDON_SURGE_COOLDOWN = 200L;
	public static final long HADES_SPAWN_COOLDOWN = 200L;
	public static final long HERMES_GLIDE_COOLDOWN = 200L;
	public static final long KILL_COOLDOWN = 216000L;
	
	public static final HashMap<String, String[]> GOD_INFO = new HashMap<String, String[]>();
	
	
	public static final String[] GODS = {"Zeus", "Poseidon", "Hades", "Hera",
										"Demeter", "Aphrodite", "Artemis", "Apollo",
										"Dionysus", "Athena", "Ares", "Hephaestus", "Hermes"};
	public static final String[] HELP_MESSAGE = { "-------Help-------", "- /oh : View your devotion to the gods",
			"- /oh <god> : View the characteristics of any god", "- /oh help : Display this help menu"};
	public static HashMap<String, String[]> OPPOSING_GODS = new HashMap<String, String[]>();
	
	public static void setup() {

		OPPOSING_GODS.put("Zeus", new String[] {"Poseidon", "Hades"});
		OPPOSING_GODS.put("Poseidon", new String[] {"Zeus", "Hades"});
		OPPOSING_GODS.put("Hades", new String[] {"Poseidon", "Zeus"});
		OPPOSING_GODS.put("Hera", new String[]{"Hephaestus","Dionysus"});
		OPPOSING_GODS.put("Hephaestus", new String[]{"Hera", "Dionysus"});
		OPPOSING_GODS.put("Dionysus", new String[]{"Hera","Hephaestus"});
		OPPOSING_GODS.put("Aphrodite", new String[] {"Athena","Ares"});
		OPPOSING_GODS.put("Athena", new String[]{"Ares", "Aphrodite"});
		OPPOSING_GODS.put("Ares", new String[]{"Athena","Ares"});	
		OPPOSING_GODS.put("Artemis", new String[]{"Apollo"});
		OPPOSING_GODS.put("Apollo", new String[]{"Artemis"});
		OPPOSING_GODS.put("Hermes", new String[]{"Demeter"});
		OPPOSING_GODS.put("Demeter", new String[] {"Hermes"});
		
		GOD_INFO.put("Zeus", new String[] {"Welcome to the Shrine of Zeus! These are the Powers I Grant.","lvl 1 - Fall Reduction","lvl 2 - Fall Reduction, Improved","lvl 3 - Jump Boost","lvl 4 - Jump Boost, Improved","lvl 5 - Sword of Lightning!(Right click with a sword)","I Cannot Share devotion with Poseidon or Hades!"});
		GOD_INFO.put("Poseidon", new String[] {"Welcome to the Shrine of Poseidon! These are the Powers I Grant.","lvl 1 - Depth Strider","lvl 2 - Depth Strider, Improved","lvl 3 - Water Strength","lvl 4 - Water Strength, Improved","lvl 5 - Water Surge!(Right click with a sword)","I Cannot Share devotion with Zeus or Hades!"});
		GOD_INFO.put("Hades", new String[] {"Welcome to the Shrine of Hades! These are the Powers I Grant.","lvl 1 - Looting Luck","lvl 2 - Looting Luck, Improved","lvl 3 - Pickaxe Haste","lvl 4 - Pickaxe Haste, Improved","lvl 5 - Summon Undead!(Right click with a sword)","I Cannot Share devotion with Poseidon or Zeus!"});
		GOD_INFO.put("Hera", new String[] {"Welcome to the Shrine of Hera! These are the Powers I Grant.(Right Click with a stick)","lvl 3 - Betow Regen 2 upon an ally","lvl 5 - Bestow Strength 2 upon an ally","I Cannot Share devotion with Hephaestus or Dionysus!"});
		GOD_INFO.put("Hephaestus", new String[] {"Welcome to the Hephaestus of Hephaestus! These are the Powers I Grant.","lvl 3 - Item Repair","lvl 5 - Enchanted Item Repair","I Cannot Share devotion with Hera or Dionysus!"});
		GOD_INFO.put("Dionysus", new String[] {"Welcome to the Shrine of Dionysus! These are the Powers I Grant.(Right Click with a stick)","lvl 2 - Curse of Drunkeness 15 sec","lvl 3 - Curse of Drunkeness 30 sec ","lvl 4 - Curse of Weakness","lvl 5 - Curse of Extreme Weakness ","I Cannot Share devotion with Hera or Hephaestus!"});
		GOD_INFO.put("Aphrodite", new String[] {"Welcome to the Shrine of Aphrodite! These are the Powers I Grant.","lvl 3 - Protection of Love","lvl 4 - Improved Protection of Love","lvl 5 - Resilience of Heart!(Just take damage)","I Cannot Share devotion with Athena or Ares!"});
		GOD_INFO.put("Athena", new String[] {"Welcome to the Shrine of Athena! These are the Powers I Grant.","lvl 3 - Swift Sword","lvl 4 - Swift Sword Perfected","lvl 5 - Athena's Aegis!(Just hold a sword)","I Cannot Share devotion with Ares or Aphrodite!"});
		GOD_INFO.put("Ares", new String[] {"Welcome to the Shrine of Ares! These are the Powers I Grant.","lvl 3 - Brute Srength","lvl 4 - Stronger Brute Strength","lvl 5 - Beserkers Rage!(Right click and an Axe)","I Cannot Share devotion with Athena or Aphrodite!"});
		GOD_INFO.put("Artemis", new String[] {"Welcome to the Shrine of Artemis! These are the Powers I Grant.","lvl 2 - Clarity in Moonlight","lvl 3 - Blessed Replenishing Arrows","lvl 5 - Stealth in Darkness","I Cannot Share devotion with my brother Apollo!"});
		GOD_INFO.put("Apollo", new String[] {"Welcome to the Shrine of Apollo! These are the Powers I Grant.","lvl 2 - Sun's Reveal","lvl 3 - Sun's Flaming Arrows","lvl 5 - Healing of the Sun","I Cannot Share devotion with my sister Artemis!"});
		GOD_INFO.put("Hermes", new String[] {"Welcome to the Shrine of Hermes! These are the Powers I Grant.","lvl 2 - Swift Feet","lvl 3 - Swifter Feet","lvl 4 - Swiftest Feet","lvl 5 - Winged Sandals!(Crouch and Jump)","I Cannot Share devotion with Demeter!"});
		GOD_INFO.put("Demeter", new String[] {"Welcome to the Shrine of Demeter! These are the Powers I Grant.","lvl 2 - Doubled Crops","lvl 3 - Grow Crops Radius 3(Use a Bone)","lvl 4 - Grow Crops Radius 4(Use a Bone)","lvl 5 - Enemy Root!(Crouch and Jump?)","I Cannot Share devotion with Hermes!"});
	}
}
