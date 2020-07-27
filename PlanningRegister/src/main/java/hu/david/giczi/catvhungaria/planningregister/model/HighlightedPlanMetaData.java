package hu.david.giczi.catvhungaria.planningregister.model;

import java.util.ArrayList;
import java.util.List;

public class HighlightedPlanMetaData {

	private PlanMetaData containerPlanMetaData;
	private String searchedExpression;
	private List<String> planMetaDataStringStore;
	private List<Integer> beginIndexStore;
	private List<Integer> endIndexStore;
	private final String preTag = "<span style=\"background-color: #FF69B4\">";
	private final String postTag = "</span>";
	public static List<PlanMetaData> highlightedPlanMetaDataStore = new ArrayList<>();

	public HighlightedPlanMetaData() {

	}

	public HighlightedPlanMetaData(PlanMetaData containerPlanMetaData, String searchedExpression) {

		this.containerPlanMetaData = containerPlanMetaData;
		this.searchedExpression = searchedExpression;
	}

	public void setSearchedExpression(String searchedExpression) {
		this.searchedExpression = searchedExpression;
	}

	public List<Integer> getBeginIndexStore() {
		return beginIndexStore;
	}

	public List<Integer> getEndIndexStore() {
		return endIndexStore;
	}

	public String getPreTag() {
		return preTag;
	}

	public String getPostTag() {
		return postTag;
	}

	public void createHighlightedGeoJob() {

		createContainerTextFromPlanMetaData();

		for (int i = 0; i < planMetaDataStringStore.size(); i++) {

			if (planMetaDataStringStore.get(i).contains(searchedExpression)) {

				createBeginIndexStore(planMetaDataStringStore.get(i));
				createEndIndexStore();
				planMetaDataStringStore.set(i, createHighlightedString(planMetaDataStringStore.get(i)));
				addHighlightedDataToContainerPlanMetaData();

			}

		}

		highlightedPlanMetaDataStore.add(containerPlanMetaData);
	}

	private List<String> createContainerTextFromPlanMetaData() {

		planMetaDataStringStore = new ArrayList<>();

		planMetaDataStringStore.add(containerPlanMetaData.getPlanName());
		planMetaDataStringStore.add(containerPlanMetaData.getComment());
		

		return planMetaDataStringStore;
	}

	public void createBeginIndexStore(String containerText) {

		beginIndexStore = new ArrayList<>();

		for (int i = 0; i <= containerText.length() - searchedExpression.length(); i++) {

			if (containerText.charAt(i) == searchedExpression.charAt(0)
					&& containerText.substring(i, i + searchedExpression.length()).equals(searchedExpression)) {

				beginIndexStore.add(i);

			}

		}

	}

	public void createEndIndexStore() {
		
		endIndexStore = new ArrayList<>();
		
		for(int i = 0; i < beginIndexStore.size(); i++) {
			
		int endIndex = beginIndexStore.get(i) + searchedExpression.length() - 1;
			
		if(i + 1 < beginIndexStore.size() && endIndex >= beginIndexStore.get(i + 1)) {
			
			continue;
		
		}
			
		endIndexStore.add(endIndex);
		
		}
			

	}

	
	
	public String createHighlightedString(String text) {

		char[] container = text.toCharArray();
		StringBuilder builder = new StringBuilder();
		boolean isOpenTag = false;

		for (int i = 0; i < container.length; i++) {

			
		 if (beginIndexStore.contains(i) && !isOpenTag) {

				builder.append(preTag);
				isOpenTag = true;

			} 
			 	 
		 builder.append(container[i]);	 
		 
		 if (endIndexStore.contains(i) && isOpenTag) {

				builder.append(postTag);
				isOpenTag = false;

			} 
					
	}


		return builder.toString();
	}

	private void addHighlightedDataToContainerPlanMetaData() {

		containerPlanMetaData.setPlanName(planMetaDataStringStore.get(0));
		containerPlanMetaData.setComment(planMetaDataStringStore.get(1));
		
	}

	public static void clearHighlightedPlanMetaDataStore() {

		highlightedPlanMetaDataStore.clear();
	}

}
