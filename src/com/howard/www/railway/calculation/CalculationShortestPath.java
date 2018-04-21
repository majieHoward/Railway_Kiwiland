package com.howard.www.railway.calculation;

import static com.howard.www.railway.RailwayLineParameters.*;

public class CalculationShortestPath {

	public void shortestDistanceSites(int[][] stationMatrix, char[] trainStationItems,int startPoint, int endPoint) {
		int[][] path=shortestDistance(stationMatrix,trainStationItems,new int[]{startPoint}, new int[]{endPoint});
	    if(path[startPoint][endPoint]==unreachable){
	    	System.out.println("仅通过一个中转站点无法从"+trainStationItems[startPoint]+"到"+trainStationItems[endPoint]);
	    }
		System.out.println(path[startPoint][endPoint]);
	}

	private int[][] shortestDistance(int[][] stationMatrix, char[] trainStationItems, int[] startPoints,
			int[] endPoints) {
		int[][] path = new int[trainStationItems.length][trainStationItems.length];
		/**
		 * dist 为stationMatrix铁路网络的拷贝矩阵
		 */
		int[][] dist = new int[trainStationItems.length][trainStationItems.length];

		// 初始化
		for (int i = 0; i < trainStationItems.length; i++) {
			for (int j = 0; j < trainStationItems.length; j++) {
				dist[i][j] = stationMatrix[i][j]; // "顶点i"到"顶点j"的路径长度为"i到j的权值"。
				path[i][j] = j; // "顶点i"到"顶点j"的最短路径是经过顶点j。
			}
		}

		// 计算最短路径
		for (int k = 0; k < trainStationItems.length; k++) {
			for (int start = 0; start < startPoints.length; start++) {
				for (int end = 0; end < endPoints.length; end++) {
					/**
					 * 从startingPoint到endPoint经过中转站点tempPoint
					 */
					// 如果经过下标为k顶点路径比原两点间路径更短，则更新dist[i][j]和path[i][j]
					int tmp;

					if (dist[startPoints[start]][k] == unreachable
							|| dist[k][endPoints[end]] == unreachable) {
						/**
						 * 两火车站直接不存在线路可以直达
						 */
						tmp = unreachable;
					} else {
						tmp = dist[startPoints[start]][k] + dist[k][endPoints[end]];
					}
					if (dist[startPoints[start]][endPoints[end]] > tmp) {
						// "i到j最短路径"对应的值设，为更小的一个(即经过k)
						dist[startPoints[start]][endPoints[end]] = tmp;
						// "i到j最短路径"对应的路径，经过k
						path[startPoints[start]][endPoints[end]] = path[startPoints[start]][k];

					}
					System.out.println(startPoints[start]+"  "+k+"  "+endPoints[end]+" "+tmp);
				}
			}
		}

		// 打印floyd最短路径的结果
		System.out.printf("floyd: \n");
		for (int i = 0; i < trainStationItems.length; i++) {
			for (int j = 0; j < trainStationItems.length; j++)
				System.out.printf("%2d  ", dist[i][j]);
			System.out.printf("\n");
		}
		return dist;
	}

	/**
	 * 表示两个站点之间不存在可以连通到达的铁路线路
	 */
	public void floyd(int[][] stationMatrix, char[] trainStationItems) {
		int[] startPoints = new int[trainStationItems.length];
		int[] endPoints = new int[trainStationItems.length];
		shortestDistance(stationMatrix, trainStationItems, startPoints, endPoints);
	}

}
