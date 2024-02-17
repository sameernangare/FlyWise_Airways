import React from "react";
import "../styles/PageNotFoundStyles.css";
import NotFound from "../assets/notFound.jpg";

const PageNotFound = () => {
  return (
    <div className="not-found-container">
      <h1>Sorry, the page you are looking for cannot be found.</h1>
      <img src={NotFound} alt="Page Not Found" className="airplane-image" />
      <p>
        It seems that the page you were trying to access either no longer exists
        or there was an error in the URL you entered. Please check the URL and
        try again.
      </p>
      <p>
        Alternatively, you can navigate back to our homepage and browse through
        our available flights and destinations. If you continue to have trouble,
        please contact our customer support team for further assistance.
      </p>
      <p>Thank you for choosing our flight booking website.</p>
    </div>
  );
};

export default PageNotFound;
