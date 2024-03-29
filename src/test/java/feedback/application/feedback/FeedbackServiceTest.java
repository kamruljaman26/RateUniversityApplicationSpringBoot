package feedback.application.feedback;

import feedback.application.feedback.model.Course;
import feedback.application.feedback.model.Feedback;
import feedback.application.feedback.model.Student;
import feedback.application.feedback.repository.FeedbackRepository;
import feedback.application.feedback.service.FeedbackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FeedbackServiceTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    @InjectMocks
    private FeedbackService feedbackService;

    private Student student;
    private Course course;
    private Feedback feedback;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

        student = new Student();
        student.setId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("johndoe@example.com");

        course = new Course();
        course.setId(1L);
        course.setTitle("Test Course");

        feedback = new Feedback();
        feedback.setId(1L);
        feedback.setStudent(student);
        feedback.setCourse(course);
        feedback.setRating(4);
        feedback.setContent("Test feedback content");
    }

    @Test
    public void testFindAllFeedbacks() {
        List<Feedback> feedbacks = new ArrayList<>();
        feedbacks.add(feedback);

        when(feedbackRepository.findAll()).thenReturn(feedbacks);

        List<Feedback> result = feedbackService.findAllFeedbacks();

        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
    }

    @Test
    public void testFindFeedbackById() {
        when(feedbackRepository.findById(1L)).thenReturn(Optional.of(feedback));

        Optional<Feedback> result = feedbackService.findFeedbackById(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(1L);
    }

    @Test
    public void testSaveFeedback() {
        when(feedbackRepository.save(feedback)).thenReturn(feedback);

        Feedback savedFeedback = feedbackService.saveFeedback(feedback);

        assertThat(savedFeedback).isNotNull();
        assertThat(savedFeedback.getId()).isEqualTo(1L);
    }

    @Test
    public void testDeleteFeedback() {
        feedbackService.deleteFeedback(1L);

        verify(feedbackRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testHasStudentLeftFeedback() {
        when(feedbackRepository.findByStudentAndCourse(student, course)).thenReturn(Optional.of(feedback));

        boolean result = feedbackService.hasStudentLeftFeedback(student, course);

        assertThat(result).isTrue();
    }

    @Test
    public void testFindAllByStudent() {
        List<Feedback> feedbacks = new ArrayList<>();
        feedbacks.add(feedback);

        when(feedbackRepository.findAll()).thenReturn(feedbacks);

        List<Feedback> result = feedbackService.findAllByStudent(student);

        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
    }

    @Test
    public void testFeedbackGetterSetterMethods() {
        // Create a new Feedback object
        Feedback testFeedback = new Feedback();

        // Create a Course and Student objects for association
        Course course = new Course();
        Student student = new Student();

        // Set values using setter methods
        testFeedback.setId(1L);
        testFeedback.setCourse(course);
        testFeedback.setStudent(student);
        testFeedback.setContent("Test Content");
        testFeedback.setRating(5);
        testFeedback.setCreatedAt(new Date());

        // Verify values using getter methods
        assertThat(testFeedback.getId()).isEqualTo(1L);
        assertThat(testFeedback.getCourse()).isEqualTo(course);
        assertThat(testFeedback.getStudent()).isEqualTo(student);
        assertThat(testFeedback.getContent()).isEqualTo("Test Content");
        assertThat(testFeedback.getRating()).isEqualTo(5);
        assertThat(testFeedback.getCreatedAt()).isNotNull();
    }

}

