import axios from "axios";

const BASE_URL = "http://localhost:8080/";

const token = sessionStorage.getItem("jwtToken");

export const publicRequest = axios.create({
  baseURL: BASE_URL,
});

export const userRequest = axios.create({
  baseURL: BASE_URL,
  headers: {
    Authorization: `Bearer ${token}`,
  },
});
