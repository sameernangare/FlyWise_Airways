import { Route, Routes } from "react-router-dom";
import "./App.css";
import Home from "./routes/Home";
import Navbar from "./components/Navbar";

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
        {/* Rendering the Navbar component */}
        <Navbar></Navbar>

        {/* Defining routes for the application */}
        <Routes>
          {/* Route for the home page, rendering the Home component */}
          <Route path="/" element={<Home />} />
        </Routes>
      </UserContext.Provider>
    </div>
  );
}

export default App;
