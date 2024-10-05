package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;

import ua.edu.ucu.apps.tempseries.TempSummaryStatistics;
import ua.edu.ucu.apps.tempseries.TemperatureSeriesAnalysis;

public class TemperatureSeriesAnalysisTest {
    @Test
    public void testAverage() {
        TemperatureSeriesAnalysis ts = new TemperatureSeriesAnalysis(new double[]{10, 20, 30});
        assertEquals(20.0, ts.average(), 0.001);
    }

    @Test
    public void testDeviationOnNonEmptySeries() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 2.0};
        TemperatureSeriesAnalysis analysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expected = Math.sqrt(9.6875);
        double actual = analysis.deviation();
        assertEquals(expected, actual, 0.00001);
    }

    @Test
    public void testMin() {
        TemperatureSeriesAnalysis ts = new TemperatureSeriesAnalysis(new double[]{10, 20, 30});
        assertEquals(10.0, ts.min(), 0.001);
    }

    @Test
    public void testMax() {
        TemperatureSeriesAnalysis ts = new TemperatureSeriesAnalysis(new double[]{10, 20, 30});
        assertEquals(30.0, ts.max(), 0.001);
    }

    @Test
    public void testFindTempClosestToZero() {
        TemperatureSeriesAnalysis ts = new TemperatureSeriesAnalysis(new double[]{-5, 2, 3, -1});
        assertEquals(-1.0, ts.findTempClosestToZero(), 0.001);
    }

    @Test
    public void testFindTempClosestToValue() {
        TemperatureSeriesAnalysis ts = new TemperatureSeriesAnalysis(new double[]{10, 20, 30});
        assertEquals(20.0, ts.findTempClosestToValue(21.0), 0.001);
    }

    @Test
    public void testFindTempsLessThan() {
        TemperatureSeriesAnalysis ts = new TemperatureSeriesAnalysis(new double[]{10, 20, 30});
        double[] result = ts.findTempsLessThan(25);
        assertArrayEquals(new double[]{10.0, 20.0}, result, 0.001);
    }

    @Test
    public void testFindTempsGreaterThan() {
        TemperatureSeriesAnalysis ts = new TemperatureSeriesAnalysis(new double[]{10, 20, 30});
        double[] result = ts.findTempsGreaterThan(15);
        assertArrayEquals(new double[]{20.0, 30.0}, result, 0.001);
    }

    @Test
    public void testFindTempsInRange() {
        TemperatureSeriesAnalysis ts = new TemperatureSeriesAnalysis(new double[]{10, 20, 30});
        double[] result = ts.findTempsInRange(15, 25);
        assertArrayEquals(new double[]{20.0}, result, 0.001);
    }

    @Test
    public void testReset() {
        TemperatureSeriesAnalysis ts = new TemperatureSeriesAnalysis(new double[]{10, 20, 30});
        ts.reset();
        assertThrows(IllegalArgumentException.class, ts::average);
    }

    @Test
    public void testSortTemps() {
        TemperatureSeriesAnalysis ts = new TemperatureSeriesAnalysis(new double[]{30, 10, 20});
        double[] sortedTemps = ts.sortTemps();
        assertArrayEquals(new double[]{10.0, 20.0, 30.0}, sortedTemps, 0.001);
    }

    @Test
    public void testSummaryStatistics() {
        TemperatureSeriesAnalysis ts = new TemperatureSeriesAnalysis(new double[]{3.0, -5.0, 1.0, 2.0});
        TempSummaryStatistics stats = ts.summaryStatistics();
        assertEquals(0.25, stats.getAverage(), 0.001);
        assertEquals(Math.sqrt(9.6875), stats.getDeviation(), 0.001);
        assertEquals(-5.0, stats.getMin(), 0.001);
        assertEquals(3.0, stats.getMax(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionOnEmptySeries() {
        TemperatureSeriesAnalysis ts = new TemperatureSeriesAnalysis();
        ts.average();
    }
}
