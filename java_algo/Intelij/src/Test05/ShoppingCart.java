package Test05;

public class ShoppingCart {
    private Long id;
    private Long productId;

    public ShoppingCart(){};

    public ShoppingCart(Long id, Long productId) {
        this.id = id;
        this.productId = productId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void addProductToCart(){
        // logic
    }
    public void removeProductFormCart(){
        // logic
    }
    public void checkOUt(){
        //logic
    }
}
