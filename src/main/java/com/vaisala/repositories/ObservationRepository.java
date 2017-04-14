package com.vaisala.repositories;

import com.vaisala.model.Observation;
import com.vaisala.model.ObservationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/** Repository class that implements methods to retrieve, add and delete observations
 *
 */
@Repository
@Transactional
public final class ObservationRepository implements IObservationRepository {

    public final Logger log = LoggerFactory.getLogger(ObservationRepository.class);

    private static final ObservationMapper OBSERVATION_MAPPER = new ObservationMapper();

    private static final StringBuffer GET_OBSERVATIONS = new StringBuffer()
            .append("select * from observations observation order by id");

    private static final StringBuffer GET_OBSERVATION_BY_ID = new StringBuffer()
            .append("select * from observations observation where observation.id = :id");

    private static final StringBuffer GET_OBSERVATIONS_BY_SENSOR = new StringBuffer()
            .append("select * from observations observation where observation.sensor_id = :id");

    private static final StringBuffer GET_OBSERVATIONS_BY_STATION = new StringBuffer()
            .append("select observation.id,observation.observed_timestamp,observation.received_timestamp,observation.value,observation.quality_code,observation.sensor_id")
            .append(" from observations observation, sensors sensor where observation.sensor_id = sensor.id AND sensor.station_id = :id");

    private static final StringBuffer ADD_OBSERVATION = new StringBuffer()
            .append("insert into observations set observed_timestamp = :observed, received_timestamp = :received, value = :value, quality_code = :quality, sensor_id = :sensor");

    private static final StringBuffer DELETE_OBSERVATION = new StringBuffer()
            .append("delete from observations where id = :id");

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

	/**
	 * @param id database primary key of observation
	 * @return Observation with given id
	 */
    @Override
	public Observation getObservationById(int id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        Observation observation = null;
        try {
            observation = this.jdbcTemplate.queryForObject(GET_OBSERVATION_BY_ID.toString(), params, OBSERVATION_MAPPER);
        } catch (final EmptyResultDataAccessException erdae) {
            log.error("Failed to retrieve the observation " + erdae.getMessage());
            //NOOP
        }
        return observation;
    }

	/**
	 * @return list of all observations
	 */
    @Override
	public List<Observation> getObservations() {
        final Map<String, Object> params = new HashMap<>();
        return getObservations(GET_OBSERVATIONS.toString(), params);
    }

    /**
     * @param id station id
     * @return list of all observations for a given station
     */
    @Override
    public List<Observation> getObservationsByStation(int id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return getObservations(GET_OBSERVATIONS_BY_STATION.toString(), params);
    }

    /**
     * @param id sensor id
     * @return list of all observations for a given sensor
     */
    @Override
    public List<Observation> getObservationsBySensor(int id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return getObservations(GET_OBSERVATIONS_BY_SENSOR.toString(), params);
    }

    // helper function to retrieve list of observations given a sql query to use and params for the query
    private List<Observation>getObservations(final String query, Map<String, Object> params) {
        List<Observation> observations = null;
        try {
            observations = this.jdbcTemplate.query(query, params, OBSERVATION_MAPPER);
        } catch (final EmptyResultDataAccessException erdae) {
            log.error("Failed to retrieve the observations using query " + query + ": " + erdae.getMessage());
            //NOOP
        }
        return observations;
    }

    /**
     * @param observation new observation
     * @return true on success, false otherwise
     */
    @Override
    public boolean addObservation(Observation observation) {
        final Map<String, Object> params = new HashMap<>();
        params.put("observed", observation.getObservedTimestamp());
        params.put("received", observation.getReceivedTimestamp());
        params.put("value", observation.getValue());
        params.put("quality", observation.getQualityCode());
        params.put("sensor", observation.getSensorId());
        try {
            this.jdbcTemplate.update( ADD_OBSERVATION.toString(), params );
            return true;
        } catch(DataAccessException e) {
            log.error("Failed to add observation " + e.getMessage());
        }
        return false;
    }

    /**
     * @param id observation id to delete
     * @return true on successful deletion, false otherwise
     */
    @Override
    public boolean deleteObservation(int id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        try {
            this.jdbcTemplate.update( DELETE_OBSERVATION.toString(), params );
            return true;
        } catch(DataAccessException e) {
            log.error("Failed to delete observation " + e.getMessage());
        }
        return false;
    }
}

