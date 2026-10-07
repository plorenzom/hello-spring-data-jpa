package es.thefactory.hellospringdatajpa.dal.validation;

import es.thefactory.hellospringdatajpa.dal.spi.Identifiable;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author Pablo Lorenzo Manzano.
 */
@Component
@RequiredArgsConstructor
public class EntityValidator {

    /**
     *
     */
    private final Validator validator;

    /**
     *
     * @param entity
     * @param <T>
     */
    public <T extends Identifiable> void validate(T entity) {
        Set<ConstraintViolation<T>> violationSet = validator.validate(entity);

        if (!(violationSet.isEmpty())) {
            throw new ConstraintViolationException(violationSet);
        }
    }
}
