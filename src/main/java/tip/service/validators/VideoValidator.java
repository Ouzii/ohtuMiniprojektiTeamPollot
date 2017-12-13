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
            errors.add("nimen pitää olla 1-63 merkkiä pitkä");
        }
        if (!validateDetailStringLength(video, "tekijä", 0, 63, CAN_NULL)) {
            errors.add("tekijä joko tyhjä tai max 63 merkkiä");
        }
        if (!validateDetailStringLength(video, "otsikko", 0, 63, CAN_NULL)) {
            errors.add("otsikko joko tyhjä tai max 63 merkkiä");
        }
        if (!validateDetailStringLength(video, "kuvaus", 0, 253, CAN_NULL)) {
            errors.add("kuvaus ei saa olla yli 253 merkkiä pitkä");
        }
        if (!validateType(video, "video")) {
            errors.add("Ei ole video");
        }
        if (!validateDateFormat(video, "julkaisupvm", CAN_NULL)) {
            errors.add("Päivämäärä väärässä muodossa");
        }

        if (!validateUrlFormat(video, "url", CAN_NULL)) {
            errors.add("Url on vääränlainen");
        }

        return errors;
    }

    @Override
    public List<String> getNotNullDetailKeys() {
        return new ArrayList<>();
    }

}
