package com.vaisala.resources;

import com.vaisala.model.Observation;
import com.vaisala.model.Sensor;
import com.vaisala.model.Station;
import com.vaisala.repositories.IObservationRepository;
import com.vaisala.repositories.ISensorRepository;
import com.vaisala.repositories.IStationRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by westp on 2/19/17.
 */
@RestController
public class VaisalaServicesResource
{
    private static final Logger log = LoggerFactory.getLogger(VaisalaServicesResource.class);

    @Autowired
    IObservationRepository observationRepository;
    @Autowired
    ISensorRepository sensorRepository;
    @Autowired
    IStationRepository stationRepository;

    @CrossOrigin(origins = "*", methods = { RequestMethod.GET })
    @RequestMapping( value = "/observations", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<Observation> getInstruments() {
        return this.observationRepository.getObservations();
    }

    @CrossOrigin(origins = "*", methods = { RequestMethod.GET })
    @RequestMapping( value = "/observation/{id:.*}", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Observation getObservation(@PathVariable(value="id") final int id) {
        return this.observationRepository.getObservationById(id);
    }

    @CrossOrigin(origins = "*", methods = { RequestMethod.GET })
    @RequestMapping( value = "/observations/sensor/{id:.*}", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<Observation> getObservationsFromSensor(@PathVariable(value="id") final int sensorId) {
        return this.observationRepository.getObservationsBySensor(sensorId);
    }

    @CrossOrigin(origins = "*", methods = { RequestMethod.GET })
    @RequestMapping( value = "/observations/station/{id:.*}", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<Observation> getObservationsFromStation(@PathVariable(value="id") final int stationId) {
        return this.observationRepository.getObservationsByStation(stationId);
    }

    @CrossOrigin(origins = "*", methods = { RequestMethod.PUT })
    @RequestMapping(value = "/observation", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public String addObservation(@RequestBody final Observation observation, HttpServletResponse response) {
      response.setStatus(200);
      boolean success = this.observationRepository.addObservation(observation);
      String responseStr = "{}";
      try {
        JSONObject j = new JSONObject();
        j.put("success", success);
        responseStr = j.toString();
      } catch(Exception e) {
        // NOOP
      }
      return responseStr;
    }

    @CrossOrigin(origins = "*", methods = { RequestMethod.DELETE })
    @RequestMapping( value = "/observation/{id:.*}", produces = APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public String deleteObservation(@PathVariable(value="id") final int id) {
      boolean success = this.observationRepository.deleteObservation(id);
      String response = "{}";
      try {
        JSONObject j = new JSONObject();
        j.put("success", success);
        response = j.toString();
      } catch(Exception e) {
          // NOOP
      }
      return response;
    }

    @CrossOrigin(origins = "*", methods = { RequestMethod.GET })
    @RequestMapping( value = "/sensors", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<Sensor> getSensors() {
        return this.sensorRepository.getSensors();
    }

    @CrossOrigin(origins = "*", methods = { RequestMethod.GET })
    @RequestMapping( value = "/sensor/{id:.*}", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Sensor getSensor(@PathVariable(value="id") final int id) {
        return this.sensorRepository.getSensorById(id);
    }

    @CrossOrigin(origins = "*", methods = { RequestMethod.GET })
    @RequestMapping( value = "/sensors/station/{id:.*}", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<Sensor> getSensorsAtStation(@PathVariable(value="id") final int stationId) {
        return this.sensorRepository.getSensorsByStation(stationId);
    }

    @CrossOrigin(origins = "*", methods = { RequestMethod.PUT })
    @RequestMapping(value = "/sensor", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public String addSensor(@RequestBody final Sensor sensor, HttpServletResponse response) {
        response.setStatus(200);
        boolean success = this.sensorRepository.addSensor(sensor);
        String responseStr = "{}";
        try {
            JSONObject j = new JSONObject();
            j.put("success", success);
            responseStr = j.toString();
        } catch(Exception e) {
            // NOOP
        }
        return responseStr;
    }

    @CrossOrigin(origins = "*", methods = { RequestMethod.DELETE })
    @RequestMapping(value = "/sensor/{id:.*}", produces = APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public String deleteSensor(@PathVariable(value="id") final int id) {
        boolean success = this.sensorRepository.deleteSensor(id);
        String response = "{}";
        try {
            JSONObject j = new JSONObject();
            j.put("success", success);
            response = j.toString();
        } catch(Exception e) {
            // NOOP
        }
        return response;
    }

    @CrossOrigin(origins = "*", methods = { RequestMethod.GET })
    @RequestMapping( value = "/stations", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<Station> getStations() {
        return this.stationRepository.getStations();
    }

    @CrossOrigin(origins = "*", methods = { RequestMethod.GET })
    @RequestMapping( value = "/station/{id:.*}", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Station getStation(@PathVariable(value="id") final int id) {
        return this.stationRepository.getStationById(id);
    }

    @CrossOrigin(origins = "*", methods = { RequestMethod.PUT })
    @RequestMapping(value = "/station", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public String addStation(@RequestBody final Station station, HttpServletResponse response) {
        response.setStatus(200);
        boolean success = this.stationRepository.addStation(station);
        String responseStr = "{}";
        try {
            JSONObject j = new JSONObject();
            j.put("success", success);
            responseStr = j.toString();
        } catch(Exception e) {
            // NOOP
        }
        return responseStr;
    }

    @CrossOrigin(origins = "*", methods = { RequestMethod.DELETE })
    @RequestMapping(value = "/station/{id:.*}", produces = APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public String deleteStation(@PathVariable(value="id") final int id) {
        boolean success = this.stationRepository.deleteStation(id);
        String response = "{}";
        try {
            JSONObject j = new JSONObject();
            j.put("success", success);
            response = j.toString();
        } catch(Exception e) {
            // NOOP
        }
        return response;
    }
}

