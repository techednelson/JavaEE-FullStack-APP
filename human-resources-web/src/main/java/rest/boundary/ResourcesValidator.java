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

        countries.add("GREECE");
        countries.add("MEXICO");
        countries.add("ENGLAND");
        countries.add("SPAIN");
        countries.add("USA");

        cities.add("ATHENS");
        cities.add("THESSALONIKI");
        cities.add("PATRA");
        cities.add("LONDON");
        cities.add("MANCHESTER CITY");
        cities.add("LIVERPOOL");
        cities.add("MEXICO CITY");
        cities.add("GUADALAJARA");
        cities.add("MONTERREY");
        cities.add("MADRID");
        cities.add("BARCELONA");
        cities.add("SEVILLA");
        cities.add("NEW YORK");
        cities.add("LOS ANGELES");
        cities.add("SAN FRANCISCO");


        if(country != null & city != null) {
            return countries.contains(country) && cities.contains(city);
        }

        return false;
    }


}
