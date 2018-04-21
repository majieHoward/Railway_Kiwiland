package com.howard.www.railway;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.howard.www.railway.entity.RailwayLineInfo;
import com.howard.www.railway.entity.RailwayLineNodeInfo;
import com.howard.www.railway.entity.RailwayLineStation;

/**
 * 
 * @ClassName:  RailwayNetworkList   
 * @Description:TODO基于邻接表方式构造      
 * @author: mayijie
 * @date:   2018年4月19日 下午4:39:51   
 *     
 * @Copyright: 2018 https://github.com/majieHoward Inc. All rights reserved.
 */
public class RailwayNetworkList extends BaseRailwayNetwork implements RailwayNetwork{
	
	public int vlen; // 顶点个数
	RailwayLineStation[] stationListItems; // 顶点数组
    /**
     *  返回ch位置
     */
    private int getPosition(char ch, char[] vexs) {
        for (int i = 0; i < vlen; i++)
            if (vexs[i] == ch)
                return i;
        return -1;
    }

    public void obtainRailwayNetwork() {
        System.out.println("通勤铁路网路图");
        for (int i = 0; i < vlen; i++) {
            System.out.print(stationListItems[i].getTrainStationItemName() + "-->");
            RailwayLineNodeInfo railwayLineNode;
            if (stationListItems[i].getConnectTrainStationItem() != null) {
            	railwayLineNode = stationListItems[i].getConnectTrainStationItem();
                System.out.print("("+railwayLineNode.getEndPoint()+","+railwayLineNode.getDistance()+")");
                while (railwayLineNode.getFollowTrainStationItem() != null) {
                	railwayLineNode = railwayLineNode.getFollowTrainStationItem();
                    System.out.print("("+railwayLineNode.getEndPoint()+","+railwayLineNode.getDistance()+")");
                }
                System.out.print("\n");
            } else {
                System.out.print("\n");
            }
        }
    }

	@Override
	public void structRailwayNetwork() {
		
	}

	@Override
	public void structRailwayNetwork(List<RailwayLineInfo> itemsOfFixedPoint) {
		
	}

	@Override
	public void structRailwayNetwork(ConcurrentHashMap<String, RailwayLineInfo> fixedPointLineMap) {
        vlen = trainStationItems.length;
        // 初始化顶点,建立顶点表
        stationListItems = new RailwayLineStation[vlen];
        for (int i = 0; i < vlen; i++) {
            stationListItems[i] = new RailwayLineStation();
            stationListItems[i].setTrainStationItemIndex(getPosition(trainStationItems[i], trainStationItems)); 
            stationListItems[i].setTrainStationItemName(trainStationItems[i]);
            stationListItems[i].setConnectTrainStationItem(null);
        }
        ergodicRailwayLine(fixedPointLineMap);
	}
	
	@Override
	public void handleRailwayLineInfo(RailwayLineInfo railwayLineInfo) {
		// TODO Auto-generated method stub
		RailwayLineNodeInfo railwayLineNodeInfo = new RailwayLineNodeInfo();
        int vi = getPosition(railwayLineInfo.getStartingPoint(), trainStationItems);
        railwayLineNodeInfo.setEndPointIndex(getPosition(railwayLineInfo.getEndPoint(), trainStationItems));
        railwayLineNodeInfo.setDistance(railwayLineInfo.getDistance());
        railwayLineNodeInfo.setEndPoint(railwayLineInfo.getEndPoint());
        railwayLineNodeInfo.setStartingPoint(railwayLineInfo.getStartingPoint());
        /**
         * 新到达的线路放在最前方
         */
        railwayLineNodeInfo.setFollowTrainStationItem(stationListItems[vi].getConnectTrainStationItem());
        stationListItems[vi].setConnectTrainStationItem(railwayLineNodeInfo);
	}
	
	/**
     * 主函数
     * @param args
     */
    public static void main(String args[]) {
        RailwayNetworkList listDG = new RailwayNetworkList();
        listDG.initRailwayLine();
        listDG.obtainRailwayNetwork();
    }

	@Override
	public int[][] obtianStationMatrix() {
		// TODO Auto-generated method stub
		return null;
	}
}
