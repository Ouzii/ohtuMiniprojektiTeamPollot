package tip.service.validators;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import tip.domain.Tip;

@Service
public class BlogpostValidator extends Validator {

    public List<String> validate(Tip blogpost) {
        List<String> errors = new ArrayList<>();

        if (!validateName(blogpost)) {
            errors.add(ERROR1);
        }
        if (!validateDetailStringLength(blogpost, "kommentti", 0, 253, CAN_NULL)) {
            errors.add(ERROR2);
        }
        if (!validateDetailStringLength(blogpost, "artist", 0, 63, CAN_NULL)) {
            errors.add(ERROR3);
        }
        if (!validateType(blogpost, "blogpost")) {
            errors.add(ERROR6);
        }

        if (!validateUrlFormat(blogpost, "url", CAN_NULL)) {
            errors.add(ERROR10);
        }
        if (!validateDateFormat(blogpost, "date", CAN_NULL)) {
            errors.add(ERROR12);
        }

        return errors;
    }

    @Override
    public List<String> getNotNullDetailKeys() {
        return new ArrayList<>();
    }
}
