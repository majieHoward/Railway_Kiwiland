package com.howard.www.railway.boot;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.howard.www.railway.RailwayNetwork;
import com.howard.www.railway.RailwayNetworkMatrix;
import com.howard.www.railway.calculation.CalculationRailwayRoute;
import com.howard.www.railway.question.entity.ToDoQuestionInfo;
import com.howard.www.railway.util.UtilTools;

public class RailwayNetworkBoot {
	
	private ConcurrentHashMap<String, CalculationRailwayRoute> processingMethodMap = new ConcurrentHashMap<String, CalculationRailwayRoute>();
	/**
	 * questionItems中保存所有需要解决的问题
	 */
	private List<ToDoQuestionInfo> questionItems=new ArrayList<ToDoQuestionInfo>();
	
	public ConcurrentHashMap<String, CalculationRailwayRoute> getProcessingMethodMap() {
		return processingMethodMap;
	}

	public void setProcessingMethodMap(ConcurrentHashMap<String, CalculationRailwayRoute> processingMethodMap) {
		this.processingMethodMap = processingMethodMap;
	}

	/**
	 * 初始化所有的计算火车线路的类,这些用来计算的类实现同一个接口
	 * @param railwayNetworkMatrix
	 */
	private  void initControllerMethod(RailwayNetwork railwayNetworkMatrix){
		BootInit.initControllerMethod(railwayNetworkMatrix, processingMethodMap);
	}
	/**
	 * 构造所有的问题
	 */
	private void initQuestionItems(){
		BootInit.initQuestionItems(questionItems);
	}
	
	public List<ToDoQuestionInfo> getQuestionItems() {
		return questionItems;
	}

	public void setQuestionItems(List<ToDoQuestionInfo> questionItems) {
		this.questionItems = questionItems;
	}

	public static void main(String[] args) {
		RailwayNetwork railwayNetworkMatrix = new RailwayNetworkMatrix();
		railwayNetworkMatrix.initRailwayLine();
		RailwayNetworkBoot boot=new RailwayNetworkBoot(); 
		boot.initControllerMethod(railwayNetworkMatrix);
		boot.initQuestionItems();
		for(ToDoQuestionInfo questionItem:boot.getQuestionItems()){
			if(!UtilTools.isEmpty(questionItem.getProcessingMethod())){
				if(boot.processingMethodMap.get(questionItem.getProcessingMethod())!=null){
					boot.processingMethodMap.get(questionItem.getProcessingMethod()).dealingWithProblem(questionItem);
				}	
			}
		}                          
		System.out.println("Graph:"+railwayNetworkMatrix.obtainStationRoad()+"\nExpected Output:");
		for(ToDoQuestionInfo questionItem:boot.getQuestionItems()){
			System.out.println(questionItem.obtainDetailsOfTheProblem());
		}
	}

}
