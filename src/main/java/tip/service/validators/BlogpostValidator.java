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
            errors.add("nimen pitää olla 1-63 merkkiä pitkä");
        }
        if (!validateDetailStringLength(blogpost, "kuvaus", 0, 253, CAN_NULL)) {
            errors.add("kuvaus ei saa olla yli 253 merkkiä pitkä");
        }
        if (!validateDetailStringLength(blogpost, "kirjoittaja", 0, 63, CAN_NULL)) {
            errors.add("kirjoittaja joko tyhjä tai max 63 merkkiä");
        }
        if (!validateDetailStringLength(blogpost, "blogin nimi", 0, 63, CAN_NULL)) {
            errors.add("blogin nimi joko tyhjä tai max 63 merkkiä");
        }        
        if (!validateType(blogpost, "blogpost")) {
            errors.add("Ei ole blogpost");
        }

        if (!validateUrlFormat(blogpost, "url", CAN_NULL)) {
            errors.add("url on vääränlainen");
        }
        if (!validateDateFormat(blogpost, "julkaisupvm", CAN_NULL)) {
            errors.add("päivämäärä väärässä muodossa");
        }

        return errors;
    }

    @Override
    public List<String> getNotNullDetailKeys() {
        return new ArrayList<>();
    }
}
