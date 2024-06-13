export interface Product {
    id: string,
    imageUrl: string,
    quantity: Number,
    name: string,
    description: string,
    price: number,
}

export interface BannerProduct extends Product {
  title: string;
  subtitle: string;
}

export interface RecommendedProduct extends Product {
  title: string;
}

interface ProductRequest extends Omit<Product, 'imageUrl'> {
    image_url: string;
}
interface RecommendedProductRequest extends Omit<RecommendedProduct, 'imageUrl'> {
    image_url: string;
}
interface BannerProductRequest extends Omit<BannerProduct, 'imageUrl'> {
    image_url: string;
}



const recommendedProducts: RecommendedProductRequest[] = [
  {
    id: "dsfsdf-34345-dsfsdf",
    image_url: "modelo1.png",
    quantity: 8,
    name: "Camisa Cyberpunk Roxa",
    description: "Lorem ipsum dolor sit amet consectetur adipisicing elit.",
    price: 25.92,
    title: "Lorem ipsum dolor sit amet consectetur adipisicing elit.",
  },
  {
    id: "dsfsdf-34345-dsfsdf",
    image_url: "modelo2.png",
    quantity: 8,
    name: "Camisa Cyberpunk Amarela",
    description: "Lorem ipsum dolor sit amet consectetur adipisicing elit.",
    price: 25.92,
    title: "Lorem ipsum dolor sit amet consectetur adipisicing elit.",
  },
];

const bannerProduct: BannerProductRequest = {
  id: "qweqweqwe-asd",
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
    id: "sdshfghgfhd-asd",
    image_url: "modelo1.png",
    quantity: 10,
    name: "Camisa Cyberpunk Roxa",
    description: "Camisa Cyberpunk Roxa",
    price: 39.9,
  },
  {
    id: "sdscfr324d-asd",
    image_url: "modelo3.png",
    quantity: 10,
    name: "Camisa Cyberpunk Roxa",
    description: "Camisa Cyberpunk Roxa",
    price: 39.9,
  },
  {
    id: "sdujuj7ud-asd",
    image_url: "modelo4.png",
    quantity: 10,
    name: "Camisa Cyberpunk Roxa",
    description: "Camisa Cyberpunk Roxa",
    price: 39.9,
  },
  {
    id: "svnmfgasd-asd",
    image_url: "modelo1.png",
    quantity: 10,
    name: "Camisa Cyberpunk Roxa",
    description: "Camisa Cyberpunk Roxa",
    price: 39.9,
  },
  {
    id: "sd893vgdasd-asd",
    image_url: "modelo2.png",
    quantity: 10,
    name: "Camisa Cyberpunk Roxa",
    description: "Camisa Cyberpunk Roxa",
    price: 39.9,
  },
  {
    id: "sdsdsdfsaad-asd",
    image_url: "modelo3.png",
    quantity: 10,
    name: "Camisa Cyberpunk Roxa",
    description: "Camisa Cyberpunk Roxa",
    price: 39.9,
  },
  {
    id: "sdnxcei5asd-asd",
    image_url: "modelo4.png",
    quantity: 10,
    name: "Camisa Cyberpunk Roxa",
    description: "Camisa Cyberpunk Roxa",
    price: 39.9,
  },
];

export const useProduct = () => {
  const getAllProducts = async (): Promise<Product[]> => {
    return new Promise((res, rej) => {
        res(products.map((product: ProductRequest) => ({
            ...product,
            imageUrl: product.image_url
        })))
    });
  };
  const getBestSellingProducts = async (): Promise<Product[]> => {
    return new Promise((res, rej) => {
        res(products.map((product: ProductRequest) => ({
            ...product,
            imageUrl: product.image_url
        })))
    });
  };
  const getOnSaleProducts = async (): Promise<Product[]> => {
    return new Promise((res, rej) => {
        res(products.map((product: ProductRequest) => ({
            ...product,
            imageUrl: product.image_url
        })))
    });
  };
  const getBannerProduct = async (): Promise<BannerProduct> => {
    return new Promise((res, rej) => {
        res({
          ...bannerProduct,
          imageUrl: bannerProduct.image_url
        })
    });
  };
  const getRecommendedProduct = async (): Promise<RecommendedProduct[]> => {
    return new Promise((res, rej) => {
        res(recommendedProducts.map((recommendedProduct: RecommendedProductRequest) => ({
            ...recommendedProduct,
            imageUrl: recommendedProduct.image_url
        })))
    });
  };

  return {
    getAllProducts,
    getBannerProduct,
    getOnSaleProducts,
    getBestSellingProducts,
    getRecommendedProduct
  };
};
