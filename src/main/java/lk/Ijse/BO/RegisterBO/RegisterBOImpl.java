package lk.Ijse.BO.RegisterBO;

import lk.Ijse.DAO.RegisterDAO.RegisterDAO;
import lk.Ijse.DAO.RegisterDAO.RegisterDAOImpl;
import lk.Ijse.Entity.Register;
import lk.Ijse.dto.RegisterDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegisterBOImpl implements RegisterBO {

    RegisterDAO registerDAO = new RegisterDAOImpl();

    @Override
    public boolean save(RegisterDTO registerDTO) throws SQLException, ClassNotFoundException {
    return registerDAO.save(new Register(  registerDTO.getRegisterId(),
        registerDTO.getRegisterName(),
        registerDTO.getPost(),
        registerDTO.getRegisterPassword()));
    }

    @Override
    public Register searchById(String id) throws SQLException, ClassNotFoundException {
return registerDAO.searchById(id);    }

    @Override
    public boolean update(RegisterDTO registerDTO) throws SQLException, ClassNotFoundException {
        return registerDAO.update(new Register(

                registerDTO.getRegisterName(),
                registerDTO.getPost(),
                registerDTO.getRegisterPassword(),
                registerDTO.getRegisterId()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return registerDAO.delete(id);
    }

    @Override
    public List<RegisterDTO> getAll() throws SQLException, ClassNotFoundException {
List<Register> registers = registerDAO.getAll();
List<RegisterDTO> registerDTOS = new ArrayList<>();
for (Register r : registers) {
    registerDTOS.add(new RegisterDTO(r.getRegisterId(),r.getRegisterName(),r.getPost(),r.getRegisterPassword()));

}
return registerDTOS;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {

        List<String> ids = registerDAO.getIds();
        List<String> list = new ArrayList<>();
        for (String id : ids) {
            list.add(id);
        }
        return list;   }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
return registerDAO.getCurrentId();
    }
}
