package tip.service.validators;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import tip.domain.Tip;

@Service
public class BookValidator {

    public List<String> validate(Tip book) {
        List<String> errors = new ArrayList<>();

        if (validateName(book) == false) {
            errors.add("nimen pitää olla 1-63 merkkiä pitkä");
        }
        if (validateWriter(book) == false) {
            errors.add("kirjoittaja joko tyhjä tai max 63 merkkiä");
        }
        if (book.getType() == null || book.getType().equals("book") == false) {
            errors.add("Ei ole kirja");
        }

        if (validateISBN(book) == false) {
            errors.add("ISBN on vääränlainen");
        }
        if (!isValid(book.getDetails().get("date").toString())) {
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

    private boolean validateWriter(Tip t) {
        if (t.getDetails().get("writer") == null) {
            return true;
        } else if (t.getDetails().get("writer").getValue() == null) {
            return true;
        } else if (t.getDetails().get("writer").getValue().length() > 63) {
            return false;
        }
        return true;
    }

    private boolean validateISBN(Tip t) {
        if (t.getDetails().containsKey("isbn") == false) {
            return false;
        }
        String isbn = t.getDetails().get("isbn").getValue();
        if (isbn == null) {
            return false;
        }

        String tmp = isbn;
        tmp = tmp.replaceAll("-", "");
        if (tmp.length() != 13) {
            return false;
        }

        int num = 0, total = 0;
        for (int i = 1; i <= 12; i++) {
            num = Integer.parseInt(tmp.substring(i - 1, i));
            total += ((i - 1) % 2 == 0) ? num * 1 : num * 3;
        }
        int chksum = 10 - (total % 10);
        if (chksum == 10) {
            chksum = 0;
        }
        return chksum == Integer.parseInt(tmp.substring(12));

    }

    public boolean isValid(String date) {
        if (date.matches("((29|30)[\\/.](18|19|20)[0-9]{2})|((0[1-9]|1[0-2])[\\/.](0[1-9]|1[0-9]|2[0-8])[\\/.](18|19|20)[0-9]{2})|((02)[\\/.]29[\\/.](0[13578]|1[02])[\\/.]31[\\/.](18|19|20)[0-9]{2})|((01|0[3-9]|1[1-2])[\\/.](((18|19|20)(04|08|[2468][048]|[13579][26]))|2000))")) {
            return true;
        } else {
            return false;
        }
    }

}
