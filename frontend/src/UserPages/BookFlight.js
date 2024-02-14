import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router";
import { publicRequest } from "../Constants";
import "../styles/SearchBox.css";
import { toast } from "react-toastify";
import UserSidebar from "../components/UserSidebar";
// import axios from "axios";

function BookFlight() {
  const [src, setsrc] = useState("");
  const [dest, setdest] = useState("");
  const [dt, setdt] = useState("");
  const [flights, setFlights] = useState([]);
  const navigate = useNavigate();
  const [cities, setCities] = useState([]);

  useEffect(() => {
    const fetchCities = async () => {
      try {
        const response = await publicRequest.get(`/cities`);
        
        setCities(response.data);
      } catch (error) {
        toast.error(`${error.response.data}`);
      }
    };

    fetchCities();
  }, []);

 

  const handleSearch = async (event) => {
    event.preventDefault();

    try {
      const response = await publicRequest.get(
        `/flight/fetch?src=${src}&dest=${dest}&dt=${dt}`
      );
      setFlights(response.data);
    } catch (error) {
      toast.error(`${error.response.data}`);
    }
  };

  // sessionStorage.setItem('flight', flights)

  const handleBook = async (flightId) => {
    sessionStorage.setItem("fid", flightId);

    console.log(sessionStorage.getItem("fid"));

    const selectedFlight = flights.find(
      (flight) => flight.flightId === flightId
    );

    sessionStorage.setItem("selectedFlight", JSON.stringify(selectedFlight));
    console.log(selectedFlight);

    if (
      sessionStorage.getItem("role") === "ROLE_USER" &&
      sessionStorage.getItem("jwtToken")
    ) {
      // const selectedFlight = flights.find((flight) => flight.flightId === flightId);
      //  navigate('/selectseat', { state: { selectedFlight } });
      navigate("/selectseat");
    } else {
      // const selectedFlight = flights.find((flight) => flight.flightId === flightId);
      //  navigate('/login', { state: { selectedFlight } });
      navigate("/login");
    }
  };

  function fetchsrc(value) {
    setsrc(value);
  }

  function fetchdest(value) {
    setdest(value);
  }

  return (
    <>
      <UserSidebar>
        <div className="middleContent">
          <div className="searchBar">
            <div className="searchBox">
              <div className="searchList">
                <form onSubmit={handleSearch}>
                  <label>
                    Source:
                    <select
                      name="src"
                      value={src}
                      onChange={(event) => fetchsrc(event.target.value)}
                    >
                      <option value="">--Select--</option>
                      {cities.map((city) => (
                        <option key={city.city} value={city.city}>
                          {city.city}
                        </option>
                      ))}
                    </select>
                  </label>
                  <label>
                    Destination:
                    <select
                      name="dest"
                      value={dest}
                      onChange={(event) => fetchdest(event.target.value)}
                    >
                      <option value="">--Select--</option>
                      {cities.map((city) => (
                        <option key={city.city} value={city.city}>
                          {city.city}
                        </option>
                      ))}
                    </select>
                  </label>
                  <label>
                    Date:
                    <input
                      type="date"
                      value={dt}
                      onChange={(event) => setdt(event.target.value)}
                      required
                    />
                  </label>
                  <button type="submit">Search Flights</button>
                </form>
              </div>
            </div>
          </div>

          <hr />

          <div className="container">
            <h4 className="text-center">Available Flights</h4>
            <table className="table table-bordered table-striped">
              <thead>
                <tr>
                  <th>Flight ID</th>
                  <th>Source</th>
                  <th>Destination</th>
                  <th>Departure Time</th>
                  <th>Arrival Time</th>
                  <th>Business Class Fare</th>
                  <th>First Class Fare</th>
                  <th>Economy Class Fare</th>
                  <th>Available Seats</th>
                  <th>Action</th>
                </tr>
              </thead>
              <tbody>
                {flights.map((flight) => (
                  <tr key={flight.flightId}>
                    <td>{flight.flightId}</td>
                    <td>{flight.source}</td>
                    <td>{flight.destination}</td>
                    <td>{flight.departureTime}</td>
                    <td>{flight.arrivalTime}</td>
                    <td>{flight.businessFare}</td>
                    <td>{flight.firstClassFare}</td>
                    <td>{flight.economyFare}</td>
                    <td>{flight.availableSeats}</td>
                    <td>
                      <button onClick={() => handleBook(flight.flightId)}>
                        Book
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      </UserSidebar>
    </>
  );
}

export default BookFlight;
