package edu.erciyes.trafficsimulation;

public class TrafficCalculator {

    private static int overplusNumber = 0;

    public void calculateTraffic(TrafficLight northLight, int northV, TrafficLight eastLight, int eastV, TrafficLight southLight, int southV, TrafficLight westLight, int westV) {
        int totalCar = northV + eastV + southV + westV;
        int timePerCar = 0;
        if (totalCar != 0) timePerCar = TrafficLight.CYCLE_TIME / totalCar;

        int northGreenTime = northV * timePerCar - 3;
        int eastGreenTime = eastV * timePerCar - 3;
        int southGreenTime = southV * timePerCar - 3;
        int westGreenTime = westV * timePerCar - 3;

        System.out.println("north green time : " + northGreenTime);
        System.out.println("east green time : " + eastGreenTime);
        System.out.println("south green time : " + southGreenTime);
        System.out.println("west green time : " + westGreenTime);

        boolean isOverplusNorth = isOverplus(northGreenTime);
        boolean isOverplusEast = isOverplus(eastGreenTime);
        boolean isOverplusSouth = isOverplus(southGreenTime);
        boolean isOverplusWest = isOverplus(westGreenTime);

        northGreenTime = checkTime(northGreenTime);
        eastGreenTime = checkTime(eastGreenTime);
        southGreenTime = checkTime(southGreenTime);
        westGreenTime = checkTime(westGreenTime);

        int totalTime = northGreenTime + eastGreenTime + southGreenTime + westGreenTime;
        int overplusPerLight = 0;
        if (overplusNumber != 0) overplusPerLight = (TrafficLight.CYCLE_TIME - totalTime) / overplusNumber;

        if (!isOverplusNorth) northGreenTime += overplusPerLight;
        if (!isOverplusEast) eastGreenTime += overplusPerLight;
        if (!isOverplusSouth) southGreenTime += overplusPerLight;
        if (!isOverplusWest) westGreenTime += overplusPerLight;

        northLight.setTimes(northGreenTime);
        eastLight.setTimes(eastGreenTime);
        southLight.setTimes(southGreenTime);
        westLight.setTimes(westGreenTime);

        System.out.println("north green time : " + northGreenTime);
        System.out.println("east green time : " + eastGreenTime);
        System.out.println("south green time : " + southGreenTime);
        System.out.println("west green time : " + westGreenTime);

        overplusNumber = 0;
    }

    private static int checkTime(int time) {
        int max = TrafficLight.MAX_GREEN_TIME;
        int min = TrafficLight.MIN_GREEN_TIME;
        if (time >= max) {
            time = max;
        } else if (time <= min) {
            time = min;
        }
        return time;
    }

    private static boolean isOverplus(int time) {
        int max = TrafficLight.MAX_GREEN_TIME;
        int min = TrafficLight.MIN_GREEN_TIME;
        if (time > max || time < min) {
            overplusNumber++;
            return true;
        }
        return false;
    }
}
