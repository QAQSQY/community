package top.yuyayao.community.community.dto;

import lombok.Data;
import top.yuyayao.community.community.model.User;

@Data
public class QuestionDTO {
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
    private String avatarUrl;//头像的url
}
