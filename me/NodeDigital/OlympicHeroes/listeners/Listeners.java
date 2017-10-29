package me.NodeDigital.OlympicHeroes.listeners;

import me.NodeDigital.OlympicHeroes.OlympicHeroes;
import me.NodeDigital.OlympicHeroes.listeners.gods.*;

public class Listeners {
	
	public static void initializeListeners(OlympicHeroes plugin) {
		new CraftListener(plugin);
		new MultiBlockListener(plugin);
		new PlayerListener(plugin);
		
		// God listeners
		new ZeusListener(plugin);
		new PoseidonListener(plugin);
		new HadesListener(plugin);
		new HeraListener(plugin);
		new HephaestusListener(plugin);
		new DionysusListener(plugin);
		new AthenaListener(plugin);
		new AresListener(plugin);
		new AphroditeListener(plugin);
		new DemeterListener(plugin);
		new HermesListener(plugin);
		new ArtemisListener(plugin);
		new ApolloListener(plugin);
	}
}
