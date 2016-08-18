package servlets;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class XYChart1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public XYChart1() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		XYSeries series = new XYSeries("XYGraph");
		
		int as=0;
		series.add(1, a1);
		series.add(2, a2);
		series.add(3, a3);
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
