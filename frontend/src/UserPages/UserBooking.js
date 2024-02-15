import React, { useState } from "react";
import axios from "axios";
import { useNavigate, useLocation } from "react-router";
import { toast } from "react-toastify";
import "../styles/UserBookingStyles.css";
import UserSidebar from "../components/UserSidebar";

function UserBooking() {
  const [passengers, setPassengers] = useState([]);
  const bookingId = sessionStorage.getItem("bid");
  const navigate = useNavigate();
  const numberOfSeatsToBook = parseInt(sessionStorage.getItem("numSeats"));
  const location = useLocation();
  // const selectedFlight = location.state.selectedFlight;
  const selectedFlight = JSON.parse(sessionStorage.getItem("selectedFlight"));
  // const cls = location.state.cls;
  const cls = sessionStorage.getItem("cls");
  const BASE_URL = "http://localhost:8080";

  const handlePassengerChange = (event, index) => {
    const { name, value } = event.target;
    const newPassengers = [...passengers];
    newPassengers[index] = {
      ...newPassengers[index],
      [name]: value,
    };
    setPassengers(newPassengers);
  };

  const handleSubmit = async (event) => {
    const token = sessionStorage.getItem("jwtToken");
    event.preventDefault();
    try {
      const response = await axios.post(
        BASE_URL + `/book/passengers?bookingId=${bookingId}`,
        passengers
      );
      navigate(`/confirmBooking`);
    } catch (error) {
      toast.error(`${error.response.data}`);
    }
  };

  const renderPassengerForm = () => {
    const forms = [];
    for (let i = 0; i < numberOfSeatsToBook; i++) {
      forms.push(
        <div className="mx-auto col-md-6" key={i}>
          <h1
            className='<div class="card text-start">
           <img class="card-img-top" src="holder.js/100px180/" alt="Title">
           <div class="card-body">
             <h4 class="card-title">Title</h4>
             <p class="card-text">Body</p>
           </div>
         </div>'
          ></h1>
          <table className="table table-bordered col-md-6">
            <thead>
              <tr>
                <th colSpan="2">Passenger {i + 1} Details</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>Name:</td>
                <td>
                  <input
                    type="text"
                    required
                    className="form-control"
                    name="passengerName"
                    onChange={(event) => handlePassengerChange(event, i)}
                  />
                </td>
              </tr>
              <tr>
                <td>Gender:</td>
                <td>
                  <select
                    className="form-select"
                    required
                    name="gender"
                    onChange={(event) => handlePassengerChange(event, i)}
                  >
                    <option value="">Select Gender</option>
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Other">Other</option>
                  </select>
                </td>
              </tr>
              <tr>
                <td>Age:</td>
                <td>
                  <input
                    type="number"
                    required
                    className="form-control"
                    name="age"
                    onChange={(event) => handlePassengerChange(event, i)}
                  />
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      );
    }
    return forms;
  };

  return (
    <UserSidebar>
      <div className="middleContent">
        <div className="d-flex align-items-start">
          <table className="mx-auto col-md-3">
            <thead>
              <tr>
                <th colSpan="2">Your Selected Flight</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>Source:</td>
                <td>{selectedFlight?.source}</td>
              </tr>
              <tr>
                <td>Destination:</td>
                <td>{selectedFlight?.destination}</td>
              </tr>
              <tr>
                <td>Date:</td>
                <td>{selectedFlight?.travelDate}</td>
              </tr>
              <tr>
                <td>Departure:</td>
                <td>{selectedFlight?.departureTime}</td>
              </tr>
              <tr>
                <td>Arrival:</td>
                <td>{selectedFlight?.arrivalTime}</td>
              </tr>
              <tr>
                <td>Class:</td>
                <td>{cls}</td>
              </tr>
            </tbody>
          </table>

          <div className="mx-auto col-md-9">{renderPassengerForm()}</div>
        </div>

        <div className="text-center">
          <button className="btn btn-primary mt-3" onClick={handleSubmit}>
            Submit
          </button>
        </div>
      </div>
    </UserSidebar>
  );
}

export default UserBooking;
