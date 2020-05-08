package top.yuyayao.community.community.model;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
}
