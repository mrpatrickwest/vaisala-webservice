package com.vaisala.repositories;

import com.vaisala.model.Observation;

import java.util.List;

public interface IObservationRepository {

	/**
	 * @param id
	 * @return Observation with given id
	 */
	public Observation getObservationById(int id);

	/**
	 * @return list of all observations
	 */
	public List<Observation> getObservations();

	/**
	 * @param id station id
	 * @return list of all observations
	 */
	public List<Observation> getObservationsByStation(int id);

	/**
	 * @param id sensor id
	 * @return list of all observations
	 */
	public List<Observation> getObservationsBySensor(int id);

	/**
	 * @param observation new observation
	 * @return true on success, false otherwise
	 */
	public boolean addObservation(Observation observation);

	/**
	 * @param id observation id
	 * @return true on successful deletion, false otherwise
	 */
	public boolean deleteObservation(int id);
}

