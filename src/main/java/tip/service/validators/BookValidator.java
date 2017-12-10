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
            errors.add(ERROR1);
        }
        if (!validateDetailStringLength(book, "writer", 0, 63, CAN_NULL)) {
            errors.add(ERROR4);
        }
        if (!validateDetailStringLength(book, "kommentti", 0, 253, CAN_NULL)) {
            errors.add(ERROR2);
        }
        if (!validateType(book, "book")) {
            errors.add(ERROR7);
        }

        if (!validateISBN(book, "isbn", NOT_NULL)) {
            errors.add(ERROR11);
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
