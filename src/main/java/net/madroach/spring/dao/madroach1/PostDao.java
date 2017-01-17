package net.madroach.spring.dao.madroach1;

import net.madroach.spring.code.TxManagerCd;
import net.madroach.spring.model.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sampark on 2016. 12. 17..
 */
@Repository
@Transactional(TxManagerCd.MADROACH1)
public interface PostDao {

    @Select("select * from posts")
    List<Post> findAll();


    @Insert("INSERT INTO `posts` " +
            "(`id`, " +
            "`title`, " +
            "`content`, " +
            "`status`, " +
            "`user_id`, " +
            "`created_at`, " +
            "`updated_at`) " +
            "VALUES " +
            "(#{id}, " +
            "#{title}, " +
            "#{content}, " +
            "#{status}, " +
            "#{userId}, " +
            "#{createdAt}, " +
            "#{updatedAt});")
    int insert(Post post);


}
