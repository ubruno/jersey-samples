package com.ericsson.charter.poc.storage;

import java.util.HashMap;
import java.util.Map;

import com.ericsson.charter.poc.bean.Title;

public class TitleStore {
	private static Map<Integer,Title> store;
	private static TitleStore instance = null;
	
	private TitleStore() {
		store = new HashMap<Integer,Title>();
		initOneContact();
	}
	
	public static Map<Integer,Title> getStore() {
		if(instance==null) {
			instance = new TitleStore();
		}
		return store;
	}
	
	private static void initOneContact() {
		Title title = new Title("Interstellar", 2014, "Mankind was born on Earth. It was never meant to die here.");
		store.put(title.getId(), title);
	}
}
