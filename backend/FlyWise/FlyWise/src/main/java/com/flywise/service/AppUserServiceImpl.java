package com.flywise.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flywise.dto.AppUserDto;
import com.flywise.exception.UserException;
import com.flywise.pojos.AppUser;
import com.flywise.repository.AppUserRepository;


@Service
@Transactional
public class AppUserServiceImpl implements IAppUserService {
	
	@Autowired
	private AppUserRepository userRepo;

	@Override
	public List<AppUser> fetchAllUsers() {
		List<AppUser> appUsers = userRepo.findAll();
		return appUsers;
	}

	@Override
	public void registerUser(AppUserDto appUserDto) throws UserException {
		List<AppUser> appUsers = (List<AppUser>) fetchAllUsers();

		AppUser tempUser = null;
		for (AppUser u : appUsers) {
			if (u.getEmail().equals(appUserDto.getEmail()))
				tempUser = u;
		}
		if (tempUser == null) {

			userRepo.save(new AppUser(appUserDto.getFirstName(), appUserDto.getLastName(), appUserDto.getEmail(),
					appUserDto.getPassword(), appUserDto.getPhoneNumber(), appUserDto.getGovtId(),
					appUserDto.getGovtIdNumber(), "ROLE_USER"));

		} else {
			throw new UserException("User already exists with username " + tempUser.getEmail());
		}
	}
	

}

