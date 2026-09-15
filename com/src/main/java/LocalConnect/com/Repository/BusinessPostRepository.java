package LocalConnect.com.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import LocalConnect.com.Entity.BusinessPost;

public interface BusinessPostRepository extends JpaRepository<BusinessPost, Long> {

    List<BusinessPost> findByLocalityOrderByCreatedAtDesc(String locality);
    
    List<BusinessPost> findByLocalityAndCategoryIgnoreCaseOrderByCreatedAtDesc(
            String locality, String category);
}
