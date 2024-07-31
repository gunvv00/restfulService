package kr.co.myservice.restfulapi.bean;


import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@Data
// 명시된 필드에 모든 생성자 생성을 위한 Annotaion
@AllArgsConstructor
public class User {
    private Integer id;

    @Size(min = 2, message = "name값은 2글자 이상 입력해주세요.")
    private String name;
    @Past(message = "등록일은 현재 일자를 미래 날짜를 입력 하실수 없습니다.")
    private Date joinDate;
}
