package ru.elkin.egar.dao.ext;

import lombok.Data;

@Data
public class UserRoleRelation {

    private Long userId;
    private Long roleId;

}
