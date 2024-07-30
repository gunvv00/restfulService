package kr.co.myservice.restfulapi.bean;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
// 명시된 필드에 모든 생성자 생성을 위한 Annotaion
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private Date joinDate;
}
