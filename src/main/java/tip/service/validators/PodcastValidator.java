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
        if (!isValid(podcast.getDetails().get("date").toString())) {
            errors.add("not a valid date");
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
        if (podcast.getDetails().get("url").toString().matches("^(https|http)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValid(String date) {
        if (date.matches("((29|30)[\\/.](18|19|20)[0-9]{2})|((0[1-9]|1[0-2])[\\/.](0[1-9]|1[0-9]|2[0-8])[\\/.](18|19|20)[0-9]{2})|((02)[\\/.]29[\\/.](0[13578]|1[02])[\\/.]31[\\/.](18|19|20)[0-9]{2})|((01|0[3-9]|1[1-2])[\\/.](((18|19|20)(04|08|[2468][048]|[13579][26]))|2000))")) {
            return true;
        } else {
            return false;
        }
    }

}
