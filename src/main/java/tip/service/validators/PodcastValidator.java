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
        if (!validateDetailStringLength(podcast, "tekijä", 0, 63, CAN_NULL)) {
            errors.add("tekijä joko tyhjä tai max 63 merkkiä");
        }
        if (!validateDetailStringLength(podcast, "podcastin nimi", 0, 63, CAN_NULL)) {
            errors.add("podcastin nimi joko tyhjä tai max 63 merkkiä");
        }
        if (!validateDetailStringLength(podcast, "jakso", 0, 63, CAN_NULL)) {
            errors.add("jakso joko tyhjä tai max 63 merkkiä");
        }
        if (!validateDetailStringLength(podcast, "kuvaus", 0, 253, CAN_NULL)) {
            errors.add("kuvaus ei saa olla yli 253 merkkiä pitkä");
        }

        if (!validateType(podcast, "podcast")) {
            errors.add("Ei ole podcast");
        }

        if (!validateUrlFormat(podcast, "url", CAN_NULL)) {
            errors.add("url on vääränlainen");
        }

        if (!validateDateFormat(podcast, "julkaisupvm", CAN_NULL)) {
            errors.add("päivämäärä väärässä muodossa");
        }
        return errors;
    }

    @Override
    public List<String> getNotNullDetailKeys() {
        return new ArrayList<>();
    }

}
