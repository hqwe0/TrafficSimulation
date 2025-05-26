package edu.erciyes.trafficsimulation;

public class TrafficCalculator {

    private static int overplusNumber = 0;

    public static void calculateTraffic(TrafficLight northLight, double northV, TrafficLight eastLight, double eastV, TrafficLight southLight, double southV, TrafficLight westLight, double westV) {
        double totalCar = northV + eastV + southV + westV;
        double timePerCar = 0;
        if (totalCar != 0) timePerCar = TrafficLight.CYCLE_TIME / totalCar;

        double northGreenTime = northV * timePerCar;
        double eastGreenTime = eastV * timePerCar;
        double southGreenTime = southV * timePerCar;
        double westGreenTime = westV * timePerCar;

        boolean isOverplusNorth = isOverplus(northGreenTime);
        boolean isOverplusEast = isOverplus(eastGreenTime);
        boolean isOverplusSouth = isOverplus(southGreenTime);
        boolean isOverplusWest = isOverplus(westGreenTime);

        northGreenTime = checkTime(northGreenTime);
        eastGreenTime = checkTime(eastGreenTime);
        southGreenTime = checkTime(southGreenTime);
        westGreenTime = checkTime(westGreenTime);

        double totalTime = northGreenTime + eastGreenTime + southGreenTime + westGreenTime;
        double overplusPerLight = 0;
        if (overplusNumber != 0) overplusPerLight = (TrafficLight.CYCLE_TIME - totalTime) / overplusNumber;

        if (isOverplusNorth) northGreenTime += overplusPerLight;
        if (isOverplusEast) eastGreenTime += overplusPerLight;
        if (isOverplusSouth) southGreenTime += overplusPerLight;
        if (isOverplusWest) westGreenTime += overplusPerLight;

        System.out.println("north green time : " + northGreenTime);
        System.out.println("east green time : " + eastGreenTime);
        System.out.println("south green time : " + southGreenTime);
        System.out.println("west green time : " + westGreenTime);

        overplusNumber = 0;
    }

    private static double checkTime(double time) {
        double max = TrafficLight.MAX_GREEN_TIME;
        double min = TrafficLight.MIN_GREEN_TIME;
        if (time >= max) {
            time = max;
        } else if (time <= min) {
            time = min;
        }
        return time;
    }

    private static boolean isOverplus(double time) {
        double max = TrafficLight.MAX_GREEN_TIME;
        double min = TrafficLight.MIN_GREEN_TIME;
        if (time > max || time < min) {
            overplusNumber++;
            return true;
        }
        return false;
    }
}
