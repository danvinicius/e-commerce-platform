export const useHelpers = () => {
  const getimageUrl = (name: string) => {
    return new URL(`../assets/img/${name}`, import.meta.url).href;
  };

  const slugify = (str: string) => {
    str = str.replace(/^\s+|\s+$/g, ""); // trim leading/trailing white space
    str = str.toLowerCase(); // convert string to lowercase
    str = str
      .replace(/[^a-z0-9 -]/g, "") // remove any non-alphanumeric characters
      .replace(/\s+/g, "-") // replace spaces with hyphens
      .replace(/-+/g, "-"); // remove consecutive hyphens
    return str;
  };

  return {
    getimageUrl,
    slugify
  };
};
