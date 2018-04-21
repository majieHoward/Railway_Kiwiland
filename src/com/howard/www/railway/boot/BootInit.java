package com.howard.www.railway.boot;

import static com.howard.www.railway.question.entity.QuestionItems.pendingQuestion;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.howard.www.railway.RailwayNetwork;
import com.howard.www.railway.calculation.CalculationCircleRouteDistance;
import com.howard.www.railway.calculation.CalculationJustRightRouteDistance;
import com.howard.www.railway.calculation.CalculationMinimumRouteDistance;
import com.howard.www.railway.calculation.CalculationMostRouteDistance;
import com.howard.www.railway.calculation.CalculationRailwayRoute;
import com.howard.www.railway.calculation.CalculationSpecifiedRouteDistance;
import com.howard.www.railway.question.entity.ToDoQuestionInfo;

public class BootInit {
	/**
	 * 初始化所有的计算火车线路的类,这些用来计算的类实现同一个接口
	 * @param railwayNetworkMatrix
	 */
	public static void initControllerMethod(RailwayNetwork railwayNetworkMatrix,ConcurrentHashMap<String, CalculationRailwayRoute> processingMethodMap){
		CalculationRailwayRoute routeSite=CalculationSpecifiedRouteDistance.getInstance();
		routeSite.initParameter(railwayNetworkMatrix.obtianStationMatrix(), railwayNetworkMatrix.obtainTrainStationItems());
		processingMethodMap.put("specifiedRouteDistance", routeSite);
		routeSite=CalculationMinimumRouteDistance.getInstance();
		routeSite.initParameter(railwayNetworkMatrix.obtianStationMatrix(), railwayNetworkMatrix.obtainTrainStationItems());
		processingMethodMap.put("minimumRouteDistance", routeSite);
		routeSite=CalculationCircleRouteDistance.getInstance();
		routeSite.initParameter(railwayNetworkMatrix.obtianStationMatrix(), railwayNetworkMatrix.obtainTrainStationItems());
		processingMethodMap.put("circleRouteDistance", routeSite);
		routeSite=CalculationMostRouteDistance.getInstance();
		routeSite.initParameter(railwayNetworkMatrix.obtianStationMatrix(), railwayNetworkMatrix.obtainTrainStationItems());
		processingMethodMap.put("mostRouteDistance", routeSite);
		routeSite=CalculationJustRightRouteDistance.getInstance();
		routeSite.initParameter(railwayNetworkMatrix.obtianStationMatrix(), railwayNetworkMatrix.obtainTrainStationItems());
		processingMethodMap.put("justRightRouteDistance", routeSite);
	}
	
	public static void initQuestionItems(List<ToDoQuestionInfo> questionItems){
		for(int questionIndex=0;questionIndex<pendingQuestion.length;questionIndex++){
			questionItems.add(new ToDoQuestionInfo(pendingQuestion[questionIndex],questionIndex+1));
		}
	}
	
}
