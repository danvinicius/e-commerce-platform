const useLocalStorage = () => {
  const getItem = (key: string) => {
    const item = localStorage.getItem(key);
    if (item) return item
    return null;
  };

  const setItem = (key: string, data: any) => {
    localStorage.setItem(key, data);
  };

  const removeItem = (key: string) => {
    localStorage.removeItem(key);
  };

  return {
    getItem,
    setItem,
    removeItem,
  };
};

export default useLocalStorage;
