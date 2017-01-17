package net.madroach.spring.model;

import lombok.Data;

/**
 * Created by sampark on 2016. 12. 17..
 */
@Data
public class Post extends Model{
    private Integer id;
    private String title;
    private String content;
    private String userId;
}
