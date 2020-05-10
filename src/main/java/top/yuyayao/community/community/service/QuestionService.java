package top.yuyayao.community.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yuyayao.community.community.dto.QuestionDTO;
import top.yuyayao.community.community.mapper.QuestionMapper;
import top.yuyayao.community.community.mapper.UserMapper;
import top.yuyayao.community.community.model.Question;
import top.yuyayao.community.community.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public List<QuestionDTO> list() {
        List<Question> questionList = questionMapper.list();
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questionList) {
//            User user = userMapper.findById(question.getCreator());
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
//            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        return questionDTOS;
    }

    public List<Question> findAll() {
        return questionMapper.list();
    }

    public List<QuestionDTO> pageFind() {
        return questionMapper.pageFind();
    }

    public List<QuestionDTO> pageFindByUserId() {
        return questionMapper.pageFindByUserId();
    }

    public QuestionDTO getById(Integer id) {
        QuestionDTO question = questionMapper.getById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        return questionDTO;
    }

    public void createOrupdate(Question question) {
        if(question.getId()==null){
            questionMapper.create(question);
        }else{
            questionMapper.update(question);
        }
    }
}
