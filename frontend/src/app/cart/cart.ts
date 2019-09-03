export interface Product {
    productId: number;
    name: string;
    shortDescription: string;
    longDescription: string;
    price: number;
}

export interface Cart {
    product: Product;
    count: number;
}
