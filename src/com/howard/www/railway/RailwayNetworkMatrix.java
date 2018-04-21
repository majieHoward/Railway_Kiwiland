package com.howard.www.railway;

import static com.howard.www.railway.RailwayLineParameters.unreachable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import com.howard.www.railway.entity.RailwayLineInfo;
import com.howard.www.railway.util.UtilTools;
/**
 * 
 * @ClassName: RailwayNetworkMatrix
 * @Description:TODO 基于邻接矩阵方式构造
 * @author: mayijie
 * @date: 2018年4月19日 下午4:35:13
 * 
 * @Copyright: 2018 https://github.com/majieHoward Inc. All rights reserved.
 */
public class RailwayNetworkMatrix extends BaseRailwayNetwork implements RailwayNetwork {

	/*
	 * 火车线路网矩阵
	 */
	private int[][] stationMatrix;
	
	public int[][] obtianStationMatrix(){
		return this.stationMatrix;
	}

	private int getPosition(char ch) {
		return UtilTools.getPosition(ch, trainStationItems);
	}

	/**
	 * 
	 * <p>
	 * Title: obtainRailwayNetwork
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @see com.howard.www.railway.RailwayNetwork#obtainRailwayNetwork()
	 */
	public void obtainRailwayNetwork() {
		System.out.println("通勤铁路网路图");
		for (int i = 0; i < trainStationItems.length; i++) {
			for (int j = 0; j < trainStationItems.length; j++) {
				System.out.printf("%d ", stationMatrix[i][j]);
			}
			System.out.printf("\n");
		}
	}

	@Override
	public void structRailwayNetwork() {
		// TODO Auto-generated method stub

	}

	private void initMatrixAttribute() {
		// 初始化"顶点数"和"边数"
		int vlen = trainStationItems.length;
		// 初始化"边"
		stationMatrix = new int[vlen][vlen];
		for (int i = 0; i < trainStationItems.length; i++) {
			for (int j = 0; j < trainStationItems.length; j++) {
				stationMatrix[i][j] = unreachable;
			}

		}
	}

	@Override
	public void structRailwayNetwork(ConcurrentHashMap<String, RailwayLineInfo> fixedPointLineMap) {
		initMatrixAttribute();
		ergodicRailwayLine(fixedPointLineMap);
	}

	public void structRailwayNetwork(List<RailwayLineInfo> itemsOfFixedPoint) {

	}

	@Override
	public void handleRailwayLineInfo(RailwayLineInfo railwayLineInfo) {
		// TODO Auto-generated method stub
		int startPoint = getPosition(railwayLineInfo.getStartingPoint());
		int endPoint = getPosition(railwayLineInfo.getEndPoint());
		stationMatrix[startPoint][endPoint] = railwayLineInfo.getDistance();
	}
}
