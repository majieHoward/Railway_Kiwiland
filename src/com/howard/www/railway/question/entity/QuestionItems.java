package com.howard.www.railway.question.entity;

public interface QuestionItems {

	public static final String[][] pendingQuestion = {
		{ "The distance of the route A-B-C","ABC","specifiedRouteDistance"}, 
		{ "The distance of the route A-D","AD","specifiedRouteDistance"}, 
		{ "The distance of the route A-D-C","ADC","specifiedRouteDistance"}, 
		{ "The distance of the route A-E-B-C-D","AEBCD","specifiedRouteDistance"}, 
		{ "The distance of the route A-E-D" ,"AED","specifiedRouteDistance"}, 
		{ "The number of trips starting at C and ending at C with a maximum of 3 stops","CC|3","mostRouteDistance"}, 
		{ "The number of trips starting at A and ending at C with exactly 4 stops","AC|4","justRightRouteDistance"},
		{ "The length of the shortest route (in terms of distance to travel) from A to C","AC","minimumRouteDistance"}, 
		{ "The length of the shortest route (in terms of distance to travel) from B to B","BB","minimumRouteDistance"}, 
		{ "The number of different routes from C to C with a distance of less than 30","CC|30","circleRouteDistance"} 
	};
}
