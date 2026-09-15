package LocalConnect.com.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LocalConnect.com.Entity.BusinessPost;
import LocalConnect.com.Entity.User;
import LocalConnect.com.Repository.BusinessPostRepository;

@Service
public class BusinessService {
    @Autowired
    private BusinessPostRepository businessPostRepository;
    
    public BusinessPost createPost(BusinessPost post, User owner) {
        post.setUser(owner);
        if (post.getLocality() == null || post.getLocality().isBlank()) {
            post.setLocality(owner.getLocality());
        }
        return businessPostRepository.save(post);
    }

    public List<BusinessPost> getForLocality(String locality) {
        return businessPostRepository.findByLocalityOrderByCreatedAtDesc(locality);
    }

    public List<BusinessPost> filterByCategory(String locality, String category) {
        if (category == null || category.isBlank()) {
            return getForLocality(locality);
        }
        return businessPostRepository.findByLocalityAndCategoryIgnoreCaseOrderByCreatedAtDesc(locality, category);
    }

    public BusinessPost getByIdOrThrow(Long id) {
        return businessPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Business post not found"));
    }

    public void deletePost(Long id, Long currentUserId) {
        BusinessPost post = getByIdOrThrow(id);
        if (!post.getUser().getId().equals(currentUserId)) {
            throw new SecurityException("You are not allowed to delete this listing");
        }
        businessPostRepository.delete(post);
    }
}
