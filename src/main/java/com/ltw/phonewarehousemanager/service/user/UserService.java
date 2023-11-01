package com.ltw.phonewarehousemanager.service.user;

import com.ltw.phonewarehousemanager.constant.Constants;
import com.ltw.phonewarehousemanager.dto.entity.UserDTO;
import com.ltw.phonewarehousemanager.dto.model.User;
import com.ltw.phonewarehousemanager.dto.request.user.CreateUserRequest;
import com.ltw.phonewarehousemanager.exception.BaseValidateException;
import com.ltw.phonewarehousemanager.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
//    public UserDTO createUser(String userId, CreateUserRequest request){
//        try{
//            User user = modelMapper.map(request, User.class);
////            user.setId(request.getId());
//            user.setCreatedBy(userId);
//            user.setCreatedTime(new Timestamp(System.currentTimeMillis()));
//            return modelMapper.map(userRepository.saveAndFlush(user), UserDTO.class);
//        }catch (DataIntegrityViolationException e) {
//            log.error("Lỗi tạo user: ", e);
//            throw new BaseValidateException("Có lỗi xảy ra trong quá trình tạo người dùng!");
//
//        }
//    }

//    public UserDTO createUser(String userId, CreateUserRequest request){
//        User user = User.builder()
//                .id(request.getId())
//                .email(request.getEmail())
//                .address(request.getAddress())
//                .createdBy(userId)
//                .createdTime()
//                .build()
//    }
}
