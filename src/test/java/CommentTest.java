import com.sun.playcat.dao.CommentDao;
import com.sun.playcat.domain.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by sunlin on 2017/7/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class CommentTest {

    @Resource
    private CommentDao commentService;

    @Test
    public void search()
    {
        List<Comment> list=commentService.search(1,0,1,1,0,5);
        assert (list.size()>0);
    }

    @Test
    public void searchCount()
    {
        int count=commentService.searchCount(1,0,1,1);
        assert (count>0);
    }
    @Test
    public void insert()
    {
        Comment comment=new Comment();
        comment.setSid(1);
        comment.setRid(-1);
        comment.setUserId(10011);
        comment.setType(1);
        comment.setContent("我认为你可以创造事物，世界会变得更美好。尤其在AI前景上，我真的感到乐观");
        comment.setGoodNum(0);
        comment.setReplyNum(0);
        comment.setCreateTime(new Date());
        comment.setStatus(1);

        commentService.insert(comment);

        assert (comment.getId()>0);

    }

}
