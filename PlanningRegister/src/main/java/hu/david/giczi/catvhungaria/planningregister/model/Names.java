package hu.david.giczi.catvhungaria.planningregister.model;

import java.util.ArrayList;
import java.util.List;

public class Names {

	
	private static List<String> catvNames;
	private static List<String> upcNames;
	
	
	public Names() {
		
		catvNames=new ArrayList<String>();
		upcNames=new ArrayList<String>();
		
		catvNames.add("Ladányi János");
		catvNames.add("Hegedüs Judit");
		upcNames.add("Pászthory Tibor");
		upcNames.add("Nimmerfroh József");
		
	}


	public static List<String> getCatvNames() {
		return catvNames;
	}


	public static List<String> getUpcNames() {
		return upcNames;
	}
	
	
	public boolean addCATVName(String name) {
		
		if(!catvNames.contains(name)) {
			catvNames.add(name);
			return true;
		}
		
		return false;
	}
	
	public boolean addUPCName(String name) {
		
		if(!upcNames.contains(name)) {
			upcNames.add(name);
			return true;
		}
		
		return false;
	}
	
	public boolean removeCATVName(String name) {
		
		if(catvNames.contains(name)) {
			catvNames.remove(name);
			return true;
		}
		
		
		return false;
	}
	
	public boolean removeUPCName(String name) {
		
		if(upcNames.contains(name)) {
			upcNames.remove(name);
			return true;
		}
		
		
		return false;
	}
}
