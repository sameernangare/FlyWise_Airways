import React, { useState } from "react";
import UserSiderbar from "../components/UserSidebar";

import { toast } from "react-toastify";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Register = () => {
  const navigate = useNavigate();
  const USER_BASE_REST_API_URI = "http://localhost:8080";

  const [formData, setFormData] = useState({
    email: "",
    password: "",
    firstName: "",
    lastName: "",
    phoneNumber: "",
    govtId: "",
    govtIdNumber: "",
  });

  const {
    email,
    password,
    firstName,
    lastName,
    phoneNumber,
    govtId,
    govtIdNumber,
  } = formData;

  const onChange = (e) =>
    setFormData({ ...formData, [e.target.name]: e.target.value });

  const onSubmit = async (event) => {
    event.preventDefault();

    try {
      const response = await axios.post(
        USER_BASE_REST_API_URI + `/register`,
        formData
      );
      console.log(response.data + "<>><");
      toast.success("Registration Successful.");
      navigate("/login");
    } catch (error) {
      if (error.response.status === 404) {
        toast.error(`${error.response.data}`);
      }
    }
    // Call register function or API to register user
  };

  return (
    <UserSiderbar>
      <div className="middleContent">
        <div className="container">
          <h1>Sign Up Here</h1>
          <br />
          <form className="add-flight-form" onSubmit={(e) => onSubmit(e)}>
            <div>
              <label htmlFor="email">Email </label>
              <br />
              <input
                type="email"
                placeholder="Email Address"
                name="email"
                value={email}
                onChange={(e) => onChange(e)}
                required
              />
            </div>

            <div>
              <label htmlFor="password">Password </label>
              <br />
              <input
                type="password"
                placeholder="Password"
                name="password"
                value={password}
                onChange={(e) => onChange(e)}
                minLength="5"
                required
              />
            </div>

            <div>
              <label htmlFor="firstName">First Name </label>
              <br />
              <input
                type="text"
                placeholder="First Name"
                name="firstName"
                value={firstName}
                onChange={(e) => onChange(e)}
                required
              />
            </div>

            <div>
              <label htmlFor="lastName">Last Name </label>
              <br />
              <input
                type="text"
                placeholder="Last Name"
                name="lastName"
                value={lastName}
                onChange={(e) => onChange(e)}
              />
            </div>

            <div>
              <label htmlFor="phoneNumber">Mobile Number </label>
              <br />
              <input
                type="text"
                placeholder="Mobile Number"
                name="phoneNumber"
                value={phoneNumber}
                onChange={(e) => onChange(e)}
              />
            </div>

            <div>
              <label htmlFor="govtId">Govt. ID </label>
              <br />
              <input
                type="text"
                placeholder="Govt ID"
                name="govtId"
                value={govtId}
                onChange={(e) => onChange(e)}
                required
              />
            </div>

            <div>
              <label htmlFor="govtIdNumber">Govt. ID Number </label>
              <br />
              <input
                type="text"
                placeholder="Govt ID Number"
                name="govtIdNumber"
                value={govtIdNumber}
                onChange={(e) => onChange(e)}
                required
              />
            </div>

            <input
              type="submit"
              className="add-flight-form button"
              value="Register"
            />
          </form>
        </div>
      </div>
    </UserSiderbar>
  );
};

export default Register;
