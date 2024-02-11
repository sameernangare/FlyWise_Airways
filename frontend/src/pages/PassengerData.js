import React from "react";
import "../styles/FlightDataTableStyles.css";

export const PassengerData = ({ passengers }) => {
  return (
    <>
      {passengers.map((currentPassenger) => {
        const { passengerId, passengerName, gender, age, bookingId } =
          currentPassenger;

        return (
          <tr key={passengerId}>
            <td>{passengerId}</td>
            <td>{passengerName}</td>
            <td>{gender}</td>
            <td>{age}</td>
            <td>{bookingId}</td>
          </tr>
        );
      })}
    </>
  );
};
