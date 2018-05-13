package m07.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Orders")
public class Order implements Serializable {
  @Id
  @GeneratedValue
  Integer id;
  //String customerId;
  @DateTimeFormat(style = "dd/mm/yyyy")
  @Temporal(TemporalType.DATE)
  Date orderDate;
  @Temporal(TemporalType.DATE)
  @DateTimeFormat(style = "dd/mm/yyyy")
  Date requireDate;
  String receiver;
  String address;
  String description;
  Double amount;
  @Column(name = "total_price")
  private double totalPrice;

  public double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }

  @ManyToOne
  @JoinColumn(name = "customerId")
  Customer customer;

  @OneToMany(mappedBy = "order")
  Collection<OrderDetail> orderDetails;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }

  public Date getRequireDate() {
    return requireDate;
  }

  public void setRequireDate(Date requireDate) {
    this.requireDate = requireDate;
  }

  public String getReceiver() {
    return receiver;
  }

  public void setReceiver(String receiver) {
    this.receiver = receiver;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Collection<OrderDetail> getOrderDetails() {
    return orderDetails;
  }

  public void setOrderDetails(Collection<OrderDetail> orderDetails) {
    this.orderDetails = orderDetails;
  }

}
