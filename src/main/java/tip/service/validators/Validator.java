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

    protected boolean validateUrlFormat(Tip tip, String key, boolean notNull) {

        Detail url = tip.getDetails().get(key);
        if (url == null) {
            if (notNull == CAN_NULL) {
                return true;
            } else {
                return false;
            }
        }
        if (url.getValue().matches("^(https|http)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]") == false) {
            return false;
        }

        return true;
        /*mikä tää on
            if (date.matches("^(?:(1[0-2]|0[1-9]).(3[01]|[12][0-9]|0[1-9])|(3[01]|[12][0-9]|0[1-9]).(1[0-2]|0[1-9])).[0-9]{4}$")) {
            try {
                DateFormat d = new SimpleDateFormat("MM.dd.yyyy");
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
         */

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
        if (date.matches("^(?:(1[0-2]|0[1-9]).(3[01]|[12][0-9]|0[1-9])|(3[01]|[12][0-9]|0[1-9]).(1[0-2]|0[1-9])).[0-9]{4}$")) {
            try {
                DateFormat d = new SimpleDateFormat("MM.dd.yyyy");
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
            //täs oli enne false ja se ei saanu validei pvm hyväksyttyä
            return true;
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
