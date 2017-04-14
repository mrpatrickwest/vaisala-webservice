package com.vaisala.model;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Station Mapper
 *
 * @author pwest
 *
 */
public class StationMapper extends BeanPropertyRowMapper<Station>
{

	@Override
	public Station mapRow(final ResultSet rs, final int rowNumber) throws SQLException {
		final Station station = new Station(rs.getInt("station.id"),
				rs.getString("station.name"),
				rs.getString("station.location"));

		return station;
	}
}

