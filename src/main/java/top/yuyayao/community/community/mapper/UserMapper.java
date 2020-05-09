package top.yuyayao.community.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import top.yuyayao.community.community.dto.QuestionDTO;
import top.yuyayao.community.community.model.User;

import java.util.List;

@Mapper
@Repository(value = "userMapper")
public interface UserMapper {

    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(String token);

    @Select("select * from user where id=#{creator}")
    User findById(Integer creator);

    @Select("select q.*,u.AVATAR_URL from QUESTION q,user u where q.CREATOR=u.ID")
    List<QuestionDTO> pageFind();
}
