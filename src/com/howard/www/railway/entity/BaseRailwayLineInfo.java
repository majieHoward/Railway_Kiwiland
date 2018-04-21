package com.howard.www.railway.entity;

/**
 * 
 * @ClassName:  BaseRailwayLineInfo   
 * @Description:TODO  
 * @author: mayijie
 * @date:   2018年4月20日 上午10:38:41   
 *     
 * @Copyright: 2018 https://github.com/majieHoward Inc. All rights reserved.
 */
public class BaseRailwayLineInfo {
	private int distance;
	private char startingPoint;
	private char endPoint;

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public char getStartingPoint() {
		return startingPoint;
	}

	public void setStartingPoint(char startingPoint) {
		this.startingPoint = startingPoint;
	}

	public char getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(char endPoint) {
		this.endPoint = endPoint;
	}
}
