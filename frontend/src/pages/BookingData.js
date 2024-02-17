import React from "react";
import "../styles/FlightDataTableStyles.css";

export const BookingData = ({ Bookings }) => {
  return (
    <>
      {Bookings.map((currentBooking) => {
        const {
          bookingId,
          numberOfSeatsToBook,
          bookingDate,
          bookingStatus,
          paymentStatus,
        } = currentBooking;

        const className = currentBooking.classes.classDescription;

        return (
          <tr key={bookingId}>
            <td>{bookingId}</td>
            <td>{numberOfSeatsToBook}</td>
            <td>{bookingDate}</td>
            <td>{bookingStatus}</td>
            <td>{className}</td>
            <td>{paymentStatus}</td>
          </tr>
        );
      })}
    </>
  );
};
