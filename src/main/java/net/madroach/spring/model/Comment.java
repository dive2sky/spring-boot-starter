package net.madroach.spring.model;

import lombok.Data;

/**
 * Created by sampark on 2016. 12. 17..
 */
@Data
public class Comment extends Model{
    private Integer id;
    private Integer postId;
    private String content;
    private String userId;
}
