import React from "react";
import "../styles/FlightDataTableStyles.css";

export const UserFeedbackData = ({ feedback }) => {
  return (
    <>
      {feedback.map((currentFeedback) => {
        const { feedbackId, feedback } = currentFeedback;

        return (
          <tr key={feedbackId}>
            <td>{feedbackId}</td>
            <td>{feedback}</td>
          </tr>
        );
      })}
    </>
  );
};
