package tip.service.validators;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import tip.domain.Detail;
import tip.domain.Tip;

public abstract class Validator {

    protected static final boolean CAN_NULL = false;
    protected static final boolean NOT_NULL = true;
    protected static final int STR_MAX_LEN = 63;

    public abstract List<String> getNotNullDetailKeys();

    protected boolean validateName(Tip t) {
        if (t.getName().trim().isEmpty()) {
            return false;
        } else if (t.getName().length() > STR_MAX_LEN) {
            return false;
        }
        return true;
    }

    protected boolean validateType(Tip t, String shouldBe) {
        if (t.getType() == null) {
            return false;
        }
        return t.getType().equals(shouldBe);
    }
/////////////

    protected boolean validateDetailStringLength(Tip t, String key, int min, int max, boolean notNull) {

        if (t.getDetails().get(key) == null) {
            if (notNull == CAN_NULL) {
                return true;
            } else {
                return false;
            }
        }
        Detail detail = t.getDetails().get(key);
        if (detail.getValue().length() > max) {
            return false;
        }
        if (detail.getValue().length() < min) {
            return false;
        }

        return true;
    }
    /** 
     * kelpaavat urlit ovat muotoa https://jotain.jota2in.jotain94(:99999-:00000)/jotain-&/?=$.0-9
     * 
     * @author Raul Becker
     */
    protected boolean validateUrlFormat(Tip tip, String key, boolean notNull) {
	
        Detail url = tip.getDetails().get(key);
        if (url == null) {
            if (notNull == CAN_NULL) {
                return true;
            } else {
                return false;
            }
        } 
        if (url.getValue().matches("^(http|https):\\/\\/([a-zA-Z0-9.-])*((:[0-9]{1,5}\\/)|\\/|)([A-Za-z0-9\\/\\-.$&=?])*$") == false) {
            return false;
        }

        return true;
    }

    protected boolean validateDateFormat(Tip tip, String key, boolean notNull) {
        Detail dateDetail = tip.getDetails().get(key);

        if (dateDetail == null) {
            if (notNull == CAN_NULL) {
                return true;
            } else {
                return false;
            }
        }

        String date = dateDetail.getValue(); 
        return trydateformats(date, "MM.dd.yyyy") 
                || trydateformats(date, "MM-dd-yyyy")
                || trydateformats(date, "yyyy-MM-dd")
                || trydateformats(date, "yyyy.MM.dd")
                || trydateformats(date, "dd-MM-yyyy")
                || trydateformats(date, "dd.MM.yyyy");

    }

    /**
     * Tämä on nyt siinä muodossa mitä tuo html5 datepicker haluaa syöttää eli "yyyy-MM-dd"
     * 
     * yyyy saa olla mitä vaan väliltä 0000-9999
     * MM saa olla 10-12 tai 01-09 
     * dd saa olla 30-31 tai 10-29 tai 01-09 
     *
     * @author  Raul Becker
     *  
     */
    protected boolean IS_COMPUTER_DATE_OK_YES_OR_NO(String date) {
        if (date.matches("^(?:([0-9]{4})-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9]))$")) {
            try {
                DateFormat d = new SimpleDateFormat("yyyy-MM-dd");
                Date parsedDate = d.parse(date);
                if (d.format(parsedDate).equals(date)) {
                    return true;
                } else {
                    return false;
                }
            } catch (ParseException ex) {
                return false;
            }
        } else {
            return false;
        }

    }
    
    private boolean trydateformats(String date, String format) {
        try {
            DateFormat d = new SimpleDateFormat(format);
            Date parsedDate = d.parse(date);
            if (d.format(parsedDate).equals(date)) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException ex) {
            return false;
        }

    }

    protected boolean validateISBN(Tip t, String key, boolean notNull) {
        Detail isbn = t.getDetails().get(key);
        if (t.getDetails().get(key) == null) {
            if (notNull == CAN_NULL) {
                return true;
            } else {
                return false;
            }
        }

        String tmp = isbn.getValue();
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
