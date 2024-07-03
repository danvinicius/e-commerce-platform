import axios from "axios";
import { ref } from "vue";

const api = axios.create({
  baseURL: "http://localhost:8080/api",
  timeout: 15000,
});

const useFetch = () => {
  const error = ref("");
  const loading = ref(false);
  const data = ref()
  const get = async (url: string, token?: string) => {
    loading.value = true;
    try {
      const response = await api.get(url, {
        headers: token ? { Authorization: `Bearer ${token}` } : {},
      });
      return response.data;
    } catch (err) {
      error.value = err.response.data.message;
      return null;
    } finally {
      loading.value = false;
    }
  };

  const post = async (url: string, data: any, token?: string) => {
    loading.value = true;
    try {
      const response = await api.post(url, data, {
        headers: token ? { Authorization: `Bearer ${token}` } : {},
      });
      return response.data;
    } catch (err) {
      error.value = err.response.data.message;
      return null;
    } finally {
      loading.value = false;
    }
  };

  return {
    get,
    post,
    data,
    error,
    loading,
  };
};

export default useFetch;
