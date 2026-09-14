package LocalConnect.com.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ActivityReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "activity_post_id")
    private ActivityPost activityPost;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String message;
    private String status = "PENDING";
    private LocalDateTime createdAt = LocalDateTime.now();
    
} 
