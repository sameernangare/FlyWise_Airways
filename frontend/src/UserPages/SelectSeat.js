import React from "react";
import { useState } from "react";
import { useNavigate, useLocation } from "react-router";
import "../styles/SelectSeatsStyles.css";
import { toast } from "react-toastify";
import axios from "axios";
import UserSidebar from "../components/UserSidebar";

const SelectSeat = () => {
  const [numberOfSeatsToBook, setPassengers] = useState(1);
  const [classes, setSelectedClass] = useState("");

  const navigate = useNavigate();
  const location = useLocation();

  //const selectedFlight = location.state.selectedFlight;

  const selectedFlight = JSON.parse(sessionStorage.getItem("selectedFlight"));
  console.log(selectedFlight);
  const BASE_URL = "http://localhost:8080";

  const handleBook = async (event) => {
    const uid = sessionStorage.getItem("uid");
    const fid = sessionStorage.getItem("fid");
    const params = {
      nos: numberOfSeatsToBook,
      fid: fid,
      uid: uid,
      cls: classes,
    };

    event.preventDefault();

    try {
      const response = await axios.get(BASE_URL + `/book/seats`, { params });
      const bid = response.data;
      sessionStorage.setItem("bid", bid);
      sessionStorage.setItem("numSeats", numberOfSeatsToBook);
      console.log(bid);
      console.log(numberOfSeatsToBook);
      sessionStorage.setItem("cls", classes);
      navigate("/addPassengers");
    } catch (error) {
      toast.error(`${error.response.data}`, {
        position: toast.POSITION.TOP_CENTER,
      });
    }
  };

  return (
    <>
      <UserSidebar>
        <div className="selectSeats">
          <form onSubmit={handleBook}>
            <h1>Booking Details</h1>

            <table>
              <tr>
                <th>Source : </th>
                <td>{selectedFlight.source}</td>
              </tr>

              <tr>
                <th>Destination : </th>
                <td>{selectedFlight.destination}</td>
              </tr>

              <tr>
                <th>Travel Date : </th>
                <td>{selectedFlight.travelDate}</td>
              </tr>

              <tr>
                <th>Departure Time : </th>
                <td>{selectedFlight.departureTime}</td>
              </tr>

              <tr>
                <th>Arrival Time : </th>
                <td>{selectedFlight.arrivalTime}</td>
              </tr>

              <tr>
                <th>Business Fare : </th>
                <td>{selectedFlight.businessFare}</td>
              </tr>

              <tr>
                <th>First Class Fare : </th>
                <td>{selectedFlight?.firstClassFare}</td>
              </tr>

              <tr>
                <th>Economy Fare : </th>
                <td>{selectedFlight.economyFare}</td>
              </tr>

              <tr>
                <th>Available Seats : </th>
                <td>{selectedFlight.availableSeats}</td>
              </tr>

              <tr>
                <th>Select Number of Passengers : </th>
                <td>
                  <input
                    type="number"
                    min="1"
                    max="10"
                    value={numberOfSeatsToBook}
                    required
                    onChange={(event) =>
                      setPassengers(parseInt(event.target.value))
                    }
                  />
                </td>
              </tr>

              <tr>
                <th>Select Class : </th>
                <td>
                  <select
                    required
                    value={classes}
                    onChange={(event) => setSelectedClass(event.target.value)}
                  >
                    <option value="">Select Class</option>
                    <option value="business">Business</option>
                    <option value="firstclass">First Class</option>
                    <option value="economy">Economy</option>
                  </select>
                </td>
              </tr>
            </table>
            <input type="submit" value="Book Now"></input>
          </form>
        </div>
      </UserSidebar>
    </>
  );
};

export default SelectSeat;
