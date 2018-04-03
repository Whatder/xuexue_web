package service;

import model.Admin;

public interface AdminService {
    Admin getAdminByAccount(String account);

    Admin getAdminById(int id);

    Boolean changePwd(String password, int id);
}
