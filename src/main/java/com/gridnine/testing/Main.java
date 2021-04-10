package com.gridnine.testing;

import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		List<Flight> flight = FlightBuilder.createFlights();
		System.out.println("Исходный список перелётов:");
		for(Flight fl: flight) {
			System.out.println(fl);
		}
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.print("\n" + 
					"1 - вылет до текущего момента времени\n" +
	                "2 - имеются сегменты с датой прилёта раньше даты вылета\n" +
	                "3 - общее время, проведённое на земле превышает два часа\n" +
	                "4 - выход\n" + 
	                "Исключить перелёты где: ");
			
			String s = scanner.nextLine();
			if(s.equals("4")) {
				System.out.print("Завершение работы...");
				return;
			}
			String[] idS = s.split(" ");
			int num = idS.length;
			int[] idFilters = new int[num];
			boolean trig = false;
			for(int i = 0; i<num; i++) {
				idFilters[i] = Integer.parseInt(idS[i]);
				if(idFilters[i] == 4) trig = true;
			}
			
			Filters filters = new ExcludeFilters();
			List<Flight> flightFilter = filters.addExcludeFilters(flight, idFilters);
			for(Flight fl: flightFilter) {
				System.out.println(fl);
			}
			if(trig){
				System.out.print("Завершение работы...");
				return;
			}
		}
	}
	
}
