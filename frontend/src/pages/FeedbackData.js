import React from "react";
import "../styles/FlightDataTableStyles.css";

export const FeedbackData = ({ feedback }) => {
  return (
    <>
      {feedback.map((currentFeedback) => {
        const { feedbackId, feedback, userId, firstName, lastName } =
          currentFeedback;

        return (
          <tr key={feedbackId}>
            <td>{feedbackId}</td>
            <td>{feedback}</td>
            <td>{userId}</td>
            <td>{firstName}</td>
            <td>{lastName}</td>
            {/*            
            <td>
              <button type="button" class="btn btn-light" >
                View
              </button>
            </td> */}
          </tr>
        );
      })}
    </>
  );
};
