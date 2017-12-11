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
            errors.add(ERROR1);
        }
        if (!validateDetailStringLength(podcast, "artist", 0, 63, CAN_NULL)) {
            errors.add(ERROR3);
        }
        if (!validateDetailStringLength(podcast, "kommentti", 0, 253, CAN_NULL)) {
            errors.add(ERROR2);
        }

        if (!validateType(podcast, "podcast")) {
            errors.add(ERROR8);
        }

        if (!validateUrlFormat(podcast, "url", CAN_NULL)) {
            errors.add(ERROR10);
        }

        if (!validateDateFormat(podcast, "date", CAN_NULL)) {
            errors.add(ERROR12);
        }
        return errors;
    }

    @Override
    public List<String> getNotNullDetailKeys() {
        return new ArrayList<>();
    }

}
