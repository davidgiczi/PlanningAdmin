package hu.david.giczi.catvhungaria.planningregister.model;


import java.util.Comparator;


public class PlanComparator implements Comparator<PlanMetaData> {

	@Override
	public int compare(PlanMetaData o1, PlanMetaData o2) {
		
		String[] one=o1.getPlanNumber().split("-|/");
		String[] two=o2.getPlanNumber().split("-|/");
		
		int planNumber1=Integer.parseInt(one[1]);
		int planNumber2=Integer.parseInt(two[1]);
		int planNumberYear1=Integer.parseInt(one[2]);
		int planNumberYear2=Integer.parseInt(two[2]);
		
	
		if(planNumberYear1<planNumberYear2) {
			
			return -1;
		}
		else if(planNumberYear1==planNumberYear2) {
			
			
			if(planNumber1<planNumber2) {
				
				return -1;
			}
			else if(planNumber1==planNumber2) {
				
				return o1.getId()<o2.getId() ? -1 : o1.getId()==o2.getId() ? 0 : 1;
			}
				
			
		}
		
			
		
		return 1;
	}

	
	
	
}
