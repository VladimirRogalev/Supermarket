package supermarket.model;

import java.time.LocalDate;
import java.util.Objects;

public class Product {
	private long barCode;
	private String name;
	private String category;
	private String brand;
	private double price;
	private LocalDate expDate;
	
	public Product(long barCode, String name, String category, String brand, double price, LocalDate expDate) {
		this.barCode = barCode;
		this.name = name;
		this.category = category;
		this.brand = brand;
		this.price = price;
		this.expDate = expDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getBarCode() {
		return barCode;
	}

	public String getName() {
		return name;
	}

	public String getCategory() {
		return category;
	}

	public String getBrand() {
		return brand;
	}

	public LocalDate getExpDate() {
		return expDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(barCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return barCode == other.barCode;
	}

	@Override
	public String toString() {
		return "Product [barCode=" + barCode + ", name=" + name + ", category=" + category + ", brand=" + brand
				+ ", price=" + price + ", expDate=" + expDate + "]";
	}
	
	
	

}
