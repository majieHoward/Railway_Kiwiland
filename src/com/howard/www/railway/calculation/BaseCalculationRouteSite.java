package com.howard.www.railway.calculation;

import static com.howard.www.railway.RailwayLineParameters.unreachable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.howard.www.railway.question.entity.ToDoQuestionInfo;
import com.howard.www.railway.util.UtilTools;

public abstract class BaseCalculationRouteSite {
	// 邻接矩阵
	private int[][] stationMatrix;
	// 顶点数组
	private char[] trainStationItems;
	// 顶点的数目
	private int trainStationItemsNum;

	public void initParameter(int[][] stationMatrix, char[] trainStationItems) {
		if (stationMatrix.length != stationMatrix[0].length) {
			throw new IllegalArgumentException("该邻接矩阵不是方阵");
		}
		if (stationMatrix.length != trainStationItems.length) {
			throw new IllegalArgumentException("结点数量和邻接矩阵大小不一致");
		}
		this.stationMatrix = stationMatrix;
		this.trainStationItems = trainStationItems;
		trainStationItemsNum = stationMatrix.length;
	}

	private List<Integer> removeDistance(int begin, int end, List<Integer> distance) {
		for (int i = begin; i <= end; i++) {
			distance.remove(i);
		}
		return distance;
	}

	private String obtainRailwayRoute(List<Character> railwayNames) {
		if (railwayNames != null && railwayNames.size() > 0) {
			StringBuffer railwayLine = new StringBuffer();
			for (Character railwayName : railwayNames) {
				railwayLine.append(railwayName);
			}
			return railwayLine.toString();
		}
		return null;
	}

	private int obtainRailwayRouteLength(List<Integer> railwayDistances) {
		if (railwayDistances.size() > 0 && railwayDistances != null) {
			int distanceCount = 0;
			for (int railwayDistance : railwayDistances) {
				distanceCount += railwayDistance;
			}
			return distanceCount;
		}
		return unreachable;
	}

	private void obtainRouteSite(int begin, int end, List<Character> route, List<Integer> distance,
			Map<String, Integer> routeLengthMap) {
		/**
		 * 添加当前遍历开始的第一个站点
		 */
		route.add(trainStationItems[begin]);
		/**
		 * 遍历所有的站点获得第一个站点到达所有站点的可能性
		 */
		for (int i = 0; i < trainStationItemsNum; i++) {
			removeDistance(route.size() - 1, distance.size() - 1, distance);
			/**
			 * 判断起始站点到i站点是否存可以直接到达
			 */
			if ((stationMatrix[begin][i] > 0 && stationMatrix[begin][i] < unreachable)) {
				/**
				 * 两个站点之间有可以连接的铁路线路
				 */

				if (!route.contains(trainStationItems[i])) {
					route.add(trainStationItems[i]);
					distance.add(stationMatrix[begin][i]);
				}
				/**
				 * 判断当前线路是否已经走过
				 */
				if (route.get(0).equals(trainStationItems[i])) {
					route.add(trainStationItems[i]);
					distance.add(stationMatrix[begin][i]);
					/**
					 * 存在循环状态遍历到的节点和当前线路的第一个节点相同
					 */
					if (i == end) {
						routeLengthMap.put(obtainRailwayRoute(route), obtainRailwayRouteLength(distance));
					}
					route = new LinkedList<Character>(route);
					distance = new LinkedList<Integer>(distance);
					route.remove(route.size() - 1);
				} else {
					if (i == end) {
						routeLengthMap.put(obtainRailwayRoute(route), obtainRailwayRouteLength(distance));
						route = new LinkedList<Character>(route);
						/**
						 * 回退到之前的站点,测试后一个站点是否可达
						 */
						route.remove(route.size() - 1);
						distance = new LinkedList<Integer>(distance);
					} else if (route.contains(trainStationItems[i])
							&& route.get(route.size() - 1).equals(trainStationItems[i])) {
						/**
						 * 构成了一回路
						 */
						/**
						 * 递归调用下可能的线路需要一个新的route
						 */
						route.remove(route.size() - 1);
						obtainRouteSite(i, end, new LinkedList<Character>(route), new LinkedList<Integer>(distance),
								routeLengthMap);
					}
				}

			}

			if ((trainStationItemsNum) - 1 == i) {
				return;
			}
		}
	}

	public Map<String, Integer> reachableRailwayLine(int startPoint, int endPoint) {
		Map<String, Integer> routeLengthMap = new HashMap<String, Integer>();
		List<Character> route = new LinkedList<Character>();
		List<Integer> distance = new LinkedList<Integer>();
		obtainRouteSite(startPoint, endPoint, route, distance, routeLengthMap);
		return routeLengthMap;
	}

	public void dealingWithProblem(ToDoQuestionInfo questionInfo) {
		if (questionInfo != null && !UtilTools.isEmpty(questionInfo.getEntryParameter())) {
			char[] stationItems = UtilTools.obtainLetterCharItems(questionInfo.getEntryParameter().split("\\|")[0]);
			if (stationItems.length >= 2) {
				questionInfo.setStartStation(stationItems[0]);
				questionInfo.setEndStation(stationItems[stationItems.length - 1]);
				processingResultSet(questionInfo,
						dealingWithProblem(stationItems[0], stationItems[stationItems.length - 1]));

			}
		}
	}

	public Map<String, Integer> dealingWithProblem(int startPoint, int endPoint) {
		return this.reachableRailwayLine(startPoint, endPoint);
	}

	public Map<String, Integer> dealingWithProblem(char beginStation, char endStation) {
		return dealingWithProblem(UtilTools.getPosition(beginStation, trainStationItems),
				UtilTools.getPosition(endStation, trainStationItems));
	}

	public abstract void processingResultSet(ToDoQuestionInfo questionInfo, Map<String, Integer> routeLengthMap);
}
