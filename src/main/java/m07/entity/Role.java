package m07.entity;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue
    Integer id;
    String role;

    @ManyToOne
    @JoinColumn(name="customerId")
    Customer customer;

    public void setRole(String role) {
        this.role = role;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }


}
