package datastructures;

/**
 * @author Zinan
 * @date 2022/10/24 16:07
 * @description 设计停车系统-简单计数版
 */
public class ParkingSystemBasicNum {

    int big, medium, small;
    public ParkingSystemBasicNum(int big, int medium, int small) {
        this.big = big;
        this.medium = medium;
        this.small = small;
    }
    
    public boolean addCar(int carType) {
        switch(carType){
            case 1:
                if(big == 0) {
                    return false;
                }
                big--;
                break;
            case 2:
                if(medium == 0) {
                    return false;
                }
                medium--;
                break;
            case 3:
                if(small == 0) {
                    return false;
                }
                small--;
                break;
            default: return false;
        }   
        return true;
    }
}
