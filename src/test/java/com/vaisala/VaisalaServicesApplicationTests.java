package com.vaisala;

import com.vaisala.model.Observation;
import com.vaisala.model.Sensor;
import com.vaisala.model.Station;
import com.vaisala.repositories.IObservationRepository;
import com.vaisala.repositories.ISensorRepository;
import com.vaisala.repositories.IStationRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(VaisalaServicesApplication.class)
public class VaisalaServicesApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(VaisalaServicesApplicationTests.class);

	@Autowired
	private IStationRepository stationRepository;
	@Autowired
	private ISensorRepository sensorRepository;
	@Autowired
	private IObservationRepository observationRepository;

	@Test
	public void getFirstObservation() {
		Assert.assertNotNull(this.observationRepository);
		Observation observation = this.observationRepository.getObservationById(1);
		Assert.assertNotNull(observation);
		Assert.assertEquals(observation.getId(), 1);
		Assert.assertEquals(observation.getValue(), 0.0, 0);
		Assert.assertEquals(observation.getQualityCode(), 1);
	}

    @Test
    public void getFirstSensor() {
        Assert.assertNotNull(this.sensorRepository);
        Sensor sensor = this.sensorRepository.getSensorById(1);
        Assert.assertNotNull(sensor);
        Assert.assertEquals(sensor.getId(), 1);
        Assert.assertEquals(sensor.getName(), "PW20");
        Assert.assertEquals(sensor.getType(), "PRECIP_AMOUNT");
        Assert.assertEquals(sensor.getStationId(), 1);
    }

    @Test
    public void getFirstStation() {
        Assert.assertNotNull(this.stationRepository);
        Station station = this.stationRepository.getStationById(1);
        Assert.assertNotNull(station);
        Assert.assertEquals(station.getId(), 1);
        Assert.assertEquals(station.getName(), "BOU-MASTER");
        Assert.assertEquals(station.getLocation(), "123 Local Street, CO – 80027");
    }
}
