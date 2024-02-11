import axios from "axios";
import React, { useEffect, useState } from "react";
import UserSidebar from "../components/UserSidebar";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

export const UserProfile = () => {
  const navigate = useNavigate();
  const USER_BASE_REST_API_URI = "http://localhost:8080";
  const uid = sessionStorage.getItem("uid");
  const [user, setUser] = useState();

  useEffect(() => {
    const response = axios.get(USER_BASE_REST_API_URI + `/user/getbyid`);
    setUser(response.data);
  }, []);

  const [formData, setFormData] = useState({
    email: user.email,
    password: user.password,
    firstName: user.firstName,
    lastName: user.lastName,
    phoneNumber: user.phoneNumber,
    govtId: user.govtId,
    govtIdNumber: user.govtIdNumber,
  });

  const onChange = (e) =>
    setFormData({ ...formData, [e.target.name]: e.target.value });

  const onSubmit = async (event) => {
    event.preventDefault();

    try {
      const response = await axios.post(
        USER_BASE_REST_API_URI + `/register`,
        formData
      );

      console.log(response.data);
      toast.success("Registration Successful.", {
        position: toast.POSITION.TOP_CENTER,
      });
      navigate("/login");
    } catch (error) {
      if (error.response.status === 404) {
        toast.error(`${error.response.data}`, {
          position: toast.POSITION.TOP_CENTER,
        });
      }
    }
  };

  return (
    <UserSidebar>
      <div className="middleContent">
        <form className="add-flight-form" onSubmit={(e) => onSubmit(e)}>
          <div className="form-group">
            <label htmlFor="email">Email : </label>
            <br />
            <input
              type="email"
              placeholder="Email Address"
              name="email"
              value={user.email}
              onChange={(e) => onChange(e)}
              readOnly
            />
          </div>
          <div className="form-group">
            <label htmlFor="password">Password : </label>
            <br />
            <input
              type="password"
              placeholder="Password"
              name="password"
              value={user.password}
              onChange={(e) => onChange(e)}
              minLength="5"
              required
            />
          </div>
          <div className="form-group">
            <label htmlFor="firstName">First Name : </label>
            <br />
            <input
              type="text"
              placeholder="First Name"
              name="firstName"
              value={user.firstName}
              onChange={(e) => onChange(e)}
              required
            />
          </div>
          <div className="form-group">
            <label htmlFor="lastName">Last Name : </label>
            <br />
            <input
              type="text"
              placeholder="Last Name"
              name="lastName"
              value={user.lastName}
              onChange={(e) => onChange(e)}
            />
          </div>
          <div className="form-group">
            <label htmlFor="phoneNumber">Mobile Number : </label>
            <br />
            <input
              type="text"
              placeholder="Mobile Number"
              name="phoneNumber"
              value={user.phoneNumber}
              onChange={(e) => onChange(e)}
            />
          </div>
          <div className="form-group">
            <label htmlFor="govtId">Govt. ID : </label>
            <br />
            <input
              type="text"
              placeholder="Govt ID"
              name="govtId"
              value={user.govtId}
              onChange={(e) => onChange(e)}
              required
            />
          </div>
          <div className="form-group">
            <label htmlFor="govtIdNumber">Govt. ID Number : </label>
            <br />
            <input
              type="text"
              placeholder="Govt ID Number"
              name="govtIdNumber"
              value={user.govtIdNumber}
              onChange={(e) => onChange(e)}
              required
            />
          </div>
          <input type="submit" className="btn btn-primary" value="Register" />
        </form>
      </div>
    </UserSidebar>
  );
};
