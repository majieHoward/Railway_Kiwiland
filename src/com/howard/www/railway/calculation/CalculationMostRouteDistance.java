package com.howard.www.railway.calculation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.howard.www.railway.question.entity.ToDoQuestionInfo;
import com.howard.www.railway.util.UtilTools;

/**
 * 
 * @ClassName:  CalculationMostRouteDistance   
 * @Description:TODO 获取起始点到终点经过指定站点的路程     
 * @author: mayijie
 * @date:   2018年4月21日 下午4:39:51   
 *     
 * @Copyright: 2018 https://github.com/majieHoward Inc. All rights reserved.
 */
public class CalculationMostRouteDistance extends BaseCalculationRouteSite implements CalculationRailwayRoute {

	private static volatile CalculationMostRouteDistance instance = null;

	public static CalculationMostRouteDistance getInstance() {
		if (instance == null) {
			synchronized (CalculationMostRouteDistance.class) {
				if (instance == null) {
					instance = new CalculationMostRouteDistance();
				}
			}
		}
		return instance;
	}

	public void processingResultSet(ToDoQuestionInfo questionInfo, Map<String, Integer> routeLengthMap) {
		if (routeLengthMap != null) {
			int counter = 0;
			List<String> stationItems=new ArrayList<String>();
			Map<String,Integer> tempMap;
			int less = Integer.parseInt(questionInfo.getEntryParameter().split("\\|")[1])+1;
			char[] stations;
			int outSide;
			int inSide;
			String tempName;
			for (String routeName : routeLengthMap.keySet()) {
				outSide=UtilTools.obtainLetterCharItems(routeName).length;
				if(UtilTools.obtainLetterCharItems(routeName).length<=less){
					if(!stationItems.contains(routeName)){
						stationItems.add(routeName);
						counter++;
					}	
				}
				if(outSide<less){
					stations=UtilTools.obtainLetterCharItems(routeName);
					for(int i=0;i<stations.length;i++){
						inSide=outSide;
						if(i==(stations.length-1)){
							tempMap=dealingWithProblem(stations[i], stations[i]);
						}else{
							tempMap=dealingWithProblem(stations[i], stations[i+1]);
						}
						for (String name : tempMap.keySet()){
							if(UtilTools.obtainLetterCharItems(name).length!=2){
								while(inSide<less){
									/**
									 * 判断是否在最后
									 */
									if(i==(stations.length-1)){
										inSide+=UtilTools.obtainLetterCharItems(name).length-1;
									}else{
										inSide+=UtilTools.obtainLetterCharItems(name).length-2;
									}
									
									if(inSide<=less){
										if(i==(stations.length-1)){
											tempName=UtilTools.insertString(stations,UtilTools.obtainLetterCharItems(name),i,i);
										}else{
											tempName=UtilTools.insertString(stations,UtilTools.obtainLetterCharItems(name),i,i+1);
										}
										
										if(!stationItems.contains(tempName)){
											stationItems.add(tempName);
											inSide=outSide;
											counter++;
										}
									}
								}
								if(inSide>less){
									inSide=outSide;
								}
								
							}
							
						}
					}
				}
			}
			StringBuffer temp=new StringBuffer();
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
