package m07.entity;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name="Suppliers")
public class Supplier implements Serializable {
	@Id
	@GeneratedValue
	Integer id;
	String name;
	String logo;
	String email;
	String phone;
	
	@OneToMany(mappedBy="supplier", fetch=FetchType.EAGER)
	Collection<Product> products;

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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Collection<Product> getProducts() {
		return products;
	}

	public void setProducts(Collection<Product> products) {
		this.products = products;
	}
	
}
