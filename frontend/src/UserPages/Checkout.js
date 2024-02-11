import { CLIENT_ID } from "../config";
import React, { useState, useEffect } from "react";
import { PayPalScriptProvider, PayPalButtons } from "@paypal/react-paypal-js";
import { useNavigate } from "react-router";
import { toast } from "react-toastify";
import UserSidebar from "../components/UserSidebar";
import UserService from "../services/UserService";

const Checkout = () => {
  const [show, setShow] = useState(false);
  const [success, setSuccess] = useState(false);
  const [ErrorMessage, setErrorMessage] = useState("");
  const [orderID, setOrderID] = useState(false);
  const fare = sessionStorage.getItem("totalFare");
  const navigate = useNavigate();

  // creates a paypal order
  const createOrder = (data, actions) => {
    return actions.order
      .create({
        purchase_units: [
          {
            description: "Sunflower",
            amount: {
              currency_code: "USD",
              value: fare,
            },
          },
        ],
      })
      .then((orderID) => {
        setOrderID(orderID);
        sessionStorage.setItem("orderID", orderID);
        return orderID;
      });
  };

  // check Approval
  const onApprove = (data, actions) => {
    return actions.order.capture().then(function (details) {
      const { payer } = details;
      setSuccess(true);
    });
  };

  //capture likely error
  const onError = (data, actions) => {
    setErrorMessage("An Error occured with your payment.");
  };

  useEffect(() => {
    if (success) {
      toast.success("Booking Successful.", {
        position: toast.POSITION.TOP_CENTER,
      });
      const bid = parseInt(sessionStorage.getItem("bid"));
      console.log(bid);
      UserService.pay(bid)
        .then((response) => {
          console.log("payment status changed.");
        })
        .catch((error) => {
          if (error.response.status === 404 || error.response.status === 400) {
            toast.error(`${error.response.data}`, {
              position: toast.POSITION.TOP_CENTER,
            });
          } else if (error.response.status === 500)
            toast.error(`No succesful booking yet.`, {
              position: toast.POSITION.TOP_CENTER,
            });
        });
      navigate("/");
    }
  }, [success]);

  return (
    <UserSidebar>
      <PayPalScriptProvider options={{ "client-id": CLIENT_ID }}>
        <div className="middleContent">
          <div className="wrapper">
            <div className="product-info">
              <div className="product-text">
                <h1>Payment Page</h1>
              </div>

              <div className="product-price-btn">
                <p>You need to pay Rs. {fare}</p>
                <button
                  className="buy-btn"
                  type="submit"
                  onClick={() => setShow(true)}
                >
                  Pay Here
                </button>
              </div>
            </div>
          </div>

          <br></br>

          {show ? (
            <PayPalButtons
              style={{ layout: "vertical" }}
              createOrder={createOrder}
              onApprove={onApprove}
            />
          ) : null}
        </div>
      </PayPalScriptProvider>
    </UserSidebar>
  );
};

export default Checkout;
