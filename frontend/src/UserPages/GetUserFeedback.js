import React from "react";
import UserSidebar from "../components/UserSidebar";
import UserService from "../services/UserService";
import { toast } from "react-toastify";
import { useState, useEffect } from "react";
import { UserFeedbackData } from "./UserFeedbackData";

export const GetUserFeedback = () => {
  const uid = sessionStorage.getItem("uid");
  const [feedback, setFeedback] = useState([]);

  const getAllFeedback = () => {
    UserService.getUserFeedbackById(uid)
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
      });
  };

  useEffect(() => {
    getAllFeedback();
  }, []);

  return (
    <UserSidebar>
      <div className="middleContent">
        <h1 style={{ textAlign: "center" }}>
          Feedbacks By {sessionStorage.getItem("fname")}
        </h1>

        <br></br>

        <table>
          <tr>
            <th>FeedbackId</th>
            <th>Feedback</th>
            <th>Action</th>
          </tr>
          <tbody>
            <UserFeedbackData feedback={feedback} />
          </tbody>
        </table>
      </div>
    </UserSidebar>
  );
};
