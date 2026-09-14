package LocalConnect.com.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import LocalConnect.com.Entity.ActivityPost;
import LocalConnect.com.Entity.ActivityReply;
import LocalConnect.com.Entity.User;
import LocalConnect.com.Repository.ActivityPostRepository;
import LocalConnect.com.Repository.ActivityReplyRepository;

@Service
public class ActivityService {
    
    @Autowired
    private ActivityPostRepository activityPostRepository;
    @Autowired
    private ActivityReplyRepository activityReplyRepository;

    public ActivityPost createPost(ActivityPost post, User owner) {
        post.setUser(owner);
        post.setLocality(owner.getLocality());
        post.setStatus("OPEN");
        return activityPostRepository.save(post);
    }
    
    public List<ActivityPost> getFeedForLocality(String locality) {
        return activityPostRepository.findByLocalityOrderByCreatedAtDesc(locality);
    }

    public ActivityPost getByIdOrThrow(Long id) {
        return activityPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Activity post not found"));
    }

    public List<ActivityReply> getRepliesForPost(Long postId) {
        return activityReplyRepository.findByActivityPostIdOrderByCreatedAtAsc(postId);
    }
    
    public ActivityReply addReply(Long postId, User replier, String message) {
        ActivityPost post = getByIdOrThrow(postId);
        if (!"OPEN".equals(post.getStatus())) {
            throw new IllegalStateException("This activity is no longer open for replies");
        }
        ActivityReply reply = new ActivityReply();
        reply.setActivityPost(post);
        reply.setUser(replier);
        reply.setMessage(message);
        ActivityReply saved = activityReplyRepository.save(reply);
        return saved;
    }
    
    public List<ActivityPost> searchByActivityName(String locality, String activityName) {
        if (activityName == null || activityName.isBlank()) {
            return getFeedForLocality(locality);
        }
        return activityPostRepository.findByLocalityAndActivityNameContainingIgnoreCaseOrderByCreatedAtDesc(
                locality, activityName);
    }









 


}

