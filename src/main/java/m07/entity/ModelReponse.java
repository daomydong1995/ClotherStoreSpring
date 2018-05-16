package m07.entity;


import java.util.List;

public class ModelReponse {
  Object data;
  int total;

  public ModelReponse() {
  }

  public ModelReponse(Object data, int total) {
    this.data = data;
    this.total = total;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }
}
