package supermarket.dao;

import supermarket.model.Product;

public interface Supermarket {
	boolean addProduct (Product product);
	Product removeProduct (long barCode);
	Product findByBarCode (long barCode);
	Product[] findByCategory (String category);
	Product [] findByBrand (String brand);
	Product [] findProductsWithExpiredDate();
	int skuQuantity();
	void printProducts();

}
