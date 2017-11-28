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
}
