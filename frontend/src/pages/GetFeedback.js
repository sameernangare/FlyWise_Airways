import React from "react";
import "../styles/FlightDataTableStyles.css";
import { useState, useEffect } from "react";
import AdminService from "../services/AdminService";
import { FeedbackData } from "./FeedbackData";
import Sidebar from "../components/Sidebar";
import { toast } from "react-toastify";

export const GetFeedback = () => {
  const [feedback, setFeedback] = useState([]);

  const getAllFeedback = () => {
    AdminService.getAllFeedback()
      .then((response) => {
        setFeedback(response.data);
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

  useEffect(() => {
    getAllFeedback();
  }, []);

  return (
    <Sidebar>
      <div className="middleContent">
        <h1 style={{ textAlign: "center" }}>Feedback From All Users</h1>

        <br></br>

        <table>
          <tr>
            <th>FeedbackId</th>
            <th>Feedback</th>
            <th>UserId</th>
            <th>User FirstName</th>
            <th>User LastName</th>
            {/* <th>Action</th> */}
          </tr>
          <tbody>
            <FeedbackData feedback={feedback} />
          </tbody>
        </table>
      </div>
    </Sidebar>
  );
};
