package me.craftcreative.bananaz.Objects;

public class Product {

    private String title;
    private String subtitle;
    private String price;
    private String img_product;

    public Product(String title, String subtitle, String price, String img_product) {
        this.title = title;
        this.subtitle = subtitle;
        this.price = price;
        this.img_product = img_product;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg_product() {
        return img_product;
    }

    public void setImg_product(String img_product) {
        this.img_product = img_product;
    }
}
