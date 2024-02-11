import React from "react";
import { FlightData } from "./FlightData";
import "../styles/FlightDataTableStyles.css";
import { useState, useEffect } from "react";
import FlightService from "../services/FlightService";
import Sidebar from "../components/Sidebar";
import { toast } from "react-toastify";

export const Dashboard = () => {
  const [flights, setFlights] = useState([]);

  const getAllFlights = () => {
    FlightService.getAllFlights()
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
        else if (error.response.status === 403)
          toast.error(`Forbidden access to admin pages`, {
            position: toast.POSITION.TOP_CENTER,
          });
      });
  };

  useEffect(() => {
    getAllFlights();
  }, []);

  return (
    <>
      <Sidebar>
        <div className="middleContent">
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
              <th>Update</th>
            </tr>
            <tbody>
              <FlightData flights={flights} />
            </tbody>
          </table>
        </div>
      </Sidebar>
    </>
  );
};
