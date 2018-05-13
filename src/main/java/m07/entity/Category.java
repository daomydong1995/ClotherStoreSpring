package m07.entity;



import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Categories")
public class Category implements Serializable{
	@Id
	@GeneratedValue
	Integer id;

	@NotNull(message = "vui lòng nhập")
	String nameVN;

	@NotNull(message = "vui lòng nhập")
	String name;

	@OneToMany(mappedBy="category", fetch=FetchType.EAGER)
	Collection<Product> products;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameVN() {
		return nameVN;
	}

	public void setNameVN(String nameVN) {
		this.nameVN = nameVN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Product> getProducts() {
		return products;
	}

	public void setProducts(Collection<Product> products) {
		this.products = products;
	}

}
