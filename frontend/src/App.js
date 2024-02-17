import { Route, Routes } from "react-router-dom";
import { ToastContainer } from "react-toastify";
import "./App.css";
import Navbar from "./components/Navbar";
import Home from "./routes/Home";
import About from "./routes/About";
import Contact from "./routes/Contact";
import Footer from "./components/Footer";
import Register from "./routes/Register";
import SignIn from "./routes/SignIn";
import Logout from "./routes/Logout";
import PageNotFound from "./routes/PageNotFound";
import ProtectedRoute from "./routes/ProtectedRoute";
import { UserProfile } from "./UserPages/UserProfile";
import BookFlight from "./UserPages/BookFlight";
import { Dashboard } from "./pages/Dashboard";
import UserBooking from "./UserPages/UserBooking";
import { GetUsers } from "./pages/GetUsers";
import { AddFlight } from "./pages/AddFlight";
import { FlightDetails } from "./pages/FlightDetails";
import { UpdateFlight } from "./pages/UpdateFlight";
import SelectSeat from "./UserPages/SelectSeat";
import ConfirmBooking from "./UserPages/ConfirmBooking";
import { PastBookings } from "./UserPages/PastBookings";
import { PostFeedback } from "./UserPages/PostFeedback";
import { GetUserFeedback } from "./UserPages/GetUserFeedback";
import { UserAnalytics } from "./UserPages/UserAnalytics";
import { GetFeedback } from "./pages/GetFeedback";
import { Analytics } from "./pages/Analytics";
import Checkout from "./UserPages/Checkout";
import FlightCancellation from "./UserPages/FlightCancellation";
import { Passengers } from "./pages/Passengers";

import "react-toastify/dist/ReactToastify.css";

import { createContext, useReducer } from "react";
import { initialState, reducer } from "./reducer/UseReducer";
export const UserContext = createContext();

function App() {
  const [state, dispatch] = useReducer(reducer, initialState);

  return (
    <div className="App">
      <UserContext.Provider value={{ state, dispatch }}>
        <Navbar></Navbar>

        <Routes>
          <Route path="*" element={<PageNotFound />} />
          <Route path="/" element={<Home />} />
          <Route path="/about" element={<About />} />
          <Route path="/contact" element={<Contact />} />
          <Route path="/userhome" element={<Home />} />
          <Route path="/register" element={<Register />} />
          <Route path="/login" element={<SignIn />} />
          <Route path="/bookflight" element={<BookFlight />} />
          <Route path="/logout" element={<Logout />} />

          <Route element={<ProtectedRoute />}>
            <Route path="/userprofile" element={<UserProfile />} />
            <Route path="/addPassengers" element={<UserBooking />} />
            <Route path="/selectseat" element={<SelectSeat />} />
            <Route path="/confirmBooking" element={<ConfirmBooking />} />
            <Route path="/userhistory" element={<PastBookings />} />
            <Route path="/postfeedback" element={<PostFeedback />} />
            <Route path="/getuserfeedback" element={<GetUserFeedback />} />
            <Route path="/useranalytics" element={<UserAnalytics />} />
            <Route path="/checkout" element={<Checkout />} />
            <Route path="/cancelBooking" element={<FlightCancellation />} />
          </Route>

          <Route path="/admin" element={<Dashboard />} />
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/getUsers" element={<GetUsers />} />
          <Route path="/addFlight" element={<AddFlight />} />
          <Route path="/getFlight" element={<FlightDetails />} />
          <Route path="/updateflight" element={<UpdateFlight />} />
          <Route path="/getfeedback" element={<GetFeedback />} />
          <Route path="/analytics" element={<Analytics />} />
          <Route path="/passengers" element={<Passengers />} />
        </Routes>

        <ToastContainer />
        <Footer></Footer>
      </UserContext.Provider>
    </div>
  );
}

export default App;
