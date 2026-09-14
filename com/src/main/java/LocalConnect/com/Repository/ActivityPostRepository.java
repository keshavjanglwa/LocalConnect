package LocalConnect.com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import LocalConnect.com.Entity.ActivityPost;

import java.util.List;

public interface ActivityPostRepository extends JpaRepository<ActivityPost, Long> {
    
    List<ActivityPost> findByUserIdOrderByCreatedAtDesc(Long userId);
    List<ActivityPost> findByLocalityOrderByCreatedAtDesc(String locality);
     List<ActivityPost> findByLocalityAndActivityNameContainingIgnoreCaseOrderByCreatedAtDesc(String locality, String activityName);
}
