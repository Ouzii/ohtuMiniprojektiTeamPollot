package tip.service.validators;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import tip.domain.Tip;

@Service
public class VideoValidator extends Validator {

    public List<String> validate(Tip video) {
        List<String> errors = new ArrayList<>();

        if (validateName(video) == false) {
            errors.add(ERROR1);
        }
        if (!validateDetailStringLength(video, "artist", 0, 63, CAN_NULL)) {
            errors.add(ERROR3);
        }
        if (!validateDetailStringLength(video, "kommentti", 0, 253, CAN_NULL)) {
            errors.add(ERROR2);
        }
        if (!validateType(video, "video")) {
            errors.add(ERROR9);
        }
        if (!validateDateFormat(video, "date", CAN_NULL)) {
            errors.add(ERROR12);
        }

        if (!validateUrlFormat(video, "url", CAN_NULL)) {
            errors.add(ERROR10);
        }

        return errors;
    }

    @Override
    public List<String> getNotNullDetailKeys() {
        return new ArrayList<>();
    }

}
