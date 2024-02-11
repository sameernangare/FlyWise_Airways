import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

export const PastBookingsData = ({ bookings }) => {
  const navigate = useNavigate();
  const [bookingId, setBookingId] = useState();
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
          source,
          destination,
          bookingDate,
          numberOfSeatsToBook,
          totalPayment,
          className,
          bookingStatus,
          paymentStatus,
        } = currentBooking;

        return (
          <tr key={bookingId}>
            <td>{bookingId}</td>
            <td>{source}</td>
            <td>{destination}</td>
            <td>{bookingDate}</td>
            <td>{numberOfSeatsToBook}</td>
            <td>{totalPayment}</td>
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
