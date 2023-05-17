package com.geekster.Restaurant.management.service.services;

import com.geekster.Restaurant.management.service.dto.SignInInputData;
import com.geekster.Restaurant.management.service.dto.SignInOutputData;
import com.geekster.Restaurant.management.service.dto.SignUpInputData;
import com.geekster.Restaurant.management.service.dto.SignUpOutputData;
import com.geekster.Restaurant.management.service.models.Authentication;
import com.geekster.Restaurant.management.service.models.Customer;
import com.geekster.Restaurant.management.service.repository.IAuthenticationRepository;

import com.geekster.Restaurant.management.service.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class customerService {
    @Autowired
    ICustomerRepository icustomerRepository;
    @Autowired
    IAuthenticationRepository iAuthenticationRepository;

    public SignUpOutputData signup(SignUpInputData signUpInputData) {

        Customer customerObj = icustomerRepository.findFirstByCustomerEmailId(signUpInputData.getUserEmailId());

        if(customerObj !=null){
            throw new IllegalStateException("Email allready Exist!!");
        }
        String encryptedPassword;
        try {
            encryptedPassword = encryptPassword(signUpInputData.getUserPassword());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        Customer customer = new Customer(signUpInputData.getUserEmailId() , encryptedPassword , signUpInputData.getUserFirstName() , signUpInputData.getUserLastName() , signUpInputData.getUserAddress() , signUpInputData.getUserMobileNumber());
        icustomerRepository.save(customer);

        Authentication authentication = new Authentication(customer);
        iAuthenticationRepository.save(authentication);
       // icustomerRepository.save(customer);
        return new SignUpOutputData("SignUp Successfully Created" ,"Now You can SignIn");

    }

    private String encryptPassword(String userPassword) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(userPassword.getBytes());
            byte[] password = md5.digest();
            String stPassword = new String(password);
            return stPassword;

    }

    public SignInOutputData signin(SignInInputData signInInputData) {
        Customer customerObj = icustomerRepository.findFirstByCustomerEmailId(signInInputData.getUserEmailId());
        if(customerObj ==null){
            throw new IllegalStateException("Sorry!! Email Not  Found!!");
        }
        String encryptedPassword;
        try {
            encryptedPassword = encryptPassword(signInInputData.getPassword());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        if(!encryptedPassword.equals(customerObj.getCustomerPassword())){
            throw new IllegalStateException("Sorry!! Wrong password!!");
        }
       // Authentication authentication = new Authentication(customerObj);
//        Authentication authentication = new Authentication(customerObj);
//        iAuthenticationRepository.save(authentication);

        Authentication authenticationObj = iAuthenticationRepository.findBycustomer(customerObj);

        return new SignInOutputData("Congratulation SignIn Successfull!!",authenticationObj.getAuthenticationToken());
    }
}
