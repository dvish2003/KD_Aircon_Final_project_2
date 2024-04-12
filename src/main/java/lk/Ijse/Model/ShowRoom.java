package lk.Ijse.Model;

public class ShowRoom {
    private String showRoomId;
    private String showRoomLocation;
    private String showRoomDescription;
    private int showRoomQtyOnHand;

    public ShowRoom() {
    }

    public ShowRoom(String showRoomLocation, String showRoomDescription, int showRoomQtyOnHand, String showRoomId) {
        this.showRoomLocation = showRoomLocation;
        this.showRoomDescription = showRoomDescription;
        this.showRoomQtyOnHand = showRoomQtyOnHand;
        this.showRoomId = showRoomId;
    }

    public String getShowRoomId() {
        return showRoomId;
    }

    public void setShowRoomId(String showRoomId) {
        this.showRoomId = showRoomId;
    }

    public String getShowRoomLocation() {
        return showRoomLocation;
    }

    public void setShowRoomLocation(String showRoomLocation) {
        this.showRoomLocation = showRoomLocation;
    }

    public String getShowRoomDescription() {
        return showRoomDescription;
    }

    public void setShowRoomDescription(String showRoomDescription) {
        this.showRoomDescription = showRoomDescription;
    }

    public int getShowRoomQtyOnHand() {
        return showRoomQtyOnHand;
    }

    public void setShowRoomQtyOnHand(int showRoomQtyOnHand) {
        this.showRoomQtyOnHand = showRoomQtyOnHand;
    }

    @Override
    public String toString() {
        return "ShowRoomRepo{" +
                "showRoomId='" + showRoomId + '\'' +
                ", showRoomLocation='" + showRoomLocation + '\'' +
                ", showRoomDescription='" + showRoomDescription + '\'' +
                ", showRoomQtyOnHand=" + showRoomQtyOnHand +
                '}';
    }
}
