package com.xuanluo.myshop.commons.domain;

import com.xuanluo.myshop.commons.model.AbstractBaseDomain;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "tb_user")
public class TbUser extends AbstractBaseDomain implements Serializable {

    private static final long serialVersionUID = 7326545270776403608L;
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码，加密存储
     */
    private String password;

    /**
     * 注册手机号
     */
    @NotBlank(message = "手机号不能为空")
    private String phone;

    /**
     * 注册邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    private String email;


}