import { Link } from "react-router-dom";
import "../styles/NavbarStyles.css";
import { MenuItems } from "./MenuItems";
import { useState } from "react";
import { UserContext } from "../App";
import { useContext } from "react";

function Navbar() {
  // Destructuring state and dispatch from the UserContext
  const { state, dispatch } = useContext(UserContext);

  // State to manage the click event for the mobile menu
  const [clicked, setClicked] = useState(false);

  // Function to handle the click event for the mobile menu
  function handleClick() {
    setClicked(!clicked);
  }

  // Component to render different menu items based on the user's authentication state
  const RenderMenu = () => {
    if (state) {
      // If user is authenticated, render a welcome message and logout button
      return (
        <>
          <label>Welcome {sessionStorage.getItem("fname")}!</label>
          <Link to="/logout">
            <button> Logout</button>
          </Link>
        </>
      );
    } else {
      // If user is not authenticated, render register and sign-in buttons
      return (
        <>
          <Link to="/register">
            <button>Register</button>
          </Link>
          <Link to="/login">
            <button>SignIn</button>
          </Link>
        </>
      );
    }
  };

  // Main render function for the Navbar component
  return (
    <nav className="NavbarItems">
      <h1 className="navbar-logo">FlyWise Airways</h1>

      {/* Mobile menu icons with click event handling */}
      <div className="menu-icons" onClick={handleClick}>
        {/* <i> -- icon */}
        <i className={clicked ? "fas fa-times" : "fas fa-bars"}></i>
      </div>

      {/* Navigation menu with dynamic class based on mobile menu click */}
      <ul className={clicked ? "navbar-menu active" : "navbar-menu"}>
        {/* Mapping through MenuItems array and rendering each menu item */}
        {MenuItems.map((item, index) => {
          return (
            <li key={index}>
              {/* Link to each menu item with dynamic class and content */}
              <Link className={item.cName} to={item.url}>
                <i className={item.icon}></i>
                {item.title}
              </Link>
            </li>
          );
        })}

        {/* Rendering the dynamic menu based on user authentication state */}
        <RenderMenu></RenderMenu>
      </ul>
    </nav>
  );
}

export default Navbar;
