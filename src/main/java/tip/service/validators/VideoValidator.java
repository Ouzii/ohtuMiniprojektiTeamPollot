package tip.service.validators;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import tip.domain.Tip;

@Service
public class VideoValidator {

    public List<String> validate(Tip video) {
        List<String> errors = new ArrayList<>();

        if (validateName(video) == false) {
            errors.add("nimen pitää olla 1-63 merkkiä pitkä");
        }
        if (validateArtist(video) == false) {
            errors.add("artisti joko tyhjä tai max 63 merkkiä");
        }
        if (video.getType() == null || video.getType().equals("video") == false) {
            errors.add("Ei ole video");
        }

        if (validateUrl(video) == false) {
            errors.add("url on vääränlainen");
        }

        return errors;
    }

    private boolean validateName(Tip t) {

        if (t.getName().trim().isEmpty()) {
            return false;
        } else if (t.getName().length() > 63) {
            return false;
        }
        return true;
    }

    private boolean validateArtist(Tip t) {
        if (t.getDetails().get("writer") == null) {
            return true;
        } else if (t.getDetails().get("writer").getValue() == null) {
            return true;
        } else if (t.getDetails().get("writer").getValue().length() > 63) {
            return false;
        }
        return true;
    }

    private boolean validateUrl(Tip video) {
        if (video.getDetails().get("url").toString().matches("^(https|http)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")) {
            return true;
        } else {
            return false;
        }
    }
}
