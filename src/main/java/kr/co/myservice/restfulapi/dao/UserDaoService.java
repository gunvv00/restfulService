package kr.co.myservice.restfulapi.dao;

import kr.co.myservice.restfulapi.bean.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {

    /*
    * TODO: 이후 DB를 사용하여 조회 및 생성 하는 방식으로 수정 예정
    * */
    private static List<User> users = new ArrayList<>();

    private int usersCount = 3;

    static {
        users.add(new User(1, "Song", new Date(), "song123", "950526-1200000"));
        users.add(new User(2, "Yang", new Date(), "yang123", "951106-1200000"));
        users.add(new User(3, "Kim", new Date(), "kim123", "950621-1200000"));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        // 필수값 초기화
        if (user.getId() == null) {
            user.setId(++usersCount);
        }

        if (user.getJoinDate() == null) {
            user.setJoinDate(new Date());
        }

        users.add(user);

        return user;
    }

    public User findById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User deleteUser(int id) {
        Iterator<User> iterator = users.listIterator();

        while (iterator.hasNext()) {
            User user = iterator.next();

            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}
