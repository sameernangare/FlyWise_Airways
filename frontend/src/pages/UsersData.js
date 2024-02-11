import React from "react";
import "../styles/FlightDataTableStyles.css";

export const UsersData = ({ users }) => {
  return (
    <>
      {users.map((currentUser) => {
        const {
          userId,
          firstName,
          lastName,
          email,
          phoneNumber,
          govtId,
          govtIdNumber,
        } = currentUser;

        return (
          <tr key={userId}>
            <td>{userId}</td>
            <td>{firstName}</td>
            <td>{lastName}</td>
            <td>{email}</td>
            <td>{phoneNumber}</td>
            <td>{govtId}</td>
            <td>{govtIdNumber}</td>

            {/* <td>
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
