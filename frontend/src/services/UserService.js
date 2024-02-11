import axios from "axios";
const USER_BASE_REST_API_URI = "http://localhost:8080";

class UserService {
  getAllCities() {
    return axios.get(USER_BASE_REST_API_URI + "/cities");
  }

  getUserFeedbackById(uid) {
    const fid = parseInt(uid);
    return axios.get(USER_BASE_REST_API_URI + "/user/getfeedback", {
      params: { uid: fid },
    });
  }

  getUserBookings(uid) {
    return axios.get(USER_BASE_REST_API_URI + "/user/bookings", {
      params: { uid: uid },
    });
  }

  pay(bid) {
    return axios.post(USER_BASE_REST_API_URI + `/book/pay?bid=${bid}`);
  }
}
export default new UserService();
