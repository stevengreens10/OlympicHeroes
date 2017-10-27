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
	public static final long POSEIDON_SURGE_COOLDOWN = 200L;
	public static final long HADES_SPAWN_COOLDOWN = 200L;
	public static final long HERMES_GLIDE_COOLDOWN = 200L;
	public static final long KILL_COOLDOWN = 216000L;
	
	public static final HashMap<String, String[]> GOD_INFO = new HashMap<String, String[]>();
	
	
	public static final String[] GODS = {"Zeus", "Poseidon", "Hades", "Hera",
										"Demeter", "Aphrodite", "Artemis", "Apollo",
										"Dionysus", "Athena", "Ares", "Hephaestus", "Hermes"};
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
		
		GOD_INFO.put("Zeus", new String[] {"Logan","Write These","Please"});
		GOD_INFO.put("Poseidon", new String[] {"Logan","Write These","Please"});
		GOD_INFO.put("Hades", new String[] {"Logan","Write These","Please"});
		GOD_INFO.put("Hera", new String[] {"Logan","Write These","Please"});
		GOD_INFO.put("Hephaestus", new String[] {"Logan","Write These","Please"});
		GOD_INFO.put("Dionysus", new String[] {"Logan","Write These","Please"});
		GOD_INFO.put("Aphrodite", new String[] {"Logan","Write These","Please"});
		GOD_INFO.put("Athena", new String[] {"Logan","Write These","Please"});
		GOD_INFO.put("Ares", new String[] {"Logan","Write These","Please"});
		GOD_INFO.put("Artemis", new String[] {"Logan","Write These","Please"});
		GOD_INFO.put("Apollo", new String[] {"Logan","Write These","Please"});
		GOD_INFO.put("Hermes", new String[] {"Logan","Write These","Please"});
		GOD_INFO.put("Demeter", new String[] {"Logan","Write These","Please"});
	}
}
