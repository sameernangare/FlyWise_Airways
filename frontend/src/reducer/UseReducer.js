// Initial state for the Redux store
export const initialState = null;

// Reducer function for handling state changes based on dispatched actions
export const reducer = (state, action) => {
  // Check if the action type is "USER"
  if (action.type === "USER") {
    // If true, update the state with the payload from the action
    return action.payload;
  }
  // Check if the action type is "ADMIN"
  else if (action.type === "ADMIN") {
    // If true, update the state with the payload from the action
    return action.payload;
  }

  // If the action type doesn't match any of the above, return the current state
  return state;
};