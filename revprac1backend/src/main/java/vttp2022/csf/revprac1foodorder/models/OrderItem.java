package vttp2022.csf.revprac1foodorder.models;

public class OrderItem {

    private String item;
    private String quantity;
    
    public String getItem() {
        return item;
    }
    public void setItem(String item) {
        this.item = item;
    }
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem [item=" + item + ", quantity=" + quantity + "]";
    }

}
