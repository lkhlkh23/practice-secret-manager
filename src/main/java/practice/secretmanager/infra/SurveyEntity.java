package practice.secretmanager.infra;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "survey")
public class SurveyEntity {

    @Id
    @Column(name = "survey_id")
    private long surveyId;

    @Column(name = "title", nullable = false)
    private String title;

}
