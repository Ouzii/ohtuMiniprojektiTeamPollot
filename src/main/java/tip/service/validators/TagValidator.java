
package tip.service.validators;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import tip.domain.Tag;

@Service
public class TagValidator {
    public List<String> validateTag(Tag tag) {
         List<String> errors = new ArrayList<>();
        if (tag == null || tag.getName() == null ||tag.getName().trim().isEmpty()) {
            errors.add("tägi on tyhjä");
        } else if (tag.getName().length() > 63) {
            errors.add("tägi saa olla max 63 merkkiä pitkä");
        }
        return errors;
    } 
}
