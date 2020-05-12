package top.yuyayao.community.community.mapper;

import org.apache.ibatis.annotations.Select;
import top.yuyayao.community.community.dto.QuestionDTO;

import java.util.List;

public interface QuestionMapperCustom {
    //    @Select("select q.*,u.AVATAR_URL,u.NAME from USER u,QUESTION q where u.ID=q.CREATOR")
    List<QuestionDTO> pageFind();

    //    @Select("select q.*,u.AVATAR_URL,u.NAME from QUESTION q,user u where q.CREATOR=u.ID")
//    List<QuestionDTO> getFind();

    //    @Select("select q.*,u.AVATAR_URL,u.NAME from question q,user u where q.creator=u.id and q.id=#{id}")
    List<QuestionDTO> pageFindByUserId(Integer id);
}
