package m07.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="OrderDetails")
public class OrderDetail implements Serializable {

	@Id
	@GeneratedValue
	Integer id;
	//Integer orderId;
	//Integer productId;
	Double unitPrice;
	Integer quantity;
	Double discount;
	String status;
	@Column(name = "total_price")
	private double totalPrice;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@ManyToOne
	@JoinColumn(name="orderId")
	Order order;
	
	@ManyToOne
	@JoinColumn(name="productId")
	Product product;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
