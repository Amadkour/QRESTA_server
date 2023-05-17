package com.education.madkouresta.madkouresta.user.dto;

import com.education.madkouresta.madkouresta.user.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Integer id;
    private String name;
    private String password;
    private String mail;
    private String phone;
    private boolean isMale;
    private boolean canEdit;
    private boolean isStudent;
    private Integer age;

    public UserDTO userToDTO(User userModel) {
        return UserDTO.builder()
                .id(userModel.getId())
                .name(userModel.getName())
                .isMale(userModel.isMale())
                .age(userModel.getAge())
                .phone(userModel.getPhone())
                .mail(userModel.getMail())
                .password(userModel.getPassword())
                .canEdit(userModel.isCanEdit())
                .isStudent(userModel.isStudent())
                .build();

    }

    public User DTOToUser(UserDTO userModel) {
        return User.builder()
                .id(userModel.getId())
                .name(userModel.getName())
                .isMale(userModel.isMale())
                .age(userModel.getAge())
                .phone(userModel.getPhone())
                .mail(userModel.getMail())
                .password(userModel.getPassword())
                .canEdit(userModel.isCanEdit())
                .isStudent(userModel.isStudent())
                .build();
    }

}
