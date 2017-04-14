package com.vaisala.model;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Observation Mapper
 *
 * @author pwest
 *
 */
public class ObservationMapper extends BeanPropertyRowMapper<Observation>
{

	@Override
	public Observation mapRow(final ResultSet rs, final int rowNumber) throws SQLException {
		final Observation observation = new Observation(rs.getInt("observation.id"),
				rs.getDate("observation.observed_timestamp"),
				rs.getDate("observation.received_timestamp"),
				rs.getFloat("observation.value"),
				rs.getInt("observation.quality_code"),
				rs.getInt("observation.sensor_id"));

		return observation;
	}
}

