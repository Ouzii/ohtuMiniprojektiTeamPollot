package tip.service.validators;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import tip.domain.Tip;

@Service
public class BookValidator extends Validator {

    public List<String> validate(Tip book) {
        List<String> errors = new ArrayList<>();

        if (!validateName(book)) {
            errors.add("nimen pitää olla 1-63 merkkiä pitkä");
        }
        if (!validateDetailStringLength(book, "writer", 0, 63, CAN_NULL)) {
            errors.add("kirjoittaja joko tyhjä tai max 63 merkkiä");
        }
        if (!validateDetailStringLength(book, "kommentti", 0, 253, CAN_NULL)) {
            errors.add("kommentti ei saa olla yli 253 merkkiä pitkä");
        }
        if (!validateType(book, "book")) {
            errors.add("Ei ole kirja");
        }

        if (!validateISBN(book, "isbn", NOT_NULL)) {
            errors.add("ISBN on vääränlainen");
        }

//        if (!isValid(book.getDetails().get("date").toString())) {
//            errors.add("not a valid date");
//        }
        return errors;
    }

    @Override
    public List<String> getNotNullDetailKeys() {
        ArrayList<String> notNullList = new ArrayList<>();
        notNullList.add("isbn");
        return notNullList;
    }
}
