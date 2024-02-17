import React from "react";
import UserSidebar from "../components/UserSidebar";
import UserService from "../services/UserService";
import { useState, useEffect } from "react";
import { PastBookingsData } from "./PastBookingsData";
import { toast } from "react-toastify";

export const PastBookings = () => {
  const [bookings, setBookings] = useState([]);

  const getAllBookings = () => {
    const uid = parseInt(sessionStorage.getItem("uid"));
    UserService.getUserBookings(uid)
      .then((response) => {
        setBookings(response.data);
      })
      .catch((error) => {
        if (error.response.status === 404 || error.response.status === 400) {
          toast.error(`${error.response.data}`);
        } else if (error.response.status === 500)
          toast.error(`No succesful booking yet.`);
      });
  };

  const handleButton = () => {
    getAllBookings();
  };

  return (
    <UserSidebar>
      <div className="middleContent">
        <h1 style={{ textAlign: "center" }}>
          Past Bookings By {sessionStorage.getItem("fname")}
        </h1>

        <br></br>

        <button onClick={handleButton}>Get Bookings</button>

        <br></br>

        <table>
          <tr>
            <th>bookingId</th>
            <th>Source</th>
            <th>Destination</th>
            <th>Booking Date</th>
            <th>Number of Seats</th>
            <th>Booking Status</th>
            <th>Total Payment</th>
            <th>Class Name</th>
            <th>Payment Status</th>
            <th>Cancel Ticket</th>
          </tr>
          <tbody>
            <PastBookingsData bookings={bookings} />
          </tbody>
        </table>
      </div>
    </UserSidebar>
  );
};
