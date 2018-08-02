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
public class ResourcesValidator {

    @Inject
    private Validator validator;

    private Logger logger = LogManager.getLogger(ResourcesValidator.class.getName());

    public String validateEntity(Object entity) {

        List<String> errors = new ArrayList<>();
        StringBuilder err = new StringBuilder();

        if(entity != null) {
            Set<ConstraintViolation<Object>> constraintViolations = validator.validate(entity);
            if (constraintViolations.isEmpty()) {
                return null;
            } else {
                for (ConstraintViolation<Object> cv : constraintViolations) {
                    err.append(cv.getPropertyPath());
                    err.append(" ");
                    err.append(cv.getMessage());
                    logger.error(cv.getMessage());
                    errors.add(err.toString());
                }
                err = new StringBuilder();
                for (String s : errors) {
                    err.append(s);
                    err.append("\t");
                }
            }
        }
        return err.toString();
    }


}
