package tip.service.validators;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import tip.domain.Tip;

@Service
public class PodcastValidator extends Validator {

    public List<String> validate(Tip podcast) {
        List<String> errors = new ArrayList<>();

        if (!validateName(podcast)) {
            errors.add("nimen pitää olla 1-63 merkkiä pitkä");
        }
        if (!validateDetailStringLength(podcast, "artist",0, 63, CAN_NULL)) {
            errors.add("artisti joko tyhjä tai max 63 merkkiä");
        }

        if (!validateType(podcast, "podcast")) {
            errors.add("Ei ole podcast");
        }
      
        if (!validateUrlFormat(podcast, "url", CAN_NULL)) {
            errors.add("url on vääränlainen");
        }

        if (!validateDateFormat(podcast, "date", CAN_NULL)) {
            errors.add("not a valid date");
        }
        return errors;
    }

}
