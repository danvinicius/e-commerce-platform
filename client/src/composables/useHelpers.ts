const useHelpers = () => {
  const getImageUrl = (name: string) => {
    return new URL(`../assets/img/${name}`, import.meta.url).href;
  };

  const getIconUrl = (name: string) => {
    return new URL(`../assets/icon/${name}`, import.meta.url).href;
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

  const priceFormatter = (price: number, withStyle: boolean, locale = 'pt-br') => {
    const options: Intl.NumberFormatOptions = {
      currency: 'BRL',
      maximumFractionDigits: 2,
      minimumFractionDigits: 2
    }
    if (withStyle) options.style = 'currency';
    const formattedPrice = price.toLocaleString(locale, options)
    return formattedPrice;
  }

  return {
    getImageUrl,
    slugify,
    priceFormatter,
    getIconUrl
  };
};

export default useHelpers;
