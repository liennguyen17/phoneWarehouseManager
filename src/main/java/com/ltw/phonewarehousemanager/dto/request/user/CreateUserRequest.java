package com.ltw.phonewarehousemanager.dto.request.user;

import com.ltw.phonewarehousemanager.domain.validator.name.NameAnnotation;
import com.ltw.phonewarehousemanager.domain.validator.password.PasswordAnnotation;
import com.ltw.phonewarehousemanager.domain.validator.phone.PhoneAnnotation;
import com.ltw.phonewarehousemanager.domain.validator.username.UsernameAnnotation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
public class CreateUserRequest {
    private Long id;
    @NotBlank(message = "User name nguời dùng không được để trống!")
    @Size(min = 6, max = 20, message = "User name người dùng phải có ít nhất 6, nhiều nhất 100 kí tự!")
    @UsernameAnnotation(message = "Username không được chứa khoảng trắng hoặc các ký tự đặc biệt!")
    private String username;
    @NotBlank(message = "Tên nguời dùng không được để trống!")
    @Size(min = 6, max = 100, message = "Tên người dùng phải có ít nhất 6, nhiều nhất 100 kí tự!")
    @NameAnnotation
    private String name;
    @NotBlank(message = "Mật khẩu không được để trống!")
    @Size(min = 6, max = 20, message = "Mật khẩu phải có ít nhất 6 ký tự, nhiều nhất 100 ký tự!")
    @PasswordAnnotation
    private String password;
    @NotBlank(message = "Địa chỉ không được để trống!")
    private String address;
    @NotBlank(message = "Email không được để trống!")
    @Email(message = "Email không hợp lệ!")
    private String email;
    @NotBlank(message = "Số điện thoại không được để trống!")
    @PhoneAnnotation
    private String phone;
    @NotBlank(message = "Vai trò không được để trống!")
    private String role;
}
