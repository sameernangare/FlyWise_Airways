import React from "react";
import Sidebar from "../components/Sidebar";
import "../styles/AddFlightStyles.css";
import { useState, useEffect } from "react";
import FlightService from "../services/FlightService";
import UserService from "../services/UserService";
import { toast } from "react-toastify";

export const AddFlight = () => {
  const [sourceCities, setSourceCities] = useState([]);
  const [destinationCities, setDestinationCities] = useState([]);

  const [formData, setFormData] = useState({
    source: "",
    destination: "",
    travelDate: "",
    departureTime: "",
    arrivalTime: "",
    economyFare: "",
    businessFare: "",
    firstClassFare: "",
    availableSeats: "",
  });

  useEffect(() => {
    // Fetch the source and destination cities from your API using Axios
    UserService.getAllCities()
      .then((response) => {
        setSourceCities(response.data);
        setDestinationCities(response.data);
      })
      .catch((error) => {
        console.error("Error fetching cities:", error);
      });
  }, []);

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    // Make a POST request to your API to add the new flight
    FlightService.addFlight(formData)
      .then((response) => {
        toast.success(`${response.data}`, {
          position: toast.POSITION.TOP_CENTER,
        });

        // Clear the form data
        setFormData({
          source: "",
          destination: "",
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
        else if (error.response.status === 403)
          toast.error(`Forbidden access to admin pages`, {
            position: toast.POSITION.TOP_CENTER,
          });
      });
  };

  return (
    <Sidebar>
      <div className="middleContent">
        <form className="add-flight-form" onSubmit={handleSubmit}>
          <div>
            <label htmlFor="source">Source City :</label>
            <select
              id="source"
              name="source"
              value={formData.source}
              onChange={handleInputChange}
              required
            >
              <option value="">Select Source City</option>
              {sourceCities.map((city) => (
                <option key={city.cityId} value={city.city}>
                  {city.city}
                </option>
              ))}
            </select>
          </div>

          <div>
            <label htmlFor="destination">Destination City :</label>
            <select
              id="destination"
              name="destination"
              value={formData.destination}
              onChange={handleInputChange}
              required
            >
              <option value="">Select Destination City</option>
              {destinationCities.map((city) => (
                <option key={city.cityId} value={city.city}>
                  {city.city}
                </option>
              ))}
            </select>
          </div>

          <div>
            <label htmlFor="travelDate">Travel Date :</label>
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
            <label htmlFor="economyFare">Economy Fare :</label>
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
            <label htmlFor="businessFare">Business Fare :</label>
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
            <label htmlFor="firstClassFare">FirstClass Fare :</label>
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
            <label htmlFor="availableSeats">Available Seats :</label>
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
