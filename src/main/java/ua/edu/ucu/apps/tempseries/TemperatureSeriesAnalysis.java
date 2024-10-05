package ua.edu.ucu.apps.tempseries;
import java.util.Arrays;

public class TemperatureSeriesAnalysis {
    private double[] temperatureSeries;
    private static final double ABSOLUTE_ZERO_CELSIUS = -273;
    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[0];
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        this.temperatureSeries = temperatureSeries.clone();
    }

    public double average() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("No temperatures available");
        }
        double sum = 0;
        for (double temp : temperatureSeries) {
            sum += temp;
        }
        return sum / temperatureSeries.length;
    }

    public double deviation() {
        double mean = average(); // use the average method
        double variance = 0;
        for (double temp : temperatureSeries) {
            variance += (temp - mean)*(temp - mean);
        }
        variance /= temperatureSeries.length;
        return Math.sqrt(variance);
    }

    public double min() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("No temperatures available");
        }
        double minTemp = temperatureSeries[0];
        for (double temp : temperatureSeries) {
            if (temp < minTemp) {
                minTemp = temp;
            }
        }
        return minTemp;
    }

    public double max() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("No temperatures available");
        }
        double maxTemp = temperatureSeries[0];
        for (double temp : temperatureSeries) {
            if (temp > maxTemp) {
                maxTemp = temp;
            }
        }
        return maxTemp;
    }

    public double findTempClosestToZero() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("No temperatures available");
        }
        double closest = temperatureSeries[0];
        for (double temp : temperatureSeries) {
            if (Math.abs(temp) < Math.abs(closest) 
                || (Math.abs(temp) == Math.abs(closest) && temp > closest)) {
                closest = temp;
            }
        }
        return closest;
    }

    public double findTempClosestToValue(double tempValue) {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("No temperatures available");
        }
        double closest = temperatureSeries[0];
        for (double temp : temperatureSeries) {
            if (Math.abs(temp - tempValue) < Math.abs(closest - tempValue)) {
                closest = temp;
            }
        }
        return closest;
    }

    public double[] findTempsLessThan(double tempValue) {
        return Arrays.stream(temperatureSeries)
                     .filter(temp -> temp < tempValue)
                     .toArray();
    }

    public double[] findTempsGreaterThan(double tempValue) {
        return Arrays.stream(temperatureSeries)
                     .filter(temp -> temp > tempValue)
                     .toArray();
    }

    public double[] findTempsInRange(double lowerBound, double upperBound) {
        return Arrays.stream(temperatureSeries)
                     .filter(temp -> temp >= lowerBound && temp <= upperBound)
                     .toArray();
    }

    public void reset() {
        this.temperatureSeries = new double[0];
    }

    public double[] sortTemps() {
        return Arrays.stream(temperatureSeries)
                     .sorted()
                     .toArray();
    }

    public TempSummaryStatistics summaryStatistics() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("No temperatures available");
        }
        double average = average();
        double deviation = deviation();
        double min = min();
        double max = max();
        return new TempSummaryStatistics(average, deviation, min, max);
    }

    public int addTemps(double... temps) {
    for (double temp : temps) {
        if (temp < ABSOLUTE_ZERO_CELSIUS) {
            throw new IllegalArgumentException(
                "Temp cant be below -273");
        }
    }

    if (temperatureSeries.length + temps.length > temperatureSeries.length) {
        double[] newTemps = new double[
            (temperatureSeries.length + temps.length) * 2
        ];
        System.arraycopy(
            temperatureSeries, 
            0, 
            newTemps, 
            0, 
            temperatureSeries.length);
        temperatureSeries = newTemps;
    }
    System.arraycopy(
            temps, 
            0, 
            temperatureSeries, 
            0, 
            temps.length);
    return temperatureSeries.length; }
}
