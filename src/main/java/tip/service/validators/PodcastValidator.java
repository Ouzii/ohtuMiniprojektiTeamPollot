package tip.service.validators;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import tip.domain.Tip;

@Service
public class PodcastValidator {

    public List<String> validate(Tip podcast) {
        List<String> errors = new ArrayList<>();

        if (validateName(podcast) == false) {
            errors.add("nimen pitää olla 1-63 merkkiä pitkä");
        }
        if (validateArtist(podcast) == false) {
            errors.add("artisti joko tyhjä tai max 63 merkkiä");
        }
        if (podcast.getType() == null || podcast.getType().equals("podcast") == false) {
            errors.add("Ei ole podcast");
        }

        if (validateUrl(podcast) == false) {
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

    private boolean validateUrl(Tip podcast) {
        //tää
        return true;
    }
}
