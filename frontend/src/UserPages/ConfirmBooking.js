import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { toast } from "react-toastify";
import UserSidebar from "../components/UserSidebar";

function ConfirmBooking() {
  const [data, setData] = useState(null);
  const bid = sessionStorage.getItem("bid");
  const BASE_URL = "http://localhost:8080";
  const token = sessionStorage.getItem("jwtToken");
  const navigate = useNavigate();

  useEffect(() => {
    axios
      .get(BASE_URL + `/user/confirm?bid=${bid}`)
      .then((response) => {
        setData(response.data);
        sessionStorage.setItem("totalFare", response.data.totalPayment);
      })
      .catch((error) => {
        toast.error(`${error.response.data}`);
      });
  }, [bid, token]);

  const handleConfirmation = () => {
    navigate("/checkout");
  };

  return (
    <UserSidebar>
      <div className="middleContent">
        <h2>Booking Confirmation</h2>
        {data && (
          <>
            <div className="row">
              <div className="col-md-6">
                <h3>Flight Details : </h3>
                <table className="table table-striped">
                  <tbody>
                    <tr>
                      <td>Flight No : </td>
                      <td>{data.flightDto.flightId}</td>
                    </tr>

                    <tr>
                      <td>Source : </td>
                      <td>{data.flightDto.source}</td>
                    </tr>

                    <tr>
                      <td>Destination : </td>
                      <td>{data.flightDto.destination}</td>
                    </tr>

                    <tr>
                      <td>Departure Date : </td>
                      <td>{data.flightDto.travelDate}</td>
                    </tr>

                    <tr>
                      <td>Arrival Time : </td>
                      <td>{data.flightDto.arrivalTime}</td>
                    </tr>

                    <tr>
                      <td>Departure Time : </td>
                      <td>{data.flightDto.departureTime}</td>
                    </tr>
                  </tbody>
                </table>
              </div>
              <div className="col-md-3">
                <div className="card">
                  <div className="card-header bg-primary text-white">
                    <h3 className="card-title mb-0">Total Fare : </h3>
                  </div>
                  <div className="card-body">
                    <h2>RS.{data.totalPayment}</h2>
                  </div>
                </div>
              </div>
            </div>
            <div>
              <h3>Passenger Details : </h3>
              <table className="table table-striped">
                <thead>
                  <tr>
                    <th>Passenger #</th>
                    <th>Passenger ID</th>
                    <th>Name</th>
                    <th>Age</th>
                    <th>Gender</th>
                    <th>Booking ID</th>
                  </tr>
                </thead>
                <tbody>
                  {data.listOfPassengerDto.map((passenger, index) => (
                    <tr key={index}>
                      <td>{index + 1}</td>
                      <td>{passenger.passengerId}</td>
                      <td>{passenger.passengerName}</td>
                      <td>{passenger.age}</td>
                      <td>{passenger.gender}</td>
                      <td>{passenger.bookingId}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
            <button onClick={handleConfirmation} className="btn btn-primary">
              Confirm Now
            </button>
          </>
        )}
      </div>
    </UserSidebar>
  );
}

export default ConfirmBooking;
