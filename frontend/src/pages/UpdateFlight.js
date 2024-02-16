import React from "react";
import { useLocation } from "react-router-dom";
import Sidebar from "../components/Sidebar";
import "../styles/AddFlightStyles.css";
import { useState } from "react";
import FlightService from "../services/FlightService";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

export const UpdateFlight = ({ flight }) => {
  const location = useLocation();
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    flightId: location.state.flight.flightId,
    source: location.state.flight.source,
    destination: location.state.flight.destination,
    travelDate: location.state.flight.travelDate,
    departureTime: location.state.flight.departureTime,
    arrivalTime: location.state.flight.arrivalTime,
    economyFare: location.state.flight.economyFare,
    businessFare: location.state.flight.businessFare,
    firstClassFare: location.state.flight.firstClassFare,
    availableSeats: location.state.flight.availableSeats,
  });

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    // Make a PUT request to your API to add the new flight
    FlightService.updateFlight(formData)
      .then((response) => {
        toast.success(`${response.data}`);

        navigate("/dashboard");

        // Clear the form data
        setFormData({
          travelDate: "",
          departureTime: "",
          arrivalTime: "",
          economyFare: "",
          businessFare: "",
          firstClassFare: "",
          availableSeats: "",
        });
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
      });
  };

  return (
    <Sidebar>
      <div className="middleContent">
        <form className="add-flight-form" onSubmit={handleSubmit}>
          <div>
            <label htmlFor="flightId">Flight Id : </label>
            <input
              type="text"
              readOnly
              value={formData.flightId}
              id="flightId"
              name="flightId"
            ></input>
          </div>

          <div>
            <label htmlFor="source">Source City : </label>
            <input
              type="text"
              id="source"
              name="source"
              value={formData.source}
              readOnly
              required
            ></input>
          </div>

          <div>
            <label htmlFor="destination">Destination City : </label>
            <input
              type="text"
              id="destination"
              name="destination"
              value={formData.destination}
              readOnly
              required
            ></input>
          </div>

          <div>
            <label htmlFor="travelDate">Travel Date : </label>
            <input
              type="date"
              id="travelDate"
              name="travelDate"
              value={formData.travelDate}
              onChange={handleInputChange}
              required
            />
          </div>

          <div>
            <label htmlFor="departureTime">Departure Time :</label>
            <input
              type="time"
              id="departureTime"
              name="departureTime"
              value={formData.departureTime}
              onChange={handleInputChange}
              required
            />
          </div>

          <div>
            <label htmlFor="arrivalTime">Arrival Time :</label>
            <input
              type="time"
              id="arrivalTime"
              name="arrivalTime"
              value={formData.arrivalTime}
              onChange={handleInputChange}
              required
            />
          </div>

          <div>
            <label htmlFor="economyFare">Economy Fare : </label>
            <input
              type="number"
              id="economyFare"
              name="economyFare"
              value={formData.economyFare}
              onChange={handleInputChange}
              required
            />
          </div>

          <div>
            <label htmlFor="businessFare">Business Fare : </label>
            <input
              type="number"
              id="businessFare"
              name="businessFare"
              value={formData.businessFare}
              onChange={handleInputChange}
              required
            />
          </div>

          <div>
            <label htmlFor="firstClassFare">FirstClass Fare : </label>
            <input
              type="number"
              id="firstClassFare"
              name="firstClassFare"
              value={formData.firstClassFare}
              onChange={handleInputChange}
              required
            />
          </div>

          <div>
            <label htmlFor="availableSeats">Available Seats : </label>
            <input
              type="number"
              id="availableSeats"
              name="availableSeats"
              value={formData.availableSeats}
              onChange={handleInputChange}
              required
            />
          </div>
          <input type="submit" value="Submit"></input>
        </form>
      </div>
    </Sidebar>
  );
};
