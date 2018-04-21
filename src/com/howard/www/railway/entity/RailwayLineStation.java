package com.howard.www.railway.entity;

public class RailwayLineStation {
	/**
	 *  起始station在stationListItems 中的下标值
	 */
	private int trainStationItemIndex;
	/**
	 *  station名称A,B,C,D,E
	 */
	private char trainStationItemName;
	
	/**
	 * 当前station所连接的一个station(connectTrainStationItem)
	 */
	private RailwayLineNodeInfo connectTrainStationItem; 

	public int getTrainStationItemIndex() {
		return trainStationItemIndex;
	}

	public void setTrainStationItemIndex(int trainStationItemIndex) {
		this.trainStationItemIndex = trainStationItemIndex;
	}

	public char getTrainStationItemName() {
		return trainStationItemName;
	}

	public void setTrainStationItemName(char trainStationItemName) {
		this.trainStationItemName = trainStationItemName;
	}

	public RailwayLineNodeInfo getConnectTrainStationItem() {
		return connectTrainStationItem;
	}

	public void setConnectTrainStationItem(RailwayLineNodeInfo connectTrainStationItem) {
		this.connectTrainStationItem = connectTrainStationItem;
	}

}
