package com.vaisala.model;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Sensor Mapper
 *
 * @author pwest
 *
 */
public class SensorMapper extends BeanPropertyRowMapper<Sensor>
{

	@Override
	public Sensor mapRow(final ResultSet rs, final int rowNumber) throws SQLException {
		final Sensor sensor = new Sensor(rs.getInt("sensor.id"),
				rs.getString("sensor.name"),
				rs.getString("sensor.type"),
				rs.getInt("sensor.station_id"));

		return sensor;
	}
}

