package pl.edu.wszib.pracadyplomowa.dto;

public class ProductListMemberDto {

    Long id;
    String name;
    String icon;
    double price;
    int availability;


    public ProductListMemberDto() {
    }

/*konstruktor dodany na potrzeby7 testowania*/
    public ProductListMemberDto(Long id, String name, String icon, double price, int availability) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.price = price;
        this.availability = availability;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
