package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExcludeFilters implements Filters{

	@Override
	public List<Flight> addExcludeFilters(List<Flight> flight, int[] fId) {
		List<Flight> res = new ArrayList(flight);
		//System.out.print("Включены фильтры: ");
		for(int temp: fId) {
			switch(temp) {
				case 1:
					//System.out.print("вылет раньше текущей даты;  ");
					res = addUntilNowFilter(res);
					break;
				case 2:
					//System.out.print("вылет позже прилёта;  ");
					res = addArrivalBeforeDepartureFilter(res);
					break;
				case 3:
					//System.out.print("на земле более 2х часов;  ");
					res = addTimeOnLandFilter(res);
					break;
			}
		}
		System.out.println();
		return res;
	}

	private List<Flight> addUntilNowFilter(List<Flight> flights) {
		List<Flight> res = new ArrayList<Flight>();
		boolean trig;
		for(Flight item: flights) {
			trig = true;
			for(Segment segment: item.getSegments()) {
				if(segment.getDepartureDate().isBefore(LocalDateTime.now())) {
					trig = false;
					break;
				}
			}
			if(trig) {
				res.add(item);
			}
		}
		return res;
	}

	private List<Flight> addArrivalBeforeDepartureFilter(List<Flight> flights) {
		List<Flight> res = new ArrayList<Flight>();
		boolean trig;
		for(Flight item: flights) {
			trig = true;
			for(Segment segment: item.getSegments()) {
				if(segment.getArrivalDate().isBefore(segment.getDepartureDate())) {
					trig = false;
					break;
				}
			}
			if(trig) {
				res.add(item);
			}
		}
		return res;
	}

	private List<Flight> addTimeOnLandFilter(List<Flight> flights) {
		
		boolean trig;
		List<Flight> res = new ArrayList<Flight>();
		for(Flight item: flights) {
			if(item.getSegments().size()<2) {
				res.add(item);
				continue;
			}
			trig = true;
			long time = 0;
			LocalDateTime start = null;
			LocalDateTime end = null;
			for(Segment segment: item.getSegments()) {
				if(Objects.isNull(start)) {
					start = segment.getArrivalDate();
				}
				else {
					end = segment.getDepartureDate();
					time += Duration.between(start, end).toHours();
					start = segment.getArrivalDate();
				}
			}
			if(time > 2) trig = false;
			if(trig) res.add(item);
		}
		return res;
	}

}
