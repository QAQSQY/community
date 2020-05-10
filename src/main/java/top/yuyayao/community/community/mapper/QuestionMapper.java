package top.yuyayao.community.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import top.yuyayao.community.community.dto.QuestionDTO;
import top.yuyayao.community.community.model.Question;

import java.util.List;

@Repository
@Mapper
public interface QuestionMapper {

    @Insert("insert into question (" +
            "title," +
            "description," +
            "gmt_create," +
            "gmt_modified," +
            "creator," +
            "tag"+") values(" +
            "#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from question")
    List<Question> list();

    @Select("select q.*,u.AVATAR_URL,u.NAME from USER u,QUESTION q where u.ID=q.CREATOR")
    List<QuestionDTO> pageFind();

    @Select("select q.*,u.AVATAR_URL,u.NAME from QUESTION q,user u where q.CREATOR=u.ID")
    List<QuestionDTO> pageFindByUserId();

    @Select("select q.*,u.AVATAR_URL,u.NAME from question q,user u where q.creator=u.id and q.id=#{id}")
    QuestionDTO getById(Integer id);

    @Select("select * from question where id=#{id}")
    Question getOneByI(Integer id);

    @Update("update question set " +
            "title=#{title}," +
            "description=#{description}," +
            "gmt_modified=#{gmtModified}," +
            "tag=#{tag} where id=#{id}")
    void update(Question question);
}
