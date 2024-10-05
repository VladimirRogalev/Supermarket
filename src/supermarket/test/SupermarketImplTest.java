package supermarket.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import supermarket.dao.Supermarket;
import supermarket.dao.SupermarketImpl;
import supermarket.model.Product;

class SupermarketImplTest {
	Supermarket supermarket;
	Product[] products = new Product[7];
	LocalDate date = LocalDate.now();
	Comparator<Product> comparator = (o1, o2) -> o1.getBrand().compareTo(o2.getBrand());

	@BeforeEach
	void setUp() throws Exception {
		supermarket = new SupermarketImpl();
		products[0] = new Product(48795674867L, "Milkiway", "Chocolate", "Nestle", 15.60, date.plusDays(6));
		products[1] = new Product(566145866654L, "Coca-Cola", "Drinks", "Coca-Cola", 7.90, date.plusDays(8));
		products[2] = new Product(5114655035L, "Sprite", "Drinks", "Coca-Cola", 7.60, date.minusDays(3));
		products[3] = new Product(78795448626L, "Milk", "Milk products", "Tnuva", 7.11, date.plusDays(4));
		products[4] = new Product(78525622566L, "Cream", "Milk products", "Shtraus", 11.50, date.minusDays(7));
		products[5] = new Product(85245696712L, "Ritter Sport", "Chocolate", "Ritter Sport", 7.80, date.plusDays(18));
		products[6] = new Product(1456445656L, "Cereal", "Breakfasts", "Nestle", 26.50, date.plusDays(145));
		for (int i = 0; i < products.length - 1; i++) {
			supermarket.addProduct(products[i]);

		}
	}

	@Test
	void testAddProduct() {
		assertFalse(supermarket.addProduct(null));
		assertTrue(supermarket.addProduct(products[6]));
		assertEquals(7, supermarket.skuQuantity());
		assertFalse(supermarket.addProduct(products[6]));
		assertEquals(7, supermarket.skuQuantity());
	}

	@Test
	void testRemoveProduct() {
		assertEquals(products[1], supermarket.removeProduct(566145866654L));
		assertNull(supermarket.removeProduct(566145866654L));
	}

	@Test
	void testFindByBarCode() {
		assertEquals(products[4], supermarket.findByBarCode(78525622566L));
		assertNull(supermarket.findByBarCode(1456445656L));

	}

	@Test
	void testFindByCategory() {
		Product[] expected = { products[0], products[5] };
		Product[] actual = supermarket.findByCategory("Chocolate");
		assertArrayEquals(expected, actual);
	}

	@Test
	void testFindByBrand() {
		Product[] expected = { products[2], products[1] };
//		Arrays.sort(expected,comparator);
		Product[] actual = supermarket.findByBrand("Coca-Cola");
//		Arrays.sort(actual,comparator);
		assertArrayEquals(expected, actual);
	}

	@Test
	void testFindProductsWithExpiredDate() {
		Product[] expected = { products[2], products[4] };
		Product[] actual = supermarket.findProductsWithExpiredDate();
		assertArrayEquals(expected, actual);
		System.out.println("Products with experity date: ");
		for (int i = 0; i < actual.length; i++) {
			System.out.println(actual[i]);
		}

	}

	@Test
	void testSkuQuantity() {
		assertEquals(6, supermarket.skuQuantity());
	}

	@Test
	void testPrintProducts() {
		System.out.println("All products:");
		supermarket.printProducts();

	}
}
