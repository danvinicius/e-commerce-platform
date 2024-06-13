import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080/api/",
  timeout: 15000,
});

export const useFetch = () => {
  const get = async (url: string, token?: string) => {
    try {
      const response = await api.get(url, {
        headers: { Authorization: `Bearer ${token}` },
      });
      return response.data;
    } catch (error) {
      return null;
    }
  };

  return {
    get,
  };
};
