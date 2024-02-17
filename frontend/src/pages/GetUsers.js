import React from "react";
import "../styles/FlightDataTableStyles.css";
import { useState, useEffect } from "react";
import AdminService from "../services/AdminService";
import { UsersData } from "./UsersData";
import Sidebar from "../components/Sidebar";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

export const GetUsers = () => {
  const [users, setUsers] = useState([]);
  const navigate = useNavigate();

  const getAllUsers = () => {
    AdminService.getAllUsers()
      .then((response) => {
        setUsers(response.data);
      })
      .catch((error) => {
        if (error.response.status === 404 || error.response.status === 400) {
          toast.error(`${error.response.data}`);
          navigate("/");
        } else if (error.response.status === 500) {
          toast.error(`${error.response.data.message}`);
          navigate("/");
        } else if (error.response.status === 403) {
          toast.error(`Forbidden access to admin pages`);
          navigate("/");
        }
      });
  };

  useEffect(() => {
    getAllUsers();
  }, []);

  return (
    <Sidebar>
      <div className="middleContent">
        <table>
          <tr>
            <th>UserId</th>
            <th>FirstName </th>
            <th>LastName</th>
            <th>Email</th>
            <th>PhoneNumber</th>
            <th>GovtId</th>
            <th>GovtId Number</th>
            {/* <th>View</th> */}
          </tr>
          <tbody>
            <UsersData users={users} />
          </tbody>
        </table>
      </div>
    </Sidebar>
  );
};
