package datastructures;

import java.util.HashMap;

/**
 * @author Zinan
 * @date 2022/10/24 16:07
 * @description 设计停车系统-哈希表版
 */
public class ParkingSystemHashMap {

    HashMap<Integer,Integer> parkingMap = new HashMap<>();
    public ParkingSystemHashMap(int big, int medium, int small) {
        parkingMap.put(1, big);
        parkingMap.put(2, medium);
        parkingMap.put(3, small);
    }
    
    public boolean addCar(int carType) {
        if(parkingMap.get(carType) > 0){
            parkingMap.put(carType, parkingMap.get(carType) - 1);
            return true;
        }   
        return false;
    }
}
