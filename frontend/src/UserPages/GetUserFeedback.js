import React from "react";
import UserSidebar from "../components/UserSidebar";
import UserService from "../services/UserService";
import { toast } from "react-toastify";
import { useState, useEffect } from "react";
import { UserFeedbackData } from "./UserFeedbackData";

export const GetUserFeedback = () => {
  const [feedback, setFeedback] = useState([]);

  const getAllFeedback = () => {
    const uid = sessionStorage.getItem("uid");
    UserService.getUserFeedbackById(uid)
      .then((response) => {
        setFeedback(response.data);
      })
      .catch((error) => {
        if (error.response.status === 404 || error.response.status === 400) {
          toast.error(`${error.response.data}`);
        } else if (error.response.status === 500)
          toast.error(`${error.response.data.message}`);
      });
  };

  const handleButton = () => {
    getAllFeedback();
  };

  return (
    <UserSidebar>
      <div className="middleContent">
        <h1 style={{ textAlign: "center" }}>
          Feedbacks By {sessionStorage.getItem("fname")}
        </h1>
        <br />
        <button onClick={handleButton}>Get Feedback</button>
        <div />
        <br />
        <table>
          <tr>
            <th>FeedbackId</th>
            <th>Feedback</th>
          </tr>
          <tbody>
            <UserFeedbackData feedback={feedback} />
          </tbody>
        </table>
      </div>
    </UserSidebar>
  );
};
