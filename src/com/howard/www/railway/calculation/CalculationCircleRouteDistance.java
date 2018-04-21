package com.howard.www.railway.calculation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.howard.www.railway.question.entity.ToDoQuestionInfo;
import com.howard.www.railway.util.UtilTools;

/**
 * 
 * @ClassName: CalculationSpecifiedRouteDistance
 * @Description:TODO 获取起始点到终点经过指定站点的路程
 * @author: mayijie
 * @date: 2018年4月21日 下午4:39:51
 * 
 * @Copyright: 2018 https://github.com/majieHoward Inc. All rights reserved.
 */
public class CalculationCircleRouteDistance extends BaseCalculationRouteSite implements CalculationRailwayRoute {

	private static volatile CalculationCircleRouteDistance instance = null;

	public static CalculationCircleRouteDistance getInstance() {
		if (instance == null) {
			synchronized (CalculationCircleRouteDistance.class) {
				if (instance == null) {
					instance = new CalculationCircleRouteDistance();
				}
			}
		}
		return instance;
	}

	public void processingResultSet(ToDoQuestionInfo questionInfo, Map<String, Integer> routeLengthMap) {
		if (routeLengthMap != null) {
			List<String> stationItems=new ArrayList<String>();
			int counter = 0;
			int less = Integer.parseInt(questionInfo.getEntryParameter().split("\\|")[1]);
			int param = 0;
			int insideParam=0;
			String tempLine=null;
			String insideTempLine=null;
			StringBuffer temp=new StringBuffer();
			for (String routeName : routeLengthMap.keySet()) {
				tempLine=UtilTools.removeLastCharacter(routeName);
				param=routeLengthMap.get(routeName);
				if (param < less) {
					stationItems.add(routeName);
					counter++;
				}
				for (String name : routeLengthMap.keySet()) {
					insideTempLine=tempLine;
					insideParam=param;
					while (insideParam < less) {
						insideParam += routeLengthMap.get(name);
						if (insideParam < less) {
							temp.setLength(0);
							temp.append(insideTempLine);
							temp.append(name);
							stationItems.add(temp.toString());
							counter++;
						}
					}
				}
			}
			temp.setLength(0);
			temp.append(String.valueOf(counter));
			temp.append("     ");
			temp.append(questionInfo.getSample());
			for(String stationItem:stationItems){
				temp.append("(");
				temp.append(UtilTools.addSymbolToChar(stationItem,"-"));
				temp.append(")");
			}
			
			questionInfo.setResultSet(temp.toString());
		}
	}

}
