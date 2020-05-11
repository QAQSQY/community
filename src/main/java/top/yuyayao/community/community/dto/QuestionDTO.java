package top.yuyayao.community.community.dto;

import lombok.Data;
import lombok.ToString;
import top.yuyayao.community.community.model.User;

@Data
@ToString
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
//    private String avatarUrl;//头像的url
//    private String name;//用户名称
    protected User user;
}
