package tip.service.validators;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import tip.domain.Tip;

@Service
public class BlogpostValidator  extends Validator {

    public List<String> validate(Tip blogpost) {
        List<String> errors = new ArrayList<>();

        if (!validateName(blogpost)) {
            errors.add("nimen pitää olla 1-63 merkkiä pitkä");
        }
        if (!validateDetailStringLength(blogpost, "artist", 0, 63, CAN_NULL)) {
            errors.add("artisti joko tyhjä tai max 63 merkkiä");
        }
        if (!validateType(blogpost, "blogpost")) {
            errors.add("Ei ole blogpost");
        }

        if (!validateUrlFormat(blogpost, "url", CAN_NULL)) {
            errors.add("url on vääränlainen");
        }
        if (!validateDateFormat(blogpost, "date", CAN_NULL)) {
            errors.add("not a valid date");
        }

        return errors;
    }
}
