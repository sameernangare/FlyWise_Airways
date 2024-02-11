import React from "react";
import "../styles/FlightDataTableStyles.css";
import FlightService from "../services/FlightService";
import { useNavigate } from "react-router-dom";

export const FlightData = ({ flights }) => {
  const navigate = useNavigate();

  const updateFlight = (flightId) => {
    console.log(flightId);

    FlightService.getFlightById(flightId)
      .then((response) => {
        const flight = response.data;
        navigate(`/updateflight`, { state: { flight } });
      })
      .catch((error) => {
        alert(error.response.data);
      });
  };

  return (
    <>
      {flights.map((currentFlight) => {
        const {
          flightId,
          source,
          destination,
          travelDate,
          arrivalTime,
          departureTime,
          economyFare,
          firstClassFare,
          businessFare,
          availableSeats,
        } = currentFlight;

        return (
          <tr key={flightId}>
            <td>{flightId}</td>
            <td>{source}</td>
            <td>{destination}</td>
            <td>{travelDate}</td>
            <td>{arrivalTime}</td>
            <td>{departureTime}</td>
            <td>{economyFare}</td>
            <td>{firstClassFare}</td>
            <td>{businessFare}</td>
            <td>{availableSeats}</td>
            <td>
              <button
                type="button"
                class="btn btn-light"
                onClick={() => updateFlight(currentFlight.flightId)}
              >
                Update
              </button>
            </td>
          </tr>
        );
      })}
    </>
  );
};
