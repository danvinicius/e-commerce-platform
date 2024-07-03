import useFetch from "./useFetch";
import useLocalStorage from "./useLocalStorage";

const { get, post, data, error, loading } = useFetch();
const { getItem, removeItem } = useLocalStorage();

const useLogin = () => {

  const token = getItem("token");

  const isAuthenticated = async () => {
    if (!token) {
      return false;
    }
    const user = await get("/user/me", token);
    return !!user?.id;
  };

  const isAdmin = async (token: string) => {
    if (!token) {
      return false;
    }
    const user = await get("/user/me", token);
    return user.role == 'admin';
  };

  const logout = () => {
    removeItem("token");
    removeItem("email");
    removeItem("name");
  };

  const authenticate = async ({
    email,
    password,
  }: {
    email: string;
    password: string;
  }) => {
    data.value = await post("/auth", { email, password });
  };

  const create = async ({
    name,
    phone,
    email,
    password,
  }: {
    name: string;
    phone: string;
    email: string;
    password: string;
  }) => {
    data.value = await post("/user", { name, phone, email, password });
  };

  const forgotPassword = async ({ email }: { email: string }) => {
    data.value = await post("/user/forgot", { email });
  };

  return {
    authenticate,
    create,
    forgotPassword,
    logout,
    isAdmin,
    isAuthenticated,
    loading,
    data,
    error,
  };
};

export default useLogin;
