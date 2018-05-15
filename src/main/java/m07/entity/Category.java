package m07.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name="categories")
public class Category implements Serializable{
	@Id
	@GeneratedValue()
	Integer idCategory;

	@NotNull(message = "vui lòng nhập")
	String nameVN;

	@NotNull(message = "vui lòng nhập")
	String name;

	@OneToMany(mappedBy="category", fetch=FetchType.EAGER)
	Collection<Product> products;

	public Integer getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Integer idCategory) {
		this.idCategory = idCategory;
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
