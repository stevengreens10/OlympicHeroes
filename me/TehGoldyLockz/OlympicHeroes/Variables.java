package me.TehGoldyLockz.OlympicHeroes;

import java.util.HashMap;

public class Variables {
	public static final int IRON_XP = 5;
	public static final int GOLD_XP = 10;
	public static final int DIAMOND_XP = 20;
	public static final int EMERALD_XP = 30;
	public static final int[] LEVEL_CUTOFFS = {100, 250, 450, 800};
	public static final String[] GODS = {"Zeus", "Poseidon", "Hades", "Hera",
										"Demeter", "Aphrodite", "Artemis", "Apollo",
										"Dionysus", "Athena", "Ares", "Hephaestus", "Hermes"};
	public static HashMap<String, String[]> OPPOSING_GODS = new HashMap<String, String[]>();
	
	public static void setup() {
		OPPOSING_GODS.put("Zeus", new String[] {"Poseidon", "Hades"});
		OPPOSING_GODS.put("Poseidon", new String[] {"Zeus", "Hades"});
		OPPOSING_GODS.put("Hades", new String[] {"Poseidon", "Zeus"});
		OPPOSING_GODS.put("Hera", new String[]{"Hephaestus"});
		OPPOSING_GODS.put("Hephaestus", new String[]{"Hera"});
		OPPOSING_GODS.put("Demeter", new String[] {"Hermes"});
		OPPOSING_GODS.put("Hermes", new String[]{"Demeter"});
		OPPOSING_GODS.put("Aphrodite", new String[] {"Dionysus"});
		OPPOSING_GODS.put("Dionysus", new String[]{"Aphrodite"});
		OPPOSING_GODS.put("Athena", new String[]{"Ares"});
		OPPOSING_GODS.put("Ares", new String[]{"Athena"});
		OPPOSING_GODS.put("Artemis", new String[]{"Apollo"});
		OPPOSING_GODS.put("Apollo", new String[]{"Artemis"});
	}
}
