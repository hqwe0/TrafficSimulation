package edu.erciyes.trafficsimulation;

public class TrafficCalculator {

    public void calculateTraffic(TrafficLight northLight, int northV,
                                 TrafficLight eastLight, int eastV,
                                 TrafficLight southLight, int southV,
                                 TrafficLight westLight, int westV) {

        final int YELLOW_TIME = 2;
        final int TOTAL_CYCLE = TrafficLight.CYCLE_TIME;
        final int TOTAL_GREEN = TOTAL_CYCLE - 4 * YELLOW_TIME; // Artık toplam yeşil süre = 112

        int totalCars = northV + eastV + southV + westV;
        if (totalCars == 0) {
            // araç yoksa herkes minimum
            northLight.setTimes(TrafficLight.MIN_GREEN_TIME);
            eastLight.setTimes(TrafficLight.MIN_GREEN_TIME);
            southLight.setTimes(TrafficLight.MIN_GREEN_TIME);
            westLight.setTimes(TrafficLight.MIN_GREEN_TIME);
            return;
        }

        // 1. Oransal ilk süreleri hesapla
        double northRatio = (double) northV / totalCars;
        double eastRatio  = (double) eastV  / totalCars;
        double southRatio = (double) southV / totalCars;
        double westRatio  = (double) westV  / totalCars;

        int northG = (int) Math.round(northRatio * TOTAL_GREEN);
        int eastG  = (int) Math.round(eastRatio  * TOTAL_GREEN);
        int southG = (int) Math.round(southRatio * TOTAL_GREEN);
        int westG  = (int) Math.round(westRatio  * TOTAL_GREEN);

        // 2. Limit dışı süreleri sabitle
        boolean fixNorth = false, fixEast = false, fixSouth = false, fixWest = false;
        if (northG < TrafficLight.MIN_GREEN_TIME) {
            northG = TrafficLight.MIN_GREEN_TIME;
            fixNorth = true;
        } else if (northG > TrafficLight.MAX_GREEN_TIME) {
            northG = TrafficLight.MAX_GREEN_TIME;
            fixNorth = true;
        }

        if (eastG < TrafficLight.MIN_GREEN_TIME) {
            eastG = TrafficLight.MIN_GREEN_TIME;
            fixEast = true;
        } else if (eastG > TrafficLight.MAX_GREEN_TIME) {
            eastG = TrafficLight.MAX_GREEN_TIME;
            fixEast = true;
        }

        if (southG < TrafficLight.MIN_GREEN_TIME) {
            southG = TrafficLight.MIN_GREEN_TIME;
            fixSouth = true;
        } else if (southG > TrafficLight.MAX_GREEN_TIME) {
            southG = TrafficLight.MAX_GREEN_TIME;
            fixSouth = true;
        }

        if (westG < TrafficLight.MIN_GREEN_TIME) {
            westG = TrafficLight.MIN_GREEN_TIME;
            fixWest = true;
        } else if (westG > TrafficLight.MAX_GREEN_TIME) {
            westG = TrafficLight.MAX_GREEN_TIME;
            fixWest = true;
        }

        // 3. Sabitlenmiş süreleri ve araçları topla
        int fixedTotal = 0;
        int fixedCars = 0;
        if (fixNorth) { fixedTotal += northG; fixedCars += northV; }
        if (fixEast)  { fixedTotal += eastG;  fixedCars += eastV; }
        if (fixSouth) { fixedTotal += southG; fixedCars += southV; }
        if (fixWest)  { fixedTotal += westG;  fixedCars += westV; }

        // 4. Kalan süreyi paylaştırılabilir yönlere oranlı olarak dağıt
        int remaining = TOTAL_GREEN - fixedTotal;
        int adjustableCars = totalCars - fixedCars;

        if (!fixNorth && adjustableCars > 0)
            northG = (int) Math.round((double) northV / adjustableCars * remaining);
        if (!fixEast && adjustableCars > 0)
            eastG = (int) Math.round((double) eastV / adjustableCars * remaining);
        if (!fixSouth && adjustableCars > 0)
            southG = (int) Math.round((double) southV / adjustableCars * remaining);
        if (!fixWest && adjustableCars > 0)
            westG = (int) Math.round((double) westV / adjustableCars * remaining);

        // 5. Işık sürelerini ata
        northLight.setTimes(northG);
        eastLight.setTimes(eastG);
        southLight.setTimes(southG);
        westLight.setTimes(westG);

        // 6. Debug
        System.out.println("North Green : " + northG);
        System.out.println("East  Green : " + eastG);
        System.out.println("South Green : " + southG);
        System.out.println("West  Green : " + westG);
        System.out.println("Toplam Yeşil : " + (northG + eastG + southG + westG));
        System.out.println("Toplam Sarı  : 8");
        System.out.println("Toplam Süre  : " + (northG + eastG + southG + westG + 8));
    }
}
