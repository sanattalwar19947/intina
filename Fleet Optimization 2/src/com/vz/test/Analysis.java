package com.vz.test;

import java.io.PrintWriter;
import java.util.HashMap;

public class Analysis {
	// Initialize variables
	// Threshold for efficiency ALPHA
	final static double threshold = 75;
	// value in number of hours
	final static double freeT = 300.0;
	final static double occT = 700.0;
	final static double OtwT = 400.0;
	
	final static double TotalWorkingHours = 9.0;

	public static boolean checkThreshold(String a) {
		if (Double.parseDouble(a) < threshold)
			return true;
		else
			return false;

	}

	public static boolean checkFreeHours(int free) {
		if (free > freeT) {
			return true;
		} else
			return false;

	}

//	public static boolean checkOccupiedHours(double o1) {
//		if (o1 > occT)
//			return true;
//		else
//			return false;
//	}

	public static boolean checkOTWHours(double otw1) {
		if (otw1 > OtwT)
			return true;
		else
			return false;
	}

	// paisa fekooo tamash dekhooo
	

	public static void case1B(double otw1, double o1,PrintWriter out,String name) {
		if (otw1 > o1) {
			out.println("<h1>Warehouse "+name+" overloaded!! More warehouses needed because techies are busy in travelling to long distant nodes</h1>");

		}

	}

	// optimization of techies
	public static void optimise(double freeTime,PrintWriter out,String name) {
		double freeTimePerDay = freeTime / 30;
		if (freeTimePerDay > 8) {
			double resultingAlpha = (double) (freeTimePerDay / (TotalWorkingHours - 1.0));
			out.println("<h1>Number of personnels to be sent to other warehouses from warehouse "+name +" are "
							+ Math.floor(resultingAlpha)+"</h1>");
		}
		
	}

	
}