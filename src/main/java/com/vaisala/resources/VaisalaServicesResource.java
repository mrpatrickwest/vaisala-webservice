package com.vaisala.resources;

import com.vaisala.model.Observation;
import com.vaisala.model.Sensor;
import com.vaisala.model.Station;
import com.vaisala.repositories.IObservationRepository;
import com.vaisala.repositories.ISensorRepository;
import com.vaisala.repositories.IStationRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/** All web services are implemented here to retrieve, add and delete stations, sensors and observations
 *
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

    /** retrive the list of all observations
     *
     * @return list of all observations as json object
     */
    @CrossOrigin(origins = "*", methods = { RequestMethod.GET })
    @RequestMapping( value = "/observations", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<Observation> getObservations() {
        return this.observationRepository.getObservations();
    }

    /** retrieves information about a single observation given its id
     *
     * @param id observation id to retrieve
     * @return json representation of the observation
     */
    @CrossOrigin(origins = "*", methods = { RequestMethod.GET })
    @RequestMapping( value = "/observation/{id:.*}", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Observation getObservation(@PathVariable(value="id") final int id) {
        return this.observationRepository.getObservationById(id);
    }

    /** retrieves list of observations for the specified sensor
     *
     * @param sensorId id of the sensor that observations are from
     * @return json representation of the list of observations
     */
    @CrossOrigin(origins = "*", methods = { RequestMethod.GET })
    @RequestMapping( value = "/observations/sensor/{id:.*}", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<Observation> getObservationsFromSensor(@PathVariable(value="id") final int sensorId) {
        return this.observationRepository.getObservationsBySensor(sensorId);
    }

    /** retrieve all observations for all sensors at a given station
     *
     * @param stationId id of the station where the observations are made of sensors at its location
     * @return json representation of the list of observations retrieved
     */
    @CrossOrigin(origins = "*", methods = { RequestMethod.GET })
    @RequestMapping( value = "/observations/station/{id:.*}", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<Observation> getObservationsFromStation(@PathVariable(value="id") final int stationId) {
        return this.observationRepository.getObservationsByStation(stationId);
    }

    /** add a new observation
     *
     * @param observation the observation to add
     * @param response the http response object to handle the response
     * @return json object where success value is either true on success, or false otherwise
     */
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

    /** delete the specified observation
     *
     * @param id id of the observation to delete
     * @return json object where success value is true on successful deletion, false otherwise
     */
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

    /** retrieve the list of all sensors
     *
     * @return json representation of the list of all sensors
     */
    @CrossOrigin(origins = "*", methods = { RequestMethod.GET })
    @RequestMapping( value = "/sensors", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<Sensor> getSensors() {
        return this.sensorRepository.getSensors();
    }

    /** retrieve information about the specified sensor
     *
     * @param id id of the sensor to retrieve
     * @return json representation of the sensor
     */
    @CrossOrigin(origins = "*", methods = { RequestMethod.GET })
    @RequestMapping( value = "/sensor/{id:.*}", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Sensor getSensor(@PathVariable(value="id") final int id) {
        return this.sensorRepository.getSensorById(id);
    }

    /** retrieve the list of sensors at a given station
     *
     * @param stationId id of the station where the sensors are located
     * @return json representaiton of the list of sensors returned
     */
    @CrossOrigin(origins = "*", methods = { RequestMethod.GET })
    @RequestMapping( value = "/sensors/station/{id:.*}", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<Sensor> getSensorsAtStation(@PathVariable(value="id") final int stationId) {
        return this.sensorRepository.getSensorsByStation(stationId);
    }

    /** add a new sensor
     *
     * @param sensor sennsor to be added
     * @param response http response object that handles the response
     * @return json object where success value is true on successful addition, false otherwise
     */
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

    /** delete the given sensor
     *
     * @param id id of the sensor to delete
     * @return json object where success value is true on successful deletion, false otherwise
     */
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

    /** retrieve the list of all stations
     *
     * @return json representation of all of the stations
     */
    @CrossOrigin(origins = "*", methods = { RequestMethod.GET })
    @RequestMapping( value = "/stations", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<Station> getStations() {
        return this.stationRepository.getStations();
    }

    /** retrieve information about the given station
     *
     * @param id id of the station to retrieve
     * @return json representation of the station
     */
    @CrossOrigin(origins = "*", methods = { RequestMethod.GET })
    @RequestMapping( value = "/station/{id:.*}", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Station getStation(@PathVariable(value="id") final int id) {
        return this.stationRepository.getStationById(id);
    }

    /** add a new station
     *
     * @param station the new station to add
     * @param response http resoponse object to handle the response
     * @return json object where the success value is true on successful addition, false otherwise
     */
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

    /** delete the specified station
     *
     * @param id id of the station to delete
     * @return json object where the success value is true on successful deletion, false otherwise
     */
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

