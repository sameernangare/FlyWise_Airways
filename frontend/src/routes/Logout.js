import { useContext, useEffect } from "react";
import { UserContext } from "../App";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";

function Logout() {
  const { state, dispatch } = useContext(UserContext);
  const navigate = useNavigate();

  useEffect(() => {
    toast.success("Logged Out Successfully.", {
      position: toast.POSITION.TOP_CENTER,
    });

    if (sessionStorage.getItem("role") === "ROLE_USER")
      dispatch({ type: "USER", payload: false });
    else if (sessionStorage.getItem("role") === "ROLE_ADMIN")
      dispatch({ type: "ADMIN", payload: false });
    sessionStorage.clear();
    navigate("/");
  }, []);

  return (
    <div className="middleElement">
      <h1>You have succesfully logged out.</h1>
    </div>
  );
}

export default Logout;
