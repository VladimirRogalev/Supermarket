package supermarket.dao;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import supermarket.model.Product;

public class SupermarketImpl implements Supermarket {

	private static final int INITIAL_CAPACITY = 10;
	Product[] products;
	int skuQuantity;
	private Comparator<Product> comparator = (o1, o2) -> {
		return Long.compare(o1.getBarCode(), o2.getBarCode());
//		int res = o1.getCategory().compareTo(o2.getCategory());
//		 res = o1.getExpDate().compareTo(o2.getExpDate());
//		res = res == 0 ? o1.getBrand().compareTo(o2.getBrand()) : res;
//		return o1.getExpDate().compareTo(o2.getExpDate());
//		return (int) (res == 0 ? o1.getBarCode() - o2.getBarCode() : res);
	};

	public SupermarketImpl() {
		products = new Product[INITIAL_CAPACITY];

	}

	@Override
	public boolean addProduct(Product product) {
		if (product == null || findByBarCode(product.getBarCode()) != null || skuQuantity == products.length) {
			return false;
		}
//		int index = -Arrays.binarySearch(products, 0, skuQuantity, product, comparator) - 1;
//		System.arraycopy(products, index, products, index + 1, skuQuantity++ - index);
//		products[index] = product;
		products[skuQuantity++]=product;

		return true;
	}

	@Override
	public Product removeProduct(long barCode) {

		for (int i = 0; i < skuQuantity; i++) {
			if (barCode == products[i].getBarCode()) {
				Product victim = products[i];
				products[i] = products[--skuQuantity];
				return victim;
			}

		}
		return null;
	}

	@Override
	public Product findByBarCode(long barCode) {
		for (int i = 0; i < skuQuantity; i++) {
			if (products[i].getBarCode() == barCode) {
				return products[i];
			}
		}
		return null;
	}

	@Override
	public Product[] findByCategory(String category) {
//		Product pattern = new Product(Long.MIN_VALUE, null, category, null, Double.MIN_VALUE, null);
//		int from = -Arrays.binarySearch(products, 0, skuQuantity, pattern, comparator) - 1;
//		pattern = new Product(Long.MAX_VALUE, null, category, null, Double.MAX_VALUE, null);
//		int to = -Arrays.binarySearch(products, from, skuQuantity, pattern, comparator) - 1;
//		return Arrays.copyOfRange(products, from, to);
		return findProductByPredicate(c -> category.equals(c.getCategory()));
	}

	@Override
	public Product[] findByBrand(String brand) {
		return findProductByPredicate(c -> brand.equals(c.getBrand()));

	}

	@Override
	public Product[] findProductsWithExpiredDate() {
		LocalDate dateNow= LocalDate.now();
		return findProductByPredicate(c -> c.getExpDate().isBefore(dateNow));
		// TODO Auto-generated method stub
	}

	@Override
	public int skuQuantity() {

		return skuQuantity;
	}

//	private int searchByBarCode(long barCode) {
//		for (int i = 0; i < skuQuantity; i++) {
//			if (products[i] != null && barCode == products[i].getBarCode()) {
//				return i;
//
//			}
//		}
//		return -1;
//	}
	private Product[] findProductByPredicate(Predicate<Product> predicate) {
		int count = 0;
		for (int i = 0; i < skuQuantity; i++) {
			if (predicate.test(products[i])) {
				count++;
			}

		}
		Product[] res = new Product[count];
		for (int i = 0, j = 0; j < res.length; i++) {
			if (predicate.test(products[i])) {
				res[j++] = products[i];
			}
		}
		return res;

	}

	@Override
	public void printProducts() {
		for (int i = 0; i < skuQuantity; i++) {
			System.out.println(products[i]);
		}
	
		
	}
}
