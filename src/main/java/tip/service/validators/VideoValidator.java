package tip.service.validators;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import tip.domain.Tip;

@Service
public class VideoValidator extends Validator{

    public List<String> validate(Tip video) {
        List<String> errors = new ArrayList<>();

        if (validateName(video) == false) {
            errors.add("nimen pitää olla 1-63 merkkiä pitkä");
        }
        if (!validateDetailStringLength(video, "artist",0, 63, CAN_NULL)) {
            errors.add("artisti joko tyhjä tai max 63 merkkiä");
        }
        if (!validateType(video, "video")) {
            errors.add("Ei ole video");
        }
        if (!validateDateFormat(video, "date", CAN_NULL)){
            errors.add("not a valid date");
        }

        if (!validateUrlFormat(video, "url", CAN_NULL)) {
            errors.add("url on vääränlainen");
        }

        return errors;
    }

}
