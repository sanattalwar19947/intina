package servlets;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.XYDataset;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class XYChart1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ResultSet rs;
	PreparedStatement pst;
    public XYChart1() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		XYSeries series = new XYSeries("XYGraph");
		ToConnect cn=new ToConnect();
		Connection con=cn.getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement
			                 ("select eff from five_month where month like 'jan'");
		rs=ps.executeQuery();
		while(rs.next())
		{
		series.add(1,rs.getInt("EFF"));
		}
		ps =con.prepareStatement
                ("select eff from five_month where month like 'feb'");
		rs=ps.executeQuery();
		while(rs.next())
		{
			series.add(2,rs.getInt("EFF"));
		}
		ps =con.prepareStatement
                ("select eff from five_month where month like 'mar'");
		rs=ps.executeQuery();
		while(rs.next())
		{
			series.add(3,rs.getInt("EFF"));
		}
		ps =con.prepareStatement
                ("select eff from five_month where month like 'apr'");
		rs=ps.executeQuery();
		while(rs.next())
		{
			series.add(4,rs.getInt("EFF"));
		}
		ps =con.prepareStatement
                ("select eff from five_month where month like 'may'");
		rs=ps.executeQuery();
		while(rs.next())
		{
			series.add(5,rs.getInt("EFF"));
		}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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

