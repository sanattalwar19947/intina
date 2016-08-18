import java.io.File;
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class XYChart{
	public static void main(String[] args) {
		// Create a simple XY chart
		XYSeries series = new XYSeries("XYGraph");
		int as=0;
		series.add(1, as);

		series.add(2, 72);
		series.add(3, 74);
		series.add(4, 78);
		series.add(5, 82);
		// Add the series to your data set
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		// Generate the gFsraph
		JFreeChart chart = ChartFactory.createXYLineChart(
				"EFFICIENCY VS TIME REPORT WAREHOUSE 1", // Title
				"Month", // x-axis Label
				"Efficiency", // y-axis Label
				dataset, // Dataset
				PlotOrientation.VERTICAL, // Plot Orientation
				true, // Show Legend
				true, // Use tooltips
				false // Configure chart to generate URLs?
				);
		try {
			ChartUtilities.saveChartAsJPEG(new File("C:\\chart.jpg"), chart,
					500, 300);
		} catch (IOException e) {
			System.err.println("Problem occurred creating chart.");
		}
	}
}
