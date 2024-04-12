package lk.Ijse.Model;

import java.util.Date;

public class Order {
    private String orderId;
  //  public CustomerRepo id;
  //  public PaymentRepo paymentId;
    private String status;
    private Date OrderPlaceDate;
    private Date handOverdate;

    public Order() {
    }

    public Order(String orderId, String status, Date orderPlaceDate, Date handOverdate) {
        this.orderId = orderId;
       // this.id = id;
       // this.paymentId = paymentId;
        this.status = status;
        this.OrderPlaceDate = orderPlaceDate;
        this.handOverdate = handOverdate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOrderPlaceDate() {
        return OrderPlaceDate;
    }

    public void setOrderPlaceDate(Date orderPlaceDate) {
        OrderPlaceDate = orderPlaceDate;
    }

    public Date getHandOverdate() {
        return handOverdate;
    }

    public void setHandOverdate(Date handOverdate) {
        this.handOverdate = handOverdate;
    }

    @Override
    public String toString() {
        return "OrderRepo{" +
                "orderId='" + orderId + '\'' +
                ", status='" + status + '\'' +
                ", OrderPlaceDate=" + OrderPlaceDate +
                ", handOverdate=" + handOverdate +
                '}';
    }



}
