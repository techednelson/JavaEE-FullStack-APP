package rest.boundary;

import model.Department;
import model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Stateless
public class HibernateValidator {

    @Inject
    private Validator validator;

    private Logger logger = LogManager.getLogger(HibernateValidator.class.getName());

    public List<String> validateEntity(Object entity) {

        List<String> errors = new ArrayList<>();

        if(entity instanceof Department || entity instanceof Employee) {
            Set<ConstraintViolation<Object>> constraintViolations = validator.validate(entity);
            if (!constraintViolations.isEmpty()) {
                for (ConstraintViolation<Object> cv : constraintViolations) {

                    StringBuilder err = new StringBuilder();
                    err.append(cv.getPropertyPath());
                    err.append(" ");
                    err.append(cv.getMessage());
                    logger.error(cv.getMessage());
                    errors.add(err.toString());
                }
            }
            return errors;
        }

        return null;
    }

}
