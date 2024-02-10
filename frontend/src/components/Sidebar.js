import React, { useState } from "react";
import {
  FaBars,
  FaRegChartBar,
  FaCommentAlt,
  FaPeopleArrows,
} from "react-icons/fa";

import { BsFillAirplaneFill } from "react-icons/bs";
import { BiHomeCircle } from "react-icons/bi";
import { NavLink } from "react-router-dom";
import { MdFlightTakeoff } from "react-icons/md";
import { FiUsers } from "react-icons/fi";
import "../styles/SidebarStyles.css";

const Sidebar = ({ children }) => {
  const [isOpen, setIsOpen] = useState(false);
  const toggle = () => setIsOpen(!isOpen);
  const menuItem = [
    {
      path: "/admin",
      name: "Dashboard",
      icon: <BiHomeCircle />,
    },

    {
      path: "/getUsers",
      name: "Get Users",
      icon: <FiUsers />,
    },

    {
      path: "/addflight",
      name: "Add Flights",
      icon: <BsFillAirplaneFill />,
    },

    {
      path: "/getFlight",
      name: "Flight Details",
      icon: <MdFlightTakeoff />,
    },

    {
      path: "/passengers",
      name: "Passenger Info",
      icon: <FaPeopleArrows />,
    },

    {
      path: "/getfeedback",
      name: "Get Feedback",
      icon: <FaCommentAlt />,
    },

    {
      path: "/analytics",
      name: "Analytics",
      icon: <FaRegChartBar />,
    },
  ];
  return (
    <div className="containerCustom">
      <div style={{ width: isOpen ? "300px" : "50px" }} className="sidebar">
        <div className="top_section">
          <h1 style={{ display: isOpen ? "block" : "none" }} className="logo">
            Home
          </h1>
          <div style={{ marginLeft: isOpen ? "50px" : "0px" }} className="bars">
            <FaBars onClick={toggle} />
          </div>
        </div>

        {menuItem.map((item, index) => (
          <NavLink
            to={item.path}
            key={index}
            className="link"
            activeclassName="active"
          >
            <div className="icon">{item.icon}</div>
            <div
              style={{ display: isOpen ? "block" : "none" }}
              className="link_text"
            >
              {item.name}
            </div>
          </NavLink>
        ))}
      </div>

      <main>{children}</main>
    </div>
  );
};

export default Sidebar;
