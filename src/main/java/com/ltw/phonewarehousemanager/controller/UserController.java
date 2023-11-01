package com.ltw.phonewarehousemanager.controller;

import com.ltw.phonewarehousemanager.constant.Constants;
import com.ltw.phonewarehousemanager.dto.request.user.CreateUserRequest;
import com.ltw.phonewarehousemanager.dto.response.BaseResponse;
import com.ltw.phonewarehousemanager.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

//    @PostMapping
//    public BaseResponse createUser(@RequestHeader(value = Constants.HeaderConstant.HEADER_USER_ID) String userId,
//                                   @Valid @RequestBody CreateUserRequest request){
//        return BaseResponse.successData(userService.createUser(userId, request));
//    }
}
