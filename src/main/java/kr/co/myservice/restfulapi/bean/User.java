package kr.co.myservice.restfulapi.bean;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@Data
// 명시된 필드에 모든 생성자 생성을 위한 Annotaion
@AllArgsConstructor
@JsonIgnoreProperties(value = {"password", "ssn"})
@Schema(description = "사용자 상세 정보를 위한 도메인 객체")
public class User {

    @Schema(title = "사용자 ID", description = "자동 생성 되므로 입력 X")
    private Integer id;

    @Schema(title = "사용자 성함", description = "사용자 이름을 입력합니다.")
    @Size(min = 2, message = "name값은 2글자 이상 입력해주세요.")
    private String name;

    @Schema(title = "등록 일자", description = "사용자의 등록 일자를 입력해주세요.")
    @Past(message = "등록일은 현재 일자를 미래 날짜를 입력 하실수 없습니다.")
    private Date joinDate;

//    @JsonIgnore
    @Schema(title = "사용자 비밀번호", description = "사용하실 비밀번호를 입력해주세요.")
    private String password;

    // 주민번호
//    @JsonIgnore
    @Schema(title = "사용자 주민번호", description = "사용자의 주민번호를 입력해주세요. ")
    private String ssn;
}
