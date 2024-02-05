import { Route, Routes } from "react-router-dom";
import "./App.css";
import Navbar from "./components/Navbar";
import Home from "./routes/Home";
import About from "./routes/About";
import Contact from "./routes/Contact";

// Importing Context and Reducer for state management
import { createContext, useReducer } from "react";
import { initialState, reducer } from "./reducer/UseReducer";
export const UserContext = createContext();

function App() {
  // Using the useReducer hook to manage state with the specified reducer function and initial state
  const [state, dispatch] = useReducer(reducer, initialState);

  // Rendering the main structure of the application
  return (
    <div className="App">
      {/* Providing the UserContext to the entire application */}
      <UserContext.Provider value={{ state, dispatch }}>
        <Navbar></Navbar>

        <Routes>
          {/* Route for the home page, rendering the Home component */}
          <Route path="/" element={<Home />} />

          {/* Route for the about us page, rendering the About component */}
          <Route path="/about" element={<About />} />

          {/* Route for the contact us page, rendering the Contact component */}
          <Route path="/contact" element={<Contact />} />
        </Routes>
      </UserContext.Provider>
    </div>
  );
}

export default App;
