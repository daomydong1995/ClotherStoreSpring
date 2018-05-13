package m07.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Products")
public class Product implements Serializable {
	@Id
	@GeneratedValue
	Integer id;
	String name;
	String unitBrief;
	Double unitPrice;
	String image;
	Integer view;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(style = "dd/mm/yyyy")
	Date productDate;
	String description;
	//Integer categoryId;
	//String supplierId;
	Integer quantity;
	Double discount;



	public Integer getView() {
		return view;
	}

	public void setView(Integer view) {
		this.view = view;
	}

	@ManyToOne
	@JoinColumn(name="categoryId")
	Category category;
	
	@ManyToOne
	@JoinColumn(name="supplierId")
	Supplier supplier;
	
	@OneToMany(mappedBy="product")
	Collection<OrderDetail> orderDetails;


	public Product() {
	}

	public Product(Integer id, String name, String unitBrief, Double unitPrice, String image, Date productDate, String description, Integer quantity, Double discount, Category category, Supplier supplier, Collection<OrderDetail> orderDetails) {
		this.id = id;
		this.name = name;
		this.unitBrief = unitBrief;
		this.unitPrice = unitPrice;
		this.image = image;
		this.productDate = productDate;
		this.description = description;
		this.quantity = quantity;
		this.discount = discount;
		this.category = category;
		this.supplier = supplier;
		this.orderDetails = orderDetails;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnitBrief() {
		return unitBrief;
	}

	public void setUnitBrief(String unitBrief) {
		this.unitBrief = unitBrief;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Collection<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Collection<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
}
