import useFetch from "./useFetch";

const {post} = useFetch()

const useLogin = () => {
  const authenticate = async ({email, password}: {email: string, password: string}) => {
    try {
      const data = await post('/auth', {email, password});
      return data;
    } catch (error) {
      return null;
    }
  };

  const create = async ({name, phone, email, password}: {name: string, phone: string, email: string, password: string}) => {
    try {
      const data = await post('/user', {name, phone, email, password});
      return data;
    } catch (error) {
      return null;
    }
  };

  const forgotPassword = async ({email}: {email: string}) => {
    try {
      const data = await post('/user/forgot', {email});
      return data;
    } catch (error) {
      return null;
    }
  };

  return {
    authenticate,
    create,
    forgotPassword
  };
};

export default useLogin;
