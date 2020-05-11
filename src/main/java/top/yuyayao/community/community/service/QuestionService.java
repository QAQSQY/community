package top.yuyayao.community.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import top.yuyayao.community.community.dto.QuestionDTO;
import top.yuyayao.community.community.mapper.QuestionMapper;
import top.yuyayao.community.community.mapper.UserMapper;
import top.yuyayao.community.community.model.Question;
import top.yuyayao.community.community.model.QuestionExample;
import top.yuyayao.community.community.model.User;
import top.yuyayao.community.community.model.UserExample;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

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
        List<QuestionDTO> questionDTOList = findall();;
        return questionDTOList;
    }

    public List<QuestionDTO> pageFindByUserId() {
        List<QuestionDTO> questionDTOList = findall();;
        return questionDTOList;
    }

    private List<QuestionDTO> findall() {
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        List<Question> questionList = questionMapper.selectByExample(new QuestionExample());
        for (Question question : questionList){
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrupdate(Question question) {
        if(question.getId()==null){
            questionMapper.insertSelective(question);
        }else{
            questionMapper.updateByPrimaryKeySelective(question);
        }
    }
}
