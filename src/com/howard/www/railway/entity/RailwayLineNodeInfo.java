package com.howard.www.railway.entity;


public class RailwayLineNodeInfo extends BaseRailwayLineInfo{
	/**
	 * 终点站在stationListItems 中的下标值
	 */
	private int endPointIndex;    
	/**
	 * 起点station所连接的一个station
	 */
    private RailwayLineNodeInfo followTrainStationItem; 

	public int getEndPointIndex() {
		return endPointIndex;
	}

	public void setEndPointIndex(int endPointIndex) {
		this.endPointIndex = endPointIndex;
	}

	public RailwayLineNodeInfo getFollowTrainStationItem() {
		return followTrainStationItem;
	}

	public void setFollowTrainStationItem(RailwayLineNodeInfo followTrainStationItem) {
		this.followTrainStationItem = followTrainStationItem;
	}
     
}
