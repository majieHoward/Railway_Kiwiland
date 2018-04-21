package com.howard.www.railway.calculation;

import java.util.Map;

import com.howard.www.railway.question.entity.ToDoQuestionInfo;

/**
 * 
 * @ClassName:  CalculationSpecifiedRouteDistance   
 * @Description:TODO 获取起始点到终点经过指定站点的路程     
 * @author: mayijie
 * @date:   2018年4月21日 下午4:39:51   
 *     
 * @Copyright: 2018 https://github.com/majieHoward Inc. All rights reserved.
 */
public class CalculationSpecifiedRouteDistance extends BaseCalculationRouteSite implements CalculationRailwayRoute {

	private static volatile CalculationSpecifiedRouteDistance instance = null;

	public static CalculationSpecifiedRouteDistance getInstance() {
		if (instance == null) {
			synchronized (CalculationSpecifiedRouteDistance.class) {
				if (instance == null) {
					instance = new CalculationSpecifiedRouteDistance();
				}
			}
		}
		return instance;
	}

	public void processingResultSet(ToDoQuestionInfo questionInfo, Map<String, Integer> routeLengthMap) {
		
		
		if (routeLengthMap != null) {
			if(questionInfo.getEndStation()==questionInfo.getStartStation()){
				questionInfo.setResultSet(routeLengthMap.toString());
			}else{
				if (routeLengthMap.get(questionInfo.getEntryParameter()) == null) {
					questionInfo.setResultSet("NO SUCH ROUTE");
				}else{
					questionInfo.setResultSet(String.valueOf(routeLengthMap.get(questionInfo.getEntryParameter())));
				}
			}
			
		}
	}

}
