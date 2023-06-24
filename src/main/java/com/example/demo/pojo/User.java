package com.example.demo.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tbl_user")
public class User {
    @TableId(type =IdType.AUTO)
    private Integer id;
    private String name;
    private String password;
    private Integer gender; //0 女 1 男
    private String salt;
    @JSONField(format="yyyy/MM/dd")
    private Date birth; //生日
    @TableField(exist = false)
    List<Role> roles;


    public User(Integer id, String name, String password, Integer gender, String salt) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.salt = salt;
        this.birth = new Date();
    }
}
