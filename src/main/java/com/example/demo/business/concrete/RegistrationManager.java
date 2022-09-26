package com.example.demo.business.concrete;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.business.abstracts.RegistrationService;
import com.example.demo.dataAccess.UserDao;
import com.example.demo.entities.User;

@Service
public class RegistrationManager implements RegistrationService{
	private UserDao dao;
	
	@Autowired
	public RegistrationManager(UserDao dao) {
		super();
		this.dao=dao;
	}
	
	@Override
	public void registrateUser(User user) {
		String sha256hex = get_SHA_256_SecurePassword(user.getPassword(), user.getEmail().substring(0,5));
		user.setPassword(sha256hex);
		this.dao.save(user);
	}

	@Override
	public String verification(User user) {

		if(dao.existsById(user.getId())) {
			if(dao.verifyUserEmail(user.getEmail(),get_SHA_256_SecurePassword(user.getPassword(),user.getEmail().substring(0,5)))!=null)
			{
				return "Başarılı";
			}else {
				return "Başarısız1";
			}
		}else
			return "Başarısız2";
		
	}

	@Override
	public List<User> listOfUsers() {
		return this.dao.findAll();
	}

	private static String get_SHA_256_SecurePassword(String passwordToHash,
            String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}












