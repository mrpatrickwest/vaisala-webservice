package com.vaisala.repositories;

import com.vaisala.model.Station;

import java.util.List;

public interface IStationRepository {

	/**
	 * @param id
	 * @return Station with given id
	 */
	public Station getStationById(int id);

	/**
	 * @return list of all stations
	 */
	public List<Station> getStations();

	/**
	 * @param station new station
	 * @return true on success, false otherwise
	 */
	public boolean addStation(Station station);

	/**
	 * @param id station id
	 * @return true on successful deletion, false otherwise
	 */
	public boolean deleteStation(int id);
}

