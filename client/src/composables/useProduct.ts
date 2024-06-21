export interface Product {
  id: string;
  imageUrl: string;
  quantity: Number;
  name: string;
  description: string;
  price: number;
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

const recommendedProducts: RecommendedProductRequest[] = [
  {
    id: "123e4567-e89b-12d3-a456-426614174000",
    image_url: "modelo1.png",
    quantity: 8,
    name: "Camisa Cyberpunk Roxa",
    description: "Lorem ipsum dolor sit amet consectetur adipisicing elit.",
    price: 25.92,
    title: "Lorem ipsum dolor sit amet consectetur adipisicing elit.",
  },
  {
    id: "123e4567-e89b-12d3-a456-426614174001",
    image_url: "modelo2.png",
    quantity: 8,
    name: "Camisa Cyberpunk Amarela",
    description: "Lorem ipsum dolor sit amet consectetur adipisicing elit.",
    price: 25.92,
    title: "Lorem ipsum dolor sit amet consectetur adipisicing elit.",
  },
];

const bannerProduct: BannerProductRequest = {
  id: "123e4567-e89b-12d3-a456-426614174002",
  image_url: "modelo-banner.png",
  quantity: 8,
  name: "Camisa Cyberpunk Roxa",
  description: "Lorem ipsum dolor sit amet consectetur adipisicing elit.",
  price: 39.9,
  title: "Discover the Most Stylish Cyberpunk Tops in Market",
  subtitle:
    "Lorem ipsum dolor sit amet consectetur adipisicing elit. xcepturi delectus optio inventore eaque ipsum sit. Maxime aliquid dolorum minima modi.",
};

const products: ProductRequest[] = [
  {
    id: "123e4567-e89b-12d3-a456-426614174003",
    image_url: "modelo1.png",
    quantity: 10,
    name: "Camisa Cyberpunk Roxa",
    description: "Camisa Cyberpunk Roxa",
    price: 39.9,
  },
  {
    id: "123e4567-e89b-12d3-a456-426614174004",
    image_url: "modelo3.png",
    quantity: 10,
    name: "Camisa Cyberpunk Roxa",
    description: "Camisa Cyberpunk Roxa",
    price: 39.9,
  },
  {
    id: "123e4567-e89b-12d3-a456-426614174005",
    image_url: "modelo4.png",
    quantity: 10,
    name: "Camisa Cyberpunk Roxa",
    description: "Lorem ipsum dolor sit amet consectetur,"+
    "adipisicing elit. Qui voluptas quae recusandae itaque consequatur reiciendis,"+
    "eaque voluptate accusantium neque similique aut quam sunt eos"+
    "beatae vero laborum, adipisci saepe ducimus.",
    price: 39.9,
  },
  {
    id: "123e4567-e89b-12d3-a456-426614174006",
    image_url: "modelo1.png",
    quantity: 10,
    name: "Camisa Cyberpunk Roxa",
    description: "Camisa Cyberpunk Roxa",
    price: 39.9,
  },
  {
    id: "123e4567-e89b-12d3-a456-426614174007",
    image_url: "modelo2.png",
    quantity: 10,
    name: "Camisa Cyberpunk Roxa",
    description: "Camisa Cyberpunk Roxa",
    price: 39.9,
  },
  {
    id: "123e4567-e89b-12d3-a456-426614174008",
    image_url: "modelo3.png",
    quantity: 10,
    name: "Camisa Cyberpunk Roxa",
    description: "Camisa Cyberpunk Roxa",
    price: 39.9,
  },
  {
    id: "123e4567-e89b-12d3-a456-426614174009",
    image_url: "modelo4.png",
    quantity: 10,
    name: "Camisa Cyberpunk Roxa",
    description: "Camisa Cyberpunk Roxa",
    price: 39.9,
  },
];

const useProduct = () => {
  const getAllProducts = async (): Promise<Product[]> => {
    return new Promise((res, rej) => {
      res(
        products.map((product: ProductRequest) => ({
          ...product,
          imageUrl: product.image_url,
        }))
      );
    });
  };
  const getBestSellingProducts = async (): Promise<Product[]> => {
    return new Promise((res, rej) => {
      res(
        products.map((product: ProductRequest) => ({
          ...product,
          imageUrl: product.image_url,
        }))
      );
    });
  };
  const getOnSaleProducts = async (): Promise<Product[]> => {
    return new Promise((res, rej) => {
      res(
        products.map((product: ProductRequest) => ({
          ...product,
          imageUrl: product.image_url,
        }))
      );
    });
  };
  const getBannerProduct = async (): Promise<BannerProduct> => {
    return new Promise((res, rej) => {
      res({
        ...bannerProduct,
        imageUrl: bannerProduct.image_url,
      });
    });
  };
  const getRecommendedProduct = async (): Promise<RecommendedProduct[]> => {
    return new Promise((res, rej) => {
      res(
        recommendedProducts.map(
          (recommendedProduct: RecommendedProductRequest) => ({
            ...recommendedProduct,
            imageUrl: recommendedProduct.image_url,
          })
        )
      );
    });
  };
  const getProductBySlug = async (slug: string): Promise<Product | null> => {
    const uuidPattern =
      /[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}/;
    const match = slug.match(uuidPattern);
    if (match) {
      const uuid = match[0];
      
      const product = [...products, ...recommendedProducts, bannerProduct].find(
        (product: ProductRequest) => product.id == uuid
      );

      if (product) {
        return new Promise((res, rej) => {
          res({
            ...product,
            imageUrl: product.image_url,
          });
        });
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