import React from "react";
import { useState } from "react";
import FlightService from "../services/FlightService";
import { toast } from "react-toastify";
import "../styles/ContactUsStyles.css";
import Sidebar from "../components/Sidebar";
import AdminService from "../services/AdminService";
import { BookingData } from "./BookingData";

export const FlightDetails = () => {
  const [flightId, setFlightId] = useState("");
  const [flights, setFlights] = useState([]);

  const getFlightById = () => {
    FlightService.getFlightById(flightId)
      .then((response) => {
        setFlights(response.data);
      })
      .catch((error) => {
        if (error.response.status === 404 || error.response.status === 400) {
          toast.error(`${error.response.data}`, {
            position: toast.POSITION.TOP_CENTER,
          });
        } else if (error.response.status === 500)
          toast.error(`${error.response.data.message}`, {
            position: toast.POSITION.TOP_CENTER,
          });
      });
  };

  const [Bookings, setBookings] = useState([]);

  const getBookings = () => {
    AdminService.getBookingsByFlightId(flightId)
      .then((response) => {
        setBookings(response.data);
      })
      .catch((error) => {
        if (error.response.status === 404 || error.response.status === 400) {
          toast.error(`${error.response.data}`, {
            position: toast.POSITION.TOP_CENTER,
          });
        }
      });
  };

  return (
    <Sidebar>
      <div className="middleContent">
        <div style={{ textAlign: "center" }}>
          <h1>Get Flight Details By Flight Id </h1>
          <br></br>
          <input
            style={{
              height: "3rem",
              borderRadius: "2rem",
              textAlign: "center",
            }}
            type="text"
            value={flightId}
            placeholder="Enter Flight Id"
            onChange={(event) => {
              setFlightId(event.target.value);
            }}
          ></input>
          <button
            style={{ marginLeft: "10px" }}
            className="btn btn-primary"
            value="Submit"
            onClick={getFlightById}
          >
            Get Details
          </button>
        </div>

        <br />

        <table>
          <tr>
            <th>Id</th>
            <th>Source </th>
            <th>Destination</th>
            <th>TravelDate</th>
            <th>ArrivalTime</th>
            <th>DepartureTime</th>
            <th>Economy Fare</th>
            <th>First Class Fare</th>
            <th>Business Fare</th>
            <th>Available Seats</th>
          </tr>

          <tbody>
            <tr key={flights.flightId}>
              <td>{flights.flightId}</td>
              <td>{flights.source}</td>
              <td>{flights.destination}</td>
              <td>{flights.travelDate}</td>
              <td>{flights.arrivalTime}</td>
              <td>{flights.departureTime}</td>
              <td>{flights.economyFare}</td>
              <td>{flights.firstClassFare}</td>
              <td>{flights.businessFare}</td>
              <td>{flights.availableSeats}</td>
            </tr>
          </tbody>
        </table>

        <br></br>

        <div style={{ textAlign: "center" }}>
          {" "}
          <button type="button" class="btn btn-light" onClick={getBookings}>
            Get All Bookings
          </button>
          <br></br>
        </div>
        <br></br>
        <table>
          <tr>
            <th>Booking Id</th>
            <th>User Id </th>
            <th>Seats Booked</th>
            <th>Booking Date</th>
            <th>Booking Status</th>
            <th>ClassName</th>
            <th>Payment Status</th>
          </tr>
          <tbody>
            <BookingData Bookings={Bookings}></BookingData>
          </tbody>
        </table>
      </div>
    </Sidebar>
  );
};
