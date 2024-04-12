package lk.Ijse.Model;

public class Employee {
    private String empId;
    private String empName;
    private int empAge;
    private String empEmail;
    private int empPhone;
    private String empAddress;

    public Employee() {}

    public Employee(String empId, String empName, int empAge, String empEmail, int empPhone, String empAddress) {
        this.empId = empId;
        this.empName = empName;
        this.empAge = empAge;
        this.empEmail = empEmail;
        this.empPhone = empPhone;
        this.empAddress = empAddress;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getEmpAge() {
        return empAge;
    }

    public void setEmpAge(int empAge) {
        this.empAge = empAge;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public int getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(int empPhone) {
        this.empPhone = empPhone;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    @Override
    public String toString() {
        return "EmployeeRepo{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empAge='" + empAge + '\'' +
                ", empEmail='" + empEmail + '\'' +
                ", empPhone='" + empPhone + '\'' +
                ", empAddress='" + empAddress + '\'' +
                '}';
    }
}
