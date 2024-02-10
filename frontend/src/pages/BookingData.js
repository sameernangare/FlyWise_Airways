import React from "react";
import "../styles/FlightDataTableStyles.css";

export const BookingData = ({ Bookings }) => {
  return (
    <>
      {Bookings.map((currentBooking) => {
        const {
          bookingId,
          userId,
          numberOfSeatsToBook,
          bookingDate,
          bookingStatus,
          paymentStatus,
          className,
        } = currentBooking;

        return (
          <tr key={bookingId}>
            <td>{bookingId}</td>
            <td>{userId}</td>
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
