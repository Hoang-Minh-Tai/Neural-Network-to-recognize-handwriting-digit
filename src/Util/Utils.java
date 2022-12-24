package Util;

public class Utils {
    public static double sigmoid(double x) {
        return 1d / (1 + Math.exp(-x));
    }

    public static double[] createRandomArray(int size, double min, double max) {
        if (size < 1) return null;

        double[] ar = new double[size];
        for (int i = 0; i < size; i++) {
            ar[i] = randomValue(min, max);
        }
        return ar;
    }

    public static double[][] createRandom2DArray(int sizeX, int sizeY, double min, double max) {
        if (sizeX < 1 || sizeY < 1) return null;

        double[][] ar = new double[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++) {
            ar[i] = createRandomArray(sizeY, min, max);
        }
        return ar;
    }

    public static double randomValue(double min, double max) {
        return Math.random() * (max - min) + min;
    }

    public static int indexOfHighestValue(double[] values) {
        int index = 0;
        for (int i = 1; i < values.length; i++) {
            if (values[i] > values[index]) {
                index = i;
            }
        }
        return index;
    }

    public static double[] doubleArrayFromString(String string) {
        String[] strings = string.replace("[", "").replace("]", "").split(", ");
        double[] result = new double[strings.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Double.parseDouble(strings[i]);
        }
        return result;
    }

    public static int[] intArrayFromString(String string) {
        String[] strings = string.replace("[", "").replace("]", "").split(", ");
        int[] result = new int[strings.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(strings[i]);
        }
        return result;
    }
}