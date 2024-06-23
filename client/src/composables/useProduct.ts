import useFetch from "./useFetch";

const {get} = useFetch()

export interface Product {
  id: string;
  imageUrl: string;
  quantity: number;
  name: string;
  description: string;
  price: number;
  recommendedOnMainPage?: boolean;
  productSizes: {
    size: string;
  }[]
}

export interface BannerProduct extends Product {
  title: string;
  subtitle: string;
}

export interface RecommendedProduct extends Product {
  title: string;
}

interface ProductRequest extends Omit<Product, "imageUrl"> {
  image_url: string;
}
interface RecommendedProductRequest
  extends Omit<RecommendedProduct, "imageUrl"> {
  image_url: string;
}
interface BannerProductRequest extends Omit<BannerProduct, "imageUrl"> {
  image_url: string;
}

const images = ["modelo1.png", "modelo2.png", "modelo3.png", "modelo4.png", "modelo5.png"];


const useProduct = () => {
  const getAllProducts = async (): Promise<Product[]> => {
    const products = await get('/product');
    return products.map((product: ProductRequest) => ({
      ...product,
      imageUrl: images[Math.floor(Math.random() * images.length)],
    }))
  };
  const getBestSellingProducts = async (): Promise<Product[]> => {
    const bestSellingProducts = await get('/product/best-selling');
    return bestSellingProducts.map((product: ProductRequest) => ({
      ...product,
      imageUrl: images[Math.floor(Math.random() * images.length)],
    }));
  };
  const getOnSaleProducts = async (): Promise<Product[]> => {
    const category = await get('/category/6ff4a8d9-c1da-44e6-8bae-1b035b5d1269');
    return category.products.map((product: ProductRequest) => ({
      ...product,
      imageUrl: images[Math.floor(Math.random() * images.length)],
    }));
  };
  const getBannerProduct = async (): Promise<BannerProduct> => {
    const bannerProduct = await get('/product/7968223a-9415-47e7-8f7b-adfb271b7cda');
    return {
      ...bannerProduct,
      title: "Discover the Most Stylish Cyberpunk Tops in Market",
      subtitle: "Lorem ipsum dolor sit amet consectetur adipisicing elit. xcepturi delectus optio inventore eaque ipsum sit. Maxime aliquid dolorum minima modi.",
      imageUrl: images[Math.floor(Math.random() * images.length)],
    }
  };
  const getRecommendedProduct = async (): Promise<RecommendedProduct[]> => {
    const products = await get('/product');
    return products.filter((product: Product) => product.recommendedOnMainPage).map((product: ProductRequest) => ({
      ...product,
      title: "Lorem ipsum dolor sit amet consectetur adipisicing elit.",
      imageUrl: images[Math.floor(Math.random() * images.length)],
    }))
  };
  const getProductBySlug = async (slug: string): Promise<Product | null> => {
    const uuidPattern =
      /[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}/;
    const match = slug.match(uuidPattern);
    if (match) {
      const uuid = match[0];

      const product = await get(`/product/${uuid}`);
      return {
        ...product,
        imageUrl: images[Math.floor(Math.random() * images.length)],
      }
    }

    return null;
  };

  return {
    getAllProducts,
    getBannerProduct,
    getOnSaleProducts,
    getBestSellingProducts,
    getRecommendedProduct,
    getProductBySlug,
  };
};

export default useProduct;