package feedback.application.feedback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "feedback")
public class Feedback implements Comparable<Feedback> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false)
    private Course course;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false)
    private Student student;

    @Column(name = "content", length = 1000)
    private String content;

    @Column(name = "rating")
    private int rating;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    public Feedback() {
    }

    public Feedback(Long id, Course course, Student student, String content, int rating, Date createdAt) {
        this.id = id;
        this.course = course;
        this.student = student;
        this.content = content;
        this.rating = rating;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Feedback o) {
        return this.getCreatedAt().compareTo(o.createdAt);
    }
}

