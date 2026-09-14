package LocalConnect.com.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import LocalConnect.com.Entity.ActivityPost;
import LocalConnect.com.Entity.ActivityReply;

public interface ActivityReplyRepository extends JpaRepository<ActivityReply, Long> {
    List<ActivityReply> findByActivityPostIdOrderByCreatedAtAsc(Long activityPostId);
   
    
}
 