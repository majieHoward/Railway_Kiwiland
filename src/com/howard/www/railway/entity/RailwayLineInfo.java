package com.howard.www.railway.entity;

import com.howard.www.railway.util.UtilTools;

/**
 * 
 * @ClassName:  FixedPointDistance   
 * @Description:TODO 构造起始站到终点站信息以及站点之间的距离   
 * @author: mayijie
 * @date:   2018年4月19日 下午2:48:38   
 *     
 * @Copyright: 2018 https://github.com/majieHoward Inc. All rights reserved.
 */
public class RailwayLineInfo extends BaseRailwayLineInfo{
	private String allMessage;
	
	public String getAllMessage() {
		return allMessage;
	}

	public void setAllMessage(String allMessage) {
		this.allMessage = allMessage;
	}

	

	public RailwayLineInfo() {
		
	}
	
	private void initAttribute(char startingPoint, char endPoint, String distance) {
		/**
		 * 起始和结束的城镇将不会是同一个城镇startingPoint的值不等于endPoint
		 */
		if(startingPoint==endPoint){
			
		}else{
			setStartingPoint(startingPoint);
			setEndPoint(endPoint);
			setDistance(Integer.parseInt(String.valueOf(distance)));
		}
	}

	public RailwayLineInfo(String allMessage) {
		this.allMessage = allMessage;
		char[] allMessageChar = UtilTools.obtainLetterChars(this.allMessage);
		/**
		 * 判断allMessageChar数组的长度
		 */
		if (allMessageChar != null && allMessageChar.length == 2) {
			initAttribute(allMessageChar[0], allMessageChar[1], String.valueOf(UtilTools.obtainNumber(allMessage)));
		} else {
			System.out.println("错误上的输入格式距离为5的两个城镇(A到B)之间的路线被表示为AB5");
		}
	}
}
