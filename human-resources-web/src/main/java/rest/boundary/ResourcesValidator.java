package rest.boundary;

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
    private List<String> errors = new ArrayList<>();
    private StringBuilder err = new StringBuilder();
    private List<String> countries = new ArrayList<>();
    private List<String> cities = new ArrayList<>();

    public String validateEntity(Object entity) {

        if(entity != null) {
            Set<ConstraintViolation<Object>> constraintViolations = validator.validate(entity);
            if (constraintViolations.isEmpty()) {
                return null;
            } else {
                for (ConstraintViolation<Object> cv : constraintViolations) {
                    err.append("\n");
                    err.append(cv.getPropertyPath());
                    err.append(" ");
                    err.append(cv.getMessage());
                    err.append("\n");
                    logger.error(cv.getMessage());
                    errors.add(err.toString());
                }
                err = new StringBuilder();
                for (String s : errors) {
                    err.append(s);
                    err.append(" \n");
                }
            }
        }
        return err.toString();
    }

    public boolean validateLocation(String country, String city) {

        countries.add("greece");
        countries.add("mexico");
        countries.add("england");
        countries.add("spain");
        countries.add("USA");

        cities.add("athens");
        cities.add("thessaloniki");
        cities.add("patra");
        cities.add("london");
        cities.add("manchester city");
        cities.add("liverpool");
        cities.add("mexico city");
        cities.add("guadalajara");
        cities.add("monterrey");
        cities.add("madrid");
        cities.add("barcelona");
        cities.add("sevilla");
        cities.add("new york");
        cities.add("los angeles");
        cities.add("san francisco");


        if(country != null & city != null) {
            return countries.contains(country) && cities.contains(city);
        }

        return false;
    }


}
