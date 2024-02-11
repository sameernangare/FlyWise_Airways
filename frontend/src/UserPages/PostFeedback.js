import React from "react";
import UserSidebar from "../components/UserSidebar";
import "../styles/UserFeedbackStyles.css";
import { useState } from "react";
import { toast } from "react-toastify";
import axios from "axios";

export const PostFeedback = () => {
  const [feedback, setFeedback] = useState("");
  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const BASE_URL = "http://localhost:8080";

  const handleMessageChange = (event) => {
    setFeedback(event.target.value);
  };

  const uid = parseInt(sessionStorage.getItem("uid"));

  const params = {
    feedback: feedback,
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    try {
      const response = axios.post(
        BASE_URL + `/user/feedback?uid=${uid}&feedback=${feedback}`
      );
      toast.success(`Feedback was posted successfully.`, {
        position: toast.POSITION.TOP_CENTER,
      });
    } catch (error) {
      toast.error("Feedback was not posted. Try again.", {
        position: toast.POSITION.TOP_CENTER,
      });
    }
  };

  return (
    <UserSidebar>
      <div className="feedback-container">
        <h1>Feedback Form</h1>
        {successMessage && <p className="success-message">{successMessage}</p>}
        {errorMessage && <p className="error-message">{errorMessage}</p>}
        <form onSubmit={handleSubmit}>
          <label>
            User ID :
            <input
              style={{ textAlign: "center" }}
              type="number"
              value={sessionStorage.getItem("uid")}
              readOnly
            />
          </label>

          <label>Message : </label>
          <textarea onChange={handleMessageChange} required />

          <br />

          <button type="submit">Submit</button>
        </form>
      </div>
    </UserSidebar>
  );
};
