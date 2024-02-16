import React from "react";
import { useNavigate } from "react-router-dom";

export const PastBookingsData = ({ bookings }) => {
  const navigate = useNavigate();
  const handleCancel = (bookingId) => {
    console.log(bookingId);
    sessionStorage.setItem("cbid", JSON.stringify(bookingId));
    navigate(`/cancelBooking`);
  };

  return (
    <>
      {bookings.map((currentBooking) => {
        const {
          bookingId,
          bookingDate,
          numberOfSeatsToBook,
          // totalPayment,
          bookingStatus,
          paymentStatus,
        } = currentBooking;

        const source = currentBooking.flight.source;
        const destination = currentBooking.flight.destination;
        // const paymentStatus = currentBooking.payment.totalPayment;
        const className = currentBooking.classes.classDescription;
        return (
          <tr key={bookingId}>
            <td>{bookingId}</td>
            <td>{source}</td>
            <td>{destination}</td>
            <td>{bookingDate}</td>
            <td>{numberOfSeatsToBook}</td>
            <td>{bookingStatus}</td>
            <td>{className}</td>
            <td>{paymentStatus}</td>
            <td>
              <button
                type="button"
                class="btn btn-light"
                onClick={() => handleCancel(currentBooking.bookingId)}
              >
                Cancel
              </button>
            </td>
          </tr>
        );
      })}
    </>
  );
};
