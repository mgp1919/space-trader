package src.sample.PlanetRelated;

public class PlanetList {
    private static Planet[] arrPlanets = new Planet[10];
    private static int mapSize = arrPlanets.length + 1;
    private static Planet[][] arrMapPlanets = new Planet[mapSize][mapSize];
    private static boolean setWinCon = false;

    public static void initialize() {
        for (int numPlanets = 0; numPlanets < arrPlanets.length; numPlanets++) {
            if (!setWinCon) {
                arrPlanets[numPlanets] = new Planet(true);
                setWinCon = true;
            } else {
                arrPlanets[numPlanets] = new Planet();
            }
        }
    }

    public static Planet[] getArrPlanets() {
        return arrPlanets;
    }

    public static void setMap() {
        for (int i = 0; i < arrPlanets.length; i++) {
            int rowIndex = (int) (Math.random() * arrPlanets.length + 1);
            int columnIndex = (int) (Math.random() * arrPlanets.length + 1);
            while (arrMapPlanets[rowIndex][columnIndex] != null) {
                rowIndex = (int) (Math.random() * arrPlanets.length + 1);
                columnIndex = (int) (Math.random() * arrPlanets.length + 1);
            }
            arrPlanets[i].setRowIndex(rowIndex);
            arrPlanets[i].setColumnIndex(columnIndex);
            arrPlanets[i].setDescription();
            arrMapPlanets[rowIndex][columnIndex] = arrPlanets[i];
        }
    }
}
