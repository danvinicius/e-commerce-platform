import useFetch from "./useFetch";
import { Product, ProductRequest } from "./useProduct";

const { get } = useFetch();

const images = [
  "modelo1.png",
  "modelo2.png",
  "modelo3.png",
  "modelo4.png",
  "modelo5.png",
];

export interface Category {
  id: string;
  name: string;
  discount: number;
  products: Product[];
}

const useCategory = () => {
  const getAllCategories = async (): Promise<Category[]> => {
    const categories = await get("/category");
    return categories.map((c) => ({
      ...c,
      products: c.products.map((product: ProductRequest) => ({
        ...product,
        imageUrl: images[Math.floor(Math.random() * images.length)],
      })),
    }));
  };
  const getBestSellingProducts = async (): Promise<Product[]> => {
    const bestSellingProducts = await get('/category/best-selling');
    return bestSellingProducts.map((product: ProductRequest) => ({
      ...product,
      imageUrl: images[Math.floor(Math.random() * images.length)],
    }));
  };
  const getOnSaleProducts = async (): Promise<Product[]> => {
    const category = await get('/category/anime');
    
    return category.products.map((product: ProductRequest) => ({
      ...product,
      imageUrl: images[Math.floor(Math.random() * images.length)],
    }));
  };
  const getCategoryBySlug = async (slug: string): Promise<Category | null> => {
    const category = await get(`/category/${slug}`);
    return {
      ...category,
      products: category.products.map((product: ProductRequest) => ({
        ...product,
        imageUrl: images[Math.floor(Math.random() * images.length)],
      })),
    };
  };

  return {
    getAllCategories,
    getCategoryBySlug,
    getBestSellingProducts,
    getOnSaleProducts
  };
};

export default useCategory;
