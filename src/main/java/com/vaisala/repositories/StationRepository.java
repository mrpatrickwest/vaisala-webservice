package com.vaisala.repositories;

import com.vaisala.model.Station;
import com.vaisala.model.StationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
@Transactional
public final class StationRepository implements IStationRepository {

    public final Logger log = LoggerFactory.getLogger(StationRepository.class);

    private static final StationMapper STATION_MAPPER = new StationMapper();

    private static final StringBuffer GET_STATIONS = new StringBuffer()
            .append("select * from stations station order by id");

    private static final StringBuffer GET_STATION_BY_ID = new StringBuffer()
            .append("select * from stations station where station.id = :id");

    private static final StringBuffer ADD_STATION = new StringBuffer()
            .append("insert into stations set name = :name, location = :location");

    private static final StringBuffer DELETE_STATION = new StringBuffer()
            .append("delete from stations where id = :id");

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

	/**
	 * @param id station id
	 * @return Station with given id
	 */
    @Override
	public Station getStationById(int id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        Station station = null;
        try {
            station = this.jdbcTemplate.queryForObject(GET_STATION_BY_ID.toString(), params, STATION_MAPPER);
        } catch (final EmptyResultDataAccessException erdae) {
            log.error("Failed to retrieve the station " + erdae.getMessage());
            //NOOP
        }
        return station;
    }

	/**
	 * @return list of all stations
	 */
    @Override
	public List<Station> getStations() {
        final Map<String, Object> params = new HashMap<>();
        List<Station> stations = null;
        try {
            stations = this.jdbcTemplate.query(GET_STATIONS.toString(), params, STATION_MAPPER);
        } catch (final EmptyResultDataAccessException erdae) {
            log.error("Failed to retrieve the stations " + erdae.getMessage());
            //NOOP
        }
        return stations;
    }

    /**
     * @param station new station
     * @return true on success, false otherwise
     */
    @Override
    public boolean addStation(Station station) {
        final Map<String, Object> params = new HashMap<>();
        params.put("name", station.getName());
        params.put("location", station.getLocation());
        try {
            this.jdbcTemplate.update( ADD_STATION.toString(), params );
            return true;
        } catch(DataAccessException e) {
            log.error("Failed to add station " + e.getMessage());
        }
        return false;
    }

    /**
     * @param id station id
     * @return true on successful deletion, false otherwise
     */
    @Override
    public boolean deleteStation(int id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        try {
            this.jdbcTemplate.update( DELETE_STATION.toString(), params );
            return true;
        } catch(DataAccessException e) {
            log.error("Failed to delete stations " + e.getMessage());
        }
        return false;
    }
}

