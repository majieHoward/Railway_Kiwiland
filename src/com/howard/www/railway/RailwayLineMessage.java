package com.howard.www.railway;

import java.util.concurrent.ConcurrentHashMap;

import com.howard.www.railway.entity.RailwayLineInfo;
import com.howard.www.railway.util.UtilTools;

public class RailwayLineMessage {
    /**
     * 保存所有的两个站点字符组合
     */
	private StringBuffer point=new StringBuffer();
	/**
	 * 保存所有的站点
	 */
	private char[] vexs;
	
	/**
	 * 两个站点的字符组合作为KEY
	 */
	private ConcurrentHashMap<String, RailwayLineInfo> fixedPointLineMap = new ConcurrentHashMap<String, RailwayLineInfo>();

	public StringBuffer getPoint() {
		return point;
	}

	public void setPoint(StringBuffer point) {
		this.point = point;
	}

	public char[] getVexs() {
		this.vexs=UtilTools.removeRepeatedCharListSort(this.point.toString());
		return vexs;
	}

	public void setVexs(char[] vexs) {
		this.vexs = vexs;
	}

	public ConcurrentHashMap<String, RailwayLineInfo> getFixedPointLineMap() {
		return fixedPointLineMap;
	}

	public void setFixedPointLineMap(ConcurrentHashMap<String, RailwayLineInfo> fixedPointLineMap) {
		this.fixedPointLineMap = fixedPointLineMap;
	}

	public void evaluateFixedPointLine(final String allMessage){
		/**
		 * 截取两个站点的字符组合作为KEY值在fixedPointLineMap判断是否存在
		 */
		String keyOffixedPointLineMap=UtilTools.obtainLetters(allMessage);
		if(this.fixedPointLineMap.get(keyOffixedPointLineMap)==null){
			point.append(keyOffixedPointLineMap);
			this.fixedPointLineMap.put(keyOffixedPointLineMap, new RailwayLineInfo(allMessage));
		}else{
			/**
			 * 如果KEY存在就不满足给定的路线永远不会出现一次以上(提示错误)
			 */
			System.out.println("不满足给定的路线永远不会出现一次以上");
		}
	}
}
