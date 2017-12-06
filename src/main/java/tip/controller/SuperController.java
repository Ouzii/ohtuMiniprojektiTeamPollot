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

    protected final static String DEFAUL_MODE_SUCC_MSG = "tip has succesfully been modified olalala";
    protected final static String DEFAUL_ADD_SUCC_MSG = "tip has succesfully been added olalala";

    @Autowired
    protected DetailRepository detailRepository;
    @Autowired
    protected TipRepository tipRepository;

    protected List<String> handleDetail(String value, String key, Tip tip, List<String> notNullDetails) {
        if (value != null && !value.trim().isEmpty()) {
            addDetail(value, key, tip);
            return new ArrayList<>();
        } else {
            return removeDetail(value, key, tip, notNullDetails);
        }
    }

    protected void makeDetail(String value, String key, Tip tip) {
        if (value != null && !value.trim().isEmpty()) {
            addDetail(value, key, tip);
        }
    }

    private void addDetail(String value, String key, Tip tip) {
        Detail newDetail = new Detail(value);
        newDetail.addTip(tip);
        tip.addDetail(key, newDetail);
    }

    private List<String> removeDetail(String value, String key, Tip tip, List<String> notNullDetails) {
        List<String> errors = new ArrayList<>();
        Detail removeDetail = tip.getDetails().get(key);
        if (!notNullDetails.contains(key)) {
            if (removeDetail != null) {
                tip.removeDetailByKey(key);
                detailRepository.delete(removeDetail);

            }
            return errors;
        }
        errors.add("Poisto epäonnistui, koska " + key + " on pakollinen kenttä");
        return errors;
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

    protected boolean saveTip(List<String> errors, Tip tip, RedirectAttributes attributes, String successMsg) {
        if (errors.isEmpty()) {
            saveDetails(tip);
            this.tipRepository.save(tip);
            attributes.addFlashAttribute("message", successMsg);
            return true;
        } else {
            attributes.addFlashAttribute("errors", errors);
            return false;
        }
    }

    protected ArrayList<String> tipNameIsUnique(String name) {
        ArrayList<String> errors = new ArrayList<>();
        if (tipRepository.findByName(name) != null) {
            errors.add(("Saman niminen kirjainmerkki on jo olemassa!"));
        }
        return errors;
    }

    protected ArrayList<String> tipNameComboIsUnique(String tipName, String detailName) {
        ArrayList<String> errors = new ArrayList<>();
        Tip foundTip = tipRepository.findByName(tipName);

        if (foundTip != null) {
            Detail foundDetail = foundTip.getDetails().get(detailName);
            if (foundDetail != null && foundDetail.getValue().equals(detailName)) {
                errors.add(("Saman niminen kirjainmerkki on jo olemassa samalla alanimellä!"));
            }
        }
        return errors;
    }
}
