import axios from "axios";
const FLIGHT_BASE_REST_API_URI = "http://localhost:8080/flight";

class FlightService {
  getAllFlights() {
    return axios.get(FLIGHT_BASE_REST_API_URI + "/all");
  }

  deleteFlight(flightId) {
    return axios.delete(FLIGHT_BASE_REST_API_URI + "/remove", {
      params: { fid: flightId },
    });
  }

  getFlightById(flightId) {
    const fid = parseInt(flightId);
    return axios.get(FLIGHT_BASE_REST_API_URI + "/get", {
      params: { fid: fid },
    });
  }

  addFlight(flight) {
    return axios.post(FLIGHT_BASE_REST_API_URI + "/new", flight);
  }

  updateFlight(flight) {
    return axios.put(FLIGHT_BASE_REST_API_URI + "/update", flight);
  }
}
export default new FlightService();
