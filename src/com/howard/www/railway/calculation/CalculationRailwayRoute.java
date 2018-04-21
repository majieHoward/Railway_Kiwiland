package com.howard.www.railway.calculation;

import java.util.Map;

import com.howard.www.railway.question.entity.ToDoQuestionInfo;

public interface CalculationRailwayRoute {
	public void initParameter(int[][] stationMatrix, char[] trainStationItems);

    public void dealingWithProblem(ToDoQuestionInfo questionInfo);
    
    public Map<String, Integer> reachableRailwayLine(int startPoint, int endPoint);

    public Map<String, Integer> dealingWithProblem(int startPoint, int endPoint);
    
    public Map<String, Integer> dealingWithProblem(char beginStation, char endStation);
    
}
