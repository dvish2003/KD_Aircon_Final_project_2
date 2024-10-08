package lk.Ijse.DAO.RegisterDAO;

import lk.Ijse.DAO.SqlUtil;
import lk.Ijse.Entity.Register;
import lk.Ijse.dto.RegisterDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegisterDAOImpl implements RegisterDAO {
    public  boolean save(Register register) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT INTO Register (Register_id, Register_Name, Register_post, Register_Password) VALUES(?,?,?,?)",
                register.getRegisterId(),
                register.getRegisterName(),
                register.getPost(),
                register.getRegisterPassword()
                );
    }

    public Register searchById(String id) throws SQLException, ClassNotFoundException {

        ResultSet rs = SqlUtil.execute("SELECT * FROM Register WHERE Register_id = ?",id);
        if (rs.next()) {
            String register_id = rs.getString(1);
            String name = rs.getString(2);
            String post = rs.getString(3);
            String password = rs.getString(4);

            Register register = new Register(register_id, name, post, password);
            return register;
        }
        return null;
    }

    public  boolean update(Register register) throws SQLException, ClassNotFoundException {

        return SqlUtil.execute( "UPDATE Register SET Register_Name = ?, Register_post = ?, Register_Password = ? WHERE Register_id = ?",
                register.getRegisterName(),
                register.getPost(),
                register.getRegisterPassword(),
                register.getRegisterId());

    }

    public  boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("DELETE FROM Register WHERE Register_id = ?",id);
    }

    public  List<Register> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT * FROM Register");

        List<Register> registerList = new ArrayList<>();

        while (resultSet.next()) {
            String register_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String post = resultSet.getString(3);
            String password = resultSet.getString(4);

            Register register = new Register(register_id, name, post, password);
            registerList.add(register);
        }
        return registerList;
    }

    public  List<String> getIds() throws SQLException, ClassNotFoundException {
        List<String> idList = new ArrayList<>();

        ResultSet resultSet = SqlUtil.execute("SELECT Register_id FROM Register");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }
    public  String getCurrentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet =SqlUtil.execute("SELECT Register_id FROM Register ORDER BY Register_id DESC LIMIT 1");
        if(resultSet.next()) {
            String Register_id = resultSet.getString(1);
            return Register_id;
        }
        return null;
    }
}
