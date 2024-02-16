import React from "react";
import "../styles/FlightDataTableStyles.css";

export const PassengerData = ({ passengers }) => {
  return (
    <>
      {passengers.map((currentPassenger) => {
        const { passengerId, passengerName, gender, age } = currentPassenger;

        const bookingId = currentPassenger.booking.bookingId;

        return (
          <tr key={passengerId}>
            <td>{bookingId}</td>
            <td>{passengerId}</td>
            <td>{passengerName}</td>
            <td>{gender}</td>
            <td>{age}</td>
          </tr>
        );
      })}
    </>
  );
};
