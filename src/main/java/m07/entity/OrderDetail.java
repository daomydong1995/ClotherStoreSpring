package m07.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="orderDetails")
public class OrderDetail implements Serializable {

	@Id
	@GeneratedValue()
	Integer idOrderDetails;
	//Integer orderId;
	//Integer idProduct;
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
	@JoinColumn(name="idOrder")
	Order order;
	
	@ManyToOne
	@JoinColumn(name="idProduct")
	Product product;


	public Integer getIdOrderDetails() {
		return idOrderDetails;
	}

	public void setIdOrderDetails(Integer idOrderDetails) {
		this.idOrderDetails = idOrderDetails;
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
