export const useHelpers = () => {
  const getImageUrl = (name: string) => {
    return new URL(`../assets/img/${name}`, import.meta.url).href;
  };

  return {
    getImageUrl,
  }
};
