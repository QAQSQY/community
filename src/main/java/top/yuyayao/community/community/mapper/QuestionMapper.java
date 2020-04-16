package top.yuyayao.community.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.yuyayao.community.community.model.Question;

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
}
