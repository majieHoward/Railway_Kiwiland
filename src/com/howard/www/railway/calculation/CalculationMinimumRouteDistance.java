package com.howard.www.railway.calculation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.howard.www.railway.question.entity.ToDoQuestionInfo;
import com.howard.www.railway.util.UtilTools;

/**
 * 
 * @ClassName: CalculationMinimumRouteDistance
 * @Description:TODO 获取起始站点到终点的路程
 * @author: mayijie
 * @date: 2018年4月21日 下午4:39:51
 * 
 * @Copyright: 2018 https://github.com/majieHoward Inc. All rights reserved.
 */
public class CalculationMinimumRouteDistance extends BaseCalculationRouteSite implements CalculationRailwayRoute {

	private static volatile CalculationMinimumRouteDistance instance = null;

	public static CalculationMinimumRouteDistance getInstance() {
		if (instance == null) {
			synchronized (CalculationMinimumRouteDistance.class) {
				if (instance == null) {
					instance = new CalculationMinimumRouteDistance();
				}
			}
		}
		return instance;
	}

	public void processingResultSet(ToDoQuestionInfo questionInfo, Map<String, Integer> routeLengthMap) {
		if (routeLengthMap != null) {
			// 这里将map.entrySet()转换成list
			List<Map.Entry<String, Integer>> routeLengthList = new ArrayList<Map.Entry<String, Integer>>(
					routeLengthMap.entrySet());
			// 然后通过比较器来实现排序
			Collections.sort(routeLengthList, new Comparator<Map.Entry<String, Integer>>() {
				@Override
				public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
					return o1.getValue().compareTo(o2.getValue());
				}

			});
			
			StringBuffer param=new StringBuffer();
			param.append(String.valueOf(routeLengthList.get(0).getValue()));
			param.append("     ");
			param.append(questionInfo.getSample());
			param.append("(");
			param.append(UtilTools.addSymbolToChar(routeLengthList.get(0).getKey(),"-"));
			param.append(")");
			questionInfo.setResultSet(param.toString());
		}
	}

}
