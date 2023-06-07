import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.xy.DefaultXYDataset;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Stroke;

public class LineChart {
    public static void generateLineChart(double[] data, String title) {
        DefaultXYDataset dataset = new DefaultXYDataset();
        double[][] seriesData = new double[2][data.length];

        for (int i = 0; i < data.length; i++) {
            seriesData[0][i] = i;
            seriesData[1][i] = data[i];
        }

        dataset.addSeries("Data", seriesData);

        JFreeChart chart = ChartFactory.createXYLineChart(
                title, // Title
                "", // X-axis Label
                "", // Y-axis Label
                dataset, // Dataset
                PlotOrientation.VERTICAL,
                false, // Show Legend
                true, // Use tooltips
                false // Generate URLs
        );

        // Ustawienie tła wykresu na białe
        chart.setBackgroundPaint(Color.WHITE);

        // Pogrubienie linii
        XYPlot plot = (XYPlot) chart.getPlot();
        Stroke stroke = new BasicStroke(2.0f); // Grubość 2.0
        plot.getRenderer().setSeriesStroke(0, stroke);

        // Ustawienie tła panelu wykresu na białe
        plot.setBackgroundPaint(Color.WHITE);

        // Ustawienie marginesu dla wykresu
        int topMargin = 30;
        int bottomMargin = 30;
        int leftMargin = 30;
        int rightMargin = 30;
        plot.setInsets(new RectangleInsets(topMargin, leftMargin, bottomMargin, rightMargin));

        ChartFrame frame = new ChartFrame("Line Chart", chart);

        // Ustawienie rozmiaru okna wykresu
        int width = 700;  // Szerokość okna
        int height = 450; // Wysokość okna
        frame.setPreferredSize(new Dimension(width, height));

        frame.pack();
        frame.setVisible(true);
    }
}
