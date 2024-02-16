import React, { useState } from "react";
import axios from "axios";
import UserSidebar from "../components/UserSidebar";
//import { Form, Button } from 'react-bootstrap';
import { toast } from "react-toastify";
import { useLocation } from "react-router-dom";
import { useNavigate } from "react-router-dom";

function FlightCancellation() {
  const navigate = useNavigate();

  //const bid=parseInt(sessionStorage.getItem('bid'));
  const location = useLocation();
  const bid = parseInt(sessionStorage.getItem("cbid"));

  const [cancellationReason, setCancellationReason] = useState("");
  const USER_BASE_REST_API_URI = "http://localhost:8080";

  const handleCancellationReasonChange = (event) => {
    setCancellationReason(event.target.value);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post(
        USER_BASE_REST_API_URI + `/user/cancel?bid=${bid}`,
        {
          reason: cancellationReason,
        }
      );
      console.log(response.data);
      toast.success("Cancellation Successful.");
      navigate("/");
    } catch (error) {
      if (error.response.status === 404 || error.response.status === 400) {
        toast.error(`${error.response.data}`);
      } else if (error.response.status === 500) toast.error(`Invalid date`);
    }
  };

  return (
    <UserSidebar>
      <div className="middleContent">
        <form onSubmit={handleSubmit}>
          {/* <Form.Group controlId="cancellationReason"> */}
          <label>Cancellation Reason:</label>
          <textarea
            value={cancellationReason}
            onChange={handleCancellationReasonChange}
          />
          {/* </Form.Group> */}
          <button variant="danger" onClick={handleSubmit}>
            Cancel Flight
          </button>
        </form>
      </div>
    </UserSidebar>
  );
}

export default FlightCancellation;
