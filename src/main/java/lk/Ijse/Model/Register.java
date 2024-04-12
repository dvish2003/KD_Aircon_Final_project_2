package lk.Ijse.Model;

public class Register {
    private String registerId;
    private String registerName;
    private String registerPassword;

    public Register(String registerId) {
        this.registerId = registerId;
    }

    public Register(String registerId, String registerName, String registerPassword) {
        this.registerId = registerId;
        this.registerName = registerName;
        this.registerPassword = registerPassword;
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }

    public String getRegisterPassword() {
        return registerPassword;
    }

    public void setRegisterPassword(String registerPassword) {
        this.registerPassword = registerPassword;
    }

    @Override
    public String toString() {
        return "RegisterRepo{" +
                "registerId='" + registerId + '\'' +
                ", registerName='" + registerName + '\'' +
                ", registerPassword='" + registerPassword + '\'' +
                '}';
    }
}

