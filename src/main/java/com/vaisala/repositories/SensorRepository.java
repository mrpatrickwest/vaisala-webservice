package com.vaisala.repositories;

import com.vaisala.model.Sensor;
import com.vaisala.model.SensorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/** Repository class to be able to retrieve, add and delete sensors
 *
 */
@Repository
@Transactional
public final class SensorRepository implements ISensorRepository {

    public final Logger log = LoggerFactory.getLogger(SensorRepository.class);

    private static final SensorMapper SENSOR_MAPPER = new SensorMapper();

    private static final StringBuffer GET_SENSORS = new StringBuffer()
            .append("select * from sensors sensor order by id");

    private static final StringBuffer GET_SENSOR_BY_ID = new StringBuffer()
            .append("select * from sensors sensor where sensor.id = :id");

    private static final StringBuffer GET_SENSORS_BY_STATION = new StringBuffer()
            .append("select * from sensors sensor where sensor.station_id = :id");

    private static final StringBuffer ADD_SENSOR = new StringBuffer()
            .append("insert into sensors set name = :name, type = :type, station_id = :station");

    private static final StringBuffer DELETE_SENSOR = new StringBuffer()
            .append("delete from sensors where id = :id");

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

	/**
	 * @param id sensor id
	 * @return Sensor with given id
	 */
    @Override
	public Sensor getSensorById(int id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        Sensor sensor = null;
        try {
            sensor = this.jdbcTemplate.queryForObject(GET_SENSOR_BY_ID.toString(), params, SENSOR_MAPPER);
        } catch (final EmptyResultDataAccessException erdae) {
            log.error("Failed to retrieve the sensor " + erdae.getMessage());
            //NOOP
        }
        return sensor;
    }

	/**
	 * @return list of all sensors
	 */
    @Override
	public List<Sensor> getSensors() {
        final Map<String, Object> params = new HashMap<>();
        List<Sensor> sensors = null;
        try {
            sensors = this.jdbcTemplate.query(GET_SENSORS.toString(), params, SENSOR_MAPPER);
        } catch (final EmptyResultDataAccessException erdae) {
            log.error("Failed to retrieve the sensors " + erdae.getMessage());
            //NOOP
        }
        return sensors;
    }

    /**
     * @param stationId station identifier to look up
     * @return sensors for a given station
     */
    @Override
    public List<Sensor> getSensorsByStation(int stationId) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", stationId);
        List<Sensor> sensors = null;
        try {
            sensors = this.jdbcTemplate.query(GET_SENSORS_BY_STATION.toString(), params, SENSOR_MAPPER);
        } catch (final EmptyResultDataAccessException erdae) {
            log.error("Failed to retrieve the sensors " + erdae.getMessage());
            //NOOP
        }
        return sensors;
    }

    /**
     * @param sensor new sensor
     * @return true on success, false otherwise
     */
    @Override
    public boolean addSensor(Sensor sensor) {
        final Map<String, Object> params = new HashMap<>();
        params.put("name", sensor.getName());
        params.put("type", sensor.getType());
        params.put("station", sensor.getStationId());
        try {
            this.jdbcTemplate.update( ADD_SENSOR.toString(), params );
            return true;
        } catch(DataAccessException e) {
            log.error("Failed to add sensor " + e.getMessage());
        }
        return false;
    }

    /**
     * @param id sensor id to delete
     * @return true on successful deletion, false otherwise
     */
    @Override
    public boolean deleteSensor(int id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        try {
            this.jdbcTemplate.update( DELETE_SENSOR.toString(), params );
            return true;
        } catch(DataAccessException e) {
            log.error("Failed to delete sensor " + e.getMessage());
        }
        return false;
    }
}

