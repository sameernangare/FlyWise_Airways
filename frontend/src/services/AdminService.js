import axios from "axios";

const ADMIN_BASE_REST_API_URI = "http://localhost:8080/admin";

class AdminService {
  getAllUsers() {
    return axios.get(ADMIN_BASE_REST_API_URI + "/getusers");
  }

  getAllFeedback() {
    return axios.get(ADMIN_BASE_REST_API_URI + "/feedback");
  }

  getPassengersByFlightId(flightId) {
    const fid = parseInt(flightId);
    return axios.get(ADMIN_BASE_REST_API_URI + "/passengers", {
      params: { fid: fid },
    });
  }

  getBookingsByFlightId(flightId) {
    const fid = parseInt(flightId);
    return axios.get(ADMIN_BASE_REST_API_URI + "/getbookings", {
      params: { fid: fid },
    });
  }
}
export default new AdminService();
