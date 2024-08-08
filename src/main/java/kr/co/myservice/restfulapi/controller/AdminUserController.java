package kr.co.myservice.restfulapi.controller;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.util.SimpleBeanPropertyDefinition;
import jakarta.validation.Valid;
import kr.co.myservice.restfulapi.bean.AdminUser;
import kr.co.myservice.restfulapi.bean.AdminUserV2;
import kr.co.myservice.restfulapi.bean.User;
import kr.co.myservice.restfulapi.dao.UserDaoService;
import kr.co.myservice.restfulapi.exception.UserNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUserController {
    private UserDaoService service;

    public AdminUserController(UserDaoService service) {
        this.service = service;
    }

    @GetMapping(path = "/users")
    public MappingJacksonValue findAllByAdmin() {
        List<User> users = service.findAll();

        List<AdminUser> adminUsers = new ArrayList<>();
        AdminUser adminUser = null;
        for (User user : users) {
            adminUser = new AdminUser();
            BeanUtils.copyProperties(user, adminUser);

            adminUsers.add(adminUser);
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUsers);
        mapping.setFilters(filters);

        return mapping;
    }
    // 버전관리 : uri 사용
    // @GetMapping(path = "/v1/users/{id}")
    // 버전관리 : query parameter사용
    // @GetMapping(path = "/users/{id}", params = "version=1")
    // 버전관리 : request header 사용
    // @GetMapping(path = "/users/{id}", headers = "X-API-VERSION=1")
    // 버전관리 : mime type 사용
    @GetMapping(path = "/users/{id}", produces = "application/vnd.company.appv1+json")
    public MappingJacksonValue findByIdUserByAdmin(@PathVariable int id) {
        User user = service.findById(id);

        AdminUser adminUser = new AdminUser();

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] Not Found", id));
        } else {
            BeanUtils.copyProperties(user, adminUser);
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filters);

        return mapping;
    }

//    @GetMapping(path = "/v2/users/{id}")
//    @GetMapping(path = "/users/{id}", params = "version=1")
//    @GetMapping(path = "/users/{id}", headers = "X-API-VERSION=1")
    @GetMapping(path = "/users/{id}", produces = "application/vnd.company.appv2+json")
    public MappingJacksonValue findByIdUserByAdminV2(@PathVariable int id) {
        User user = service.findById(id);

        AdminUserV2 adminUser = new AdminUserV2();

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] Not Found", id));
        } else {
            BeanUtils.copyProperties(user, adminUser);
            adminUser.setGrade("VIP");

        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "grade");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filters);

        return mapping;
    }

}
