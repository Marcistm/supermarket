package com.example.demo.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tbl_bill")
public class Bill {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private  String billCode;
    private String productName;
    private String productDesc;
    private float price;
}
