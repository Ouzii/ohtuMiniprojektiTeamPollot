package tip.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tip.domain.Detail;
import tip.domain.Tip;
import tip.repository.DetailRepository;
import tip.repository.TipRepository;

public abstract class SuperController {

    @Autowired
    private DetailRepository detailRepository;
    @Autowired
    private TipRepository tipRepository;

    protected Detail makeDetail(String value, String key, Tip tip) {

        if (value != null && !value.trim().isEmpty()) {
            Detail detail = new Detail(value);
            detail.addTip(tip);
            tip.addDetail(key, detail);
            return detail;
        }
        return null;
    }

    protected void saveDetails(Tip t) {
        for (Detail detail : t.getDetails().values()) {
            detailRepository.save(detail);
        }
    }

    protected void setTipRead(Tip tip, int read) {
        if (read == 1) {
            tip.setRead(true);
        } else {
            tip.setRead(false);
        }
    }

    protected boolean saveTip(List<String> errors, Tip tip, RedirectAttributes attributes) {
        if (errors.isEmpty()) {
            saveDetails(tip);
            this.tipRepository.save(tip);
            return true;
        } else {
            attributes.addFlashAttribute("errors", errors);
            return false;
        }
    }

    protected ArrayList<String> tipNameIsUnique(String name) {
        ArrayList<String> errors = new ArrayList<>();
        if (tipRepository.findByName(name) != null) {
            errors.add(("Saman niminen karjainmerkki on jo olemassa!"));
        }
        return errors;
    }
}
