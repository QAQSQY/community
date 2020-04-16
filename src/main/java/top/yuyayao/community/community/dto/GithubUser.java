package top.yuyayao.community.community.dto;

import lombok.Data;

@Data
public class GithubUser {
    private String login;
    private Long id;
    private String bio;
    private String name;
}
