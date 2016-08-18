package com.vz.test;

import java.util.HashMap;

public class Analysis {
	// Initialize variables
	// Threshold for efficiency ALPHA
	final static double threshold = 75;
	// value in number of hours
	final static double freeT = 300.0;
	final static double occT = 400.0;
	final static double OtwT = 130.0;
	final static double persons = 5.0;
	final static double TotalWorkingHours = 9;

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

	public static boolean checkOccupiedHours(int occ) {
		if (occ > occT)
			return true;
		else
			return false;
	}

	public static boolean checkOTWHours(int otw) {
		if (otw > OtwT)
			return true;
		else
			return false;
	}

	// paisa fekooo tamash dekhooo
	public static void case1A() {
		// more technicians should be added
		double totalMen = persons;
		// these values are to be fetched from monthly report
		double reqServed = 0;
		double misses = 0;
		double menRequired = ((totalMen / reqServed) * misses);
		System.out.println("Total number of men required" + menRequired);
		// text box to modify number of men in db for admin
	}

	public static void case1B(int a, int b) {
		if (a > b) {
			System.out
					.println("More warehouses needed because  distant nodes in the TSP  ");

		}

	}

	// optimization of techies
	public static void optimise(int freeTime) {
		double freeTimePerDay = freeTime / 30;
		if (freeTimePerDay > 8) {
			int resultingAlpha = (int) (freeTimePerDay / (TotalWorkingHours - 1.0));
			System.out
					.println("Number of person to be sent to other workHouses are "
							+ resultingAlpha);
		}
	}

	public static void main(String[] args) {
		// Comparing efficeiency and finding out the value
		HashMap<String, String> miss = new HashMap<>();
		miss.put("W1", "98");
		// miss.put("W2", arg1);
		// miss.put("W3", arg1);
		// miss.put("W4", arg1);
		// miss.put("W5", arg1);

		// for warehouse W1
		if (checkThreshold(miss.get("W1"))) {
			// get values from sql query from table
			int f1 = 0;
			int o1 = 0;
			int otw1 = 0;

			if (checkOccupiedHours(o1)) {

				case1A();

			}
			if (checkOTWHours(otw1)) {

				case1B(otw1, o1);

			}

		} else {
			// loadBalccning
			int f1 = 0;
			optimise(f1);
		}
	}
}
