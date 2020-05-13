package top.yuyayao.community.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yuyayao.community.community.dto.QuestionDTO;
import top.yuyayao.community.community.exception.CustomizeException;
import top.yuyayao.community.community.exception.CustomizedErrorCode;
import top.yuyayao.community.community.mapper.QuestionMapper;
import top.yuyayao.community.community.mapper.QuestionMapperCustom;
import top.yuyayao.community.community.mapper.UserMapper;
import top.yuyayao.community.community.model.Question;
import top.yuyayao.community.community.model.QuestionExample;
import top.yuyayao.community.community.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapperCustom questionMapperCustom;

    public List<QuestionDTO> list() {
        List<Question> questionList = questionMapper.selectByExample(new QuestionExample());
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);

            questionDTOS.add(questionDTO);
        }
        return questionDTOS;
    }

    public List<Question> findAll() {
        return questionMapper.selectByExample(new QuestionExample());
    }

    public List<QuestionDTO> pageFind() {
        List<QuestionDTO> questionDTOS = questionMapperCustom.pageFind();
        return questionDTOS;
    }

    public List<QuestionDTO> pageFindByUserId(Integer id) {
        List<QuestionDTO> questionDTOS = questionMapperCustom.pageFindByUserId(id);
        return questionDTOS;
    }


    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomizeException(CustomizedErrorCode.QUESTION_NOT_FOUND);
        }
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrupdate(Question question) {
        if (question.getId() == null) {
            questionMapper.insertSelective(question);
        } else {
            int update = questionMapper.updateByPrimaryKeySelective(question);
            if (update != 1) {
                throw new CustomizeException(CustomizedErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    /**
     * 累加阅读数
     *
     * @param id
     */
    public void incView(Integer id) {
        questionMapperCustom.incView(id);
    }
}