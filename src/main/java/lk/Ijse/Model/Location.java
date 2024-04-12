package lk.Ijse.Model;

public class Location {

    private String id;
    private String province;
    private String city;
    private String Address;
    private String zipCode;


    public Location() {
    }

    public Location(String id, String province, String city, String address, String zipCode) {
        this.id = id;
        this.province = province;
        this.city = city;
        Address = address;
        this.zipCode = zipCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "LocationRepo{" +
                "id='" + id + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", Address='" + Address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
