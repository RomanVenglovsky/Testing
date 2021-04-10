package com.gridnine.testing;

import java.util.List;

public interface Filters {

	List<Flight> addExcludeFilters(List<Flight> flight, int[] fId);
	/*List<Flight> addUntilNowFilter(List<Flight> flight);
	List<Flight> addArrivalBeforeDepartureFilter(List<Flight> flight);
	List<Flight> addTimeOnLandFilter(List<Flight> flight);*/
}
