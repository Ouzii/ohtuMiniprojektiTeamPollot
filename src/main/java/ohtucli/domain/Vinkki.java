package ohtucli.domain;

import java.util.HashMap;
import javax.xml.soap.Detail;

public class Vinkki {

    private String header;
    private String type;
    private HashMap<String, Detail> details;

    public Vinkki(String header, String type, HashMap<String, Detail> details) {
        this.header = header;
        this.type = type;
        if (details == null) {
            details = new HashMap<>();
        } else {
            this.details = details;
        }
    }

    public String getType() {
        return type;
    }

    public String getHeader() {
        return header;
    }

    public HashMap<String, Detail> getDetails() {
        return details;
    }

    @Override
    public String toString() {
        String vinkki = "Otsikko: " + header + "\n"
                + "Tyyppi: " + type + "\n";
        for (String key : details.keySet()) {
            vinkki += key + ": " + details.get(key) + "\n";
        }
        return vinkki;
    }

}
