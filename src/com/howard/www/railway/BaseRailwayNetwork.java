package com.howard.www.railway;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.howard.www.railway.entity.RailwayLineInfo;

public abstract class BaseRailwayNetwork {
	
	private String[] stationRoad;
	
	public String[] getStationRoad() {
		return stationRoad;
	}

	public void setStationRoad(String[] stationRoad) {
		this.stationRoad = stationRoad;
	}

	public String obtainStationRoad(){
		StringBuffer param=new StringBuffer();
		
		for(int i=0;i<this.stationRoad.length;i++){
			param.append(stationRoad[i]);
			if(i!=this.stationRoad.length-1){
				param.append(",");
			}	
		}
		return param.toString();
	}
	/**
	 * 保存所有的站点
	 */
	public char[] trainStationItems;
	
	public char[] obtainTrainStationItems(){
		return this.trainStationItems;
	}
	/**
	 * 
	 * @Title: structRailwayNetwork 
	 * @Description: TODO
	 *         抽象方法用来构造铁路站点网络图 @param: @param vexs @param: @param
	 *         itemsOfFixedPoint @return: void @throws
	 */
	public abstract void structRailwayNetwork(List<RailwayLineInfo> itemsOfFixedPoint);

	public abstract void structRailwayNetwork(ConcurrentHashMap<String, RailwayLineInfo> fixedPointLineMap);
    /**
     * 
     * @Title: handleRailwayLineInfo   
     * @Description: TODO 处理每一个线路信息对象   
     * @param: @param railwayLineInfo      
     * @return: void      
     * @throws
     */
	public abstract void handleRailwayLineInfo(RailwayLineInfo railwayLineInfo);
	
	public void initRailwayLine() {
		// AB5//BC4//CD8//DC8//DE6//AD5//CE2//EB3//AE7
		setStationRoad(new String[] { "AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7" });
		initRailwayLine(this.stationRoad);
	}

	private void initRailwayLine(String[] points) {
		RailwayLineMessage fixedPoint = new RailwayLineMessage();
		for (String point : points) {
			fixedPoint.evaluateFixedPointLine(point);
		}
		trainStationItems = fixedPoint.getVexs();
		structRailwayNetwork(fixedPoint.getFixedPointLineMap());
	}
	
	/**
	 * 
	 * @Title: ergodicRailwayLine   
	 * @Description: TODO    
	 * @param: @param fixedPointLineMap      
	 * @return: void      
	 * @throws
	 */
	public void ergodicRailwayLine(ConcurrentHashMap<String, RailwayLineInfo> fixedPointLineMap){
		Iterator<Map.Entry<String, RailwayLineInfo>> it = fixedPointLineMap.entrySet().iterator();
        Map.Entry<String, RailwayLineInfo> railwayLineInfoEntity;
        RailwayLineInfo railwayLineInfo;
        while (it.hasNext()) {
          railwayLineInfoEntity= it.next();
          railwayLineInfo=railwayLineInfoEntity.getValue();
          handleRailwayLineInfo(railwayLineInfo);
        }
	}	
}
