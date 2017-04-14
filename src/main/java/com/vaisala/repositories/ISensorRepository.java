package com.vaisala.repositories;

import com.vaisala.model.Sensor;

import java.util.List;

public interface ISensorRepository {

	/**
	 * @param id
	 * @return Sensor with given id
	 */
	public Sensor getSensorById(int id);

	/**
	 * @return list of all sensors
	 */
	public List<Sensor> getSensors();

	/**
	 * @param id station id
	 * @return list of all sensors by given station id
	 */
	public List<Sensor> getSensorsByStation(int id);

	/**
	 * @param sensor new sensor
	 * @return true on success, false otherwise
	 */
	public boolean addSensor(Sensor sensor);

	/**
	 * @param id sensor id
	 * @return true on successful deletion, false otherwise
	 */
	public boolean deleteSensor(int id);
}

