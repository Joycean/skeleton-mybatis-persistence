package skeleton.persistence.persistence.dao;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;

import skeleton.persistence.model.User;
import skeleton.persistence.persistence.mapper.UserMapper;

//@Repository
public class UserDao extends BaseDao<User> implements UserMapper {

//    @Autowired
    UserMapper mapper;

}
