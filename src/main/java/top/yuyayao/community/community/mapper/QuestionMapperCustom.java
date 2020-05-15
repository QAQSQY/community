package top.yuyayao.community.community.mapper;

import top.yuyayao.community.community.dto.QuestionDTO;
import top.yuyayao.community.community.model.Question;

import java.util.List;

public interface QuestionMapperCustom {
    List<QuestionDTO> pageFind();
    List<QuestionDTO> pageFindByUserId(Long id);
    void incView(Long id);
    void incComment(Question question);
}
