package top.yuyayao.community.community.model;

import lombok.Data;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;

@Data
@ToString
public class Question {

    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
}
