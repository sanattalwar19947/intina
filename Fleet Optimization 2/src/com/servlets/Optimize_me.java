package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vz.test.Analysis;

/**
 * Servlet implementation class Testing
 */
public class Optimize_me extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Optimize_me() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in here");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	Connection con;
	PreparedStatement pst;
	double requests;
	double served;
	double misses;
	double efficiency;
	String warehouse_no;
	double occup;
	double fr;
	double ot;
	HashMap<String, String> miss = new HashMap<>();
	HashMap<String, Double> req = new HashMap<>();
	HashMap<String, Double> serve = new HashMap<>();
	HashMap<String, Double> missed = new HashMap<>();
	HashMap<String, Double> occupied = new HashMap<>();
	HashMap<String, Double> free = new HashMap<>();
	HashMap<String, Double> otw = new HashMap<>();
	static final double freeThreshold=8.0;
	final static double persons = 5.0;
	PrintWriter out;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConnectionGetter cg=new ConnectionGetter();
		con=cg.getConnection();
		Analysis an=new Analysis();
		//		try{
		 out = response.getWriter();
			// Comparing efficeiency and finding out the value
			
		try {
			pst=con.prepareStatement("select warehouse_no,avg_req,avg_served,eff from monthlyreport");
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				requests=rs.getDouble(2);
				served=rs.getDouble(3);
				warehouse_no=rs.getString(1);
				efficiency=rs.getDouble(4);
				System.out.println(efficiency);
				miss.put(warehouse_no, Double.toString(efficiency));
				req.put(warehouse_no, requests);
				serve.put(warehouse_no, served);
				missed.put(warehouse_no, (requests-served));
				// miss.put("W2", arg1);
				// miss.put("W3", arg1);
				// miss.put("W4", arg1);
				// miss.put("W5", arg1);

				
			}
			pst=con.prepareStatement("select warehouse_no,occupied,free,otw from technician_avg_status");
			ResultSet rs1=pst.executeQuery();
			while(rs1.next())
			{
				
						
				occupied.put(rs1.getString(1),rs1.getDouble(2));
				free.put(rs1.getString(1), rs1.getDouble(3));
				otw.put(rs1.getString(1), rs1.getDouble(4));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		for (String name: missed.keySet()){
//
//            String key =name.toString();
//            String value = missed.get(name).toString();  
//            out.println(key + " " + value);  
//} 

		for (String name: free.keySet()){

            String key =name.toString();
            String value = free.get(name).toString(); 
            if((free.get(name))/30.0<freeThreshold)
            {
    			double o1 =occupied.get(name) ;
    			double otw1 =otw.get(name) ;
    			
//    			if (Analysis.checkOccupiedHours(o1)) {

    				case1A(req.get(name),missed.get(name),name);

    			//}
    			if (Analysis.checkOTWHours(otw1)) {

    				Analysis.case1B(otw1, o1,out,name);

    			}
            }
            else if((free.get(name))/30.0>freeThreshold)
            {
            	Analysis.optimise(free.get(name),out,name);
            }
            //out.println(key + " " + value);  
} 
		
		
	}
	
	public void case1A(double reqServed,double misses,String name) {
		// more technicians should be added
		double totalMen = persons;
		// these values are to be fetched from monthly report
		double menRequired = ((totalMen / reqServed) * misses);
		out.println("<h1>Total number of men recommended =" + Math.ceil(menRequired) +" at warehouse "+name+"</h1>");
		
		// text box to modify number of men in db for admin
	}

}
