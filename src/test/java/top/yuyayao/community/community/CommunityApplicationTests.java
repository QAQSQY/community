package top.yuyayao.community.community;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.yuyayao.community.community.dto.QuestionDTO;
import top.yuyayao.community.community.mapper.QuestionMapperCustom;

import java.util.List;

@SpringBootTest
class CommunityApplicationTests {

    @Autowired
    private QuestionMapperCustom questionMapperCustom;

    @Test
    void contextLoads() {
        List<QuestionDTO> questionDTOS = questionMapperCustom.pageFind();
        System.out.println(questionDTOS.get(0).getUser());
//        QuestionDTO byId = questionMapperCustom.getById(1);
    }

}
