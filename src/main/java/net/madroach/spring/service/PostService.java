package net.madroach.spring.service;

import net.madroach.spring.dao.madroach1.PostDao;
import net.madroach.spring.code.TxManagerCd;
import net.madroach.spring.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sampark on 2016. 12. 17..
 */
@Service
@Transactional(TxManagerCd.MADROACH1)
public class PostService {

    @Autowired
    private PostDao postDao;

    public List<Post> getPosts(){
        return  postDao.findAll();
    }

    public int putPost(Post post){
        return postDao.insert(post);
    }
}
