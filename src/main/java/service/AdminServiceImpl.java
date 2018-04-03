package service;

import dao.AdminMapper;
import model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    public Admin getAdminByAccount(String account) {
        return adminMapper.getAdminByAccount(account);
    }

    public Admin getAdminById(int id) {
        return adminMapper.getAdminById(id);
    }

    public Boolean changePwd(String password, int id) {
        return adminMapper.changePwd(password, id);
    }
}
