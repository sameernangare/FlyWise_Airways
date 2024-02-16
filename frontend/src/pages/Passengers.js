import { PassengerData } from "./PassengerData";
import React, { useState } from "react";
import AdminService from "../services/AdminService";
import { toast } from "react-toastify";
import "../styles/ContactUsStyles.css";
import Sidebar from "../components/Sidebar";

export const Passengers = () => {
  const [flightId, setFlightId] = useState("");
  const [passengers, setPassengers] = useState([]);

  const getAllPassengers = () => {
    AdminService.getPassengersByFlightId(flightId)
      .then((response) => {
        setPassengers(response.data);
      })
      .catch((error) => {
        if (error.response.status === 404) {
          toast.error(`${error.response.data}`);
        }
      });
  };

  const check = 0;

  return (
    <Sidebar>
      <div className="middleContent">
        <div style={{ textAlign: "center" }}>
          <h1>Search Passengers By Flight Id </h1>

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
            onClick={getAllPassengers}
          >
            Get Details
          </button>
        </div>

        <br />

        <table>
          <tr>
            <th>Passenger Id</th>
            <th>Booking Id</th>
            <th>Passenger Name</th>
            <th>Gender</th>
            <th>Age</th>
          </tr>
          <tbody>
            <PassengerData passengers={passengers} />
          </tbody>
        </table>
      </div>
    </Sidebar>
  );
};
