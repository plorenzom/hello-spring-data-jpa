package es.thefactory.hellospringdatajpa.dal.entity;

import es.thefactory.hellospringdatajpa.dal.spi.Identifiable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

/**
 * @author Pablo Lorenzo Manzano.
 */
@Entity
@Getter
@Setter
@Table(name = "team")
public class TeamEntity implements Identifiable {

    /**
     *
     */
    @Id
    @UuidGenerator(style = UuidGenerator.Style.VERSION_7)
    @Column(name = "team_id")
    private UUID teamId;

    /**
     *
     */
    @NotBlank
    @Size(max = 50)
    @Column(name = "name", length = 50, nullable = false)
    private String name;
}
