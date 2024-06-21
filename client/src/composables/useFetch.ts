import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080/api/",
  timeout: 15000,
});

const useFetch = () => {
  const get = async (url: string, token?: string) => {
    try {
      const response = await api.get(url, {
        headers: token ? { Authorization: `Bearer ${token}` } : {},
      });
      return response.data;
    } catch (error) {
      return null;
    }
  };

  const post = async (url: string, data: any, token?: string) => {
    try {
      const response = await api.post(url, data, {
        headers: token ? { Authorization: `Bearer ${token}` } : {},
      });
      return response.data;
    } catch (error) {
      return null;
    }
  };

  return {
    get,
    post
  };
};

export default useFetch
