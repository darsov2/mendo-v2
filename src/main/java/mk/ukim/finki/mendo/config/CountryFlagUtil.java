package mk.ukim.finki.mendo.config;

import java.util.HashMap;
import java.util.Map;

public class CountryFlagUtil {
    private static final Map<String, String> COUNTRY_NAME_TO_CODE = new HashMap<>();

    static {
        // Europe
        COUNTRY_NAME_TO_CODE.put("Macedonia", "MK");
        COUNTRY_NAME_TO_CODE.put("United States", "US");
        COUNTRY_NAME_TO_CODE.put("Germany", "DE");
        COUNTRY_NAME_TO_CODE.put("France", "FR");
        COUNTRY_NAME_TO_CODE.put("United Kingdom", "GB");
        COUNTRY_NAME_TO_CODE.put("Italy", "IT");
        COUNTRY_NAME_TO_CODE.put("Spain", "ES");
        COUNTRY_NAME_TO_CODE.put("Albania", "AL");
        COUNTRY_NAME_TO_CODE.put("Andorra", "AD");
        COUNTRY_NAME_TO_CODE.put("Armenia", "AM");
        COUNTRY_NAME_TO_CODE.put("Austria", "AT");
        COUNTRY_NAME_TO_CODE.put("Azerbaijan", "AZ");
        COUNTRY_NAME_TO_CODE.put("Belarus", "BY");
        COUNTRY_NAME_TO_CODE.put("Belgium", "BE");
        COUNTRY_NAME_TO_CODE.put("Bosnia and Herzegovina", "BA");
        COUNTRY_NAME_TO_CODE.put("Bulgaria", "BG");
        COUNTRY_NAME_TO_CODE.put("Croatia", "HR");
        COUNTRY_NAME_TO_CODE.put("Cyprus", "CY");
        COUNTRY_NAME_TO_CODE.put("Czech Republic", "CZ");
        COUNTRY_NAME_TO_CODE.put("Denmark", "DK");
        COUNTRY_NAME_TO_CODE.put("Estonia", "EE");
        COUNTRY_NAME_TO_CODE.put("Finland", "FI");
        COUNTRY_NAME_TO_CODE.put("Georgia", "GE");
        COUNTRY_NAME_TO_CODE.put("Greece", "GR");
        COUNTRY_NAME_TO_CODE.put("Hungary", "HU");
        COUNTRY_NAME_TO_CODE.put("Iceland", "IS");
        COUNTRY_NAME_TO_CODE.put("Ireland", "IE");
        COUNTRY_NAME_TO_CODE.put("Kazakhstan", "KZ");
        COUNTRY_NAME_TO_CODE.put("Latvia", "LV");
        COUNTRY_NAME_TO_CODE.put("Liechtenstein", "LI");
        COUNTRY_NAME_TO_CODE.put("Lithuania", "LT");
        COUNTRY_NAME_TO_CODE.put("Luxembourg", "LU");
        COUNTRY_NAME_TO_CODE.put("Malta", "MT");
        COUNTRY_NAME_TO_CODE.put("Moldova", "MD");
        COUNTRY_NAME_TO_CODE.put("Monaco", "MC");
        COUNTRY_NAME_TO_CODE.put("Montenegro", "ME");
        COUNTRY_NAME_TO_CODE.put("Netherlands", "NL");
        COUNTRY_NAME_TO_CODE.put("Norway", "NO");
        COUNTRY_NAME_TO_CODE.put("Poland", "PL");
        COUNTRY_NAME_TO_CODE.put("Portugal", "PT");
        COUNTRY_NAME_TO_CODE.put("Romania", "RO");
        COUNTRY_NAME_TO_CODE.put("Russia", "RU");
        COUNTRY_NAME_TO_CODE.put("San Marino", "SM");
        COUNTRY_NAME_TO_CODE.put("Serbia", "RS");
        COUNTRY_NAME_TO_CODE.put("Slovakia", "SK");
        COUNTRY_NAME_TO_CODE.put("Slovenia", "SI");
        COUNTRY_NAME_TO_CODE.put("Sweden", "SE");
        COUNTRY_NAME_TO_CODE.put("Switzerland", "CH");
        COUNTRY_NAME_TO_CODE.put("Turkey", "TR");
        COUNTRY_NAME_TO_CODE.put("Ukraine", "UA");
        COUNTRY_NAME_TO_CODE.put("Vatican City", "VA");

        // Asia
        COUNTRY_NAME_TO_CODE.put("Afghanistan", "AF");
        COUNTRY_NAME_TO_CODE.put("Bahrain", "BH");
        COUNTRY_NAME_TO_CODE.put("Bangladesh", "BD");
        COUNTRY_NAME_TO_CODE.put("Bhutan", "BT");
        COUNTRY_NAME_TO_CODE.put("Brunei", "BN");
        COUNTRY_NAME_TO_CODE.put("Cambodia", "KH");
        COUNTRY_NAME_TO_CODE.put("China", "CN");
        COUNTRY_NAME_TO_CODE.put("India", "IN");
        COUNTRY_NAME_TO_CODE.put("Indonesia", "ID");
        COUNTRY_NAME_TO_CODE.put("Iran", "IR");
        COUNTRY_NAME_TO_CODE.put("Iraq", "IQ");
        COUNTRY_NAME_TO_CODE.put("Israel", "IL");
        COUNTRY_NAME_TO_CODE.put("Japan", "JP");
        COUNTRY_NAME_TO_CODE.put("Jordan", "JO");
        COUNTRY_NAME_TO_CODE.put("Kuwait", "KW");
        COUNTRY_NAME_TO_CODE.put("Kyrgyzstan", "KG");
        COUNTRY_NAME_TO_CODE.put("Laos", "LA");
        COUNTRY_NAME_TO_CODE.put("Lebanon", "LB");
        COUNTRY_NAME_TO_CODE.put("Malaysia", "MY");
        COUNTRY_NAME_TO_CODE.put("Maldives", "MV");
        COUNTRY_NAME_TO_CODE.put("Mongolia", "MN");
        COUNTRY_NAME_TO_CODE.put("Myanmar", "MM");
        COUNTRY_NAME_TO_CODE.put("Nepal", "NP");
        COUNTRY_NAME_TO_CODE.put("North Korea", "KP");
        COUNTRY_NAME_TO_CODE.put("Oman", "OM");
        COUNTRY_NAME_TO_CODE.put("Pakistan", "PK");
        COUNTRY_NAME_TO_CODE.put("Palestine", "PS");
        COUNTRY_NAME_TO_CODE.put("Philippines", "PH");
        COUNTRY_NAME_TO_CODE.put("Qatar", "QA");
        COUNTRY_NAME_TO_CODE.put("Saudi Arabia", "SA");
        COUNTRY_NAME_TO_CODE.put("Singapore", "SG");
        COUNTRY_NAME_TO_CODE.put("South Korea", "KR");
        COUNTRY_NAME_TO_CODE.put("Sri Lanka", "LK");
        COUNTRY_NAME_TO_CODE.put("Syria", "SY");
        COUNTRY_NAME_TO_CODE.put("Tajikistan", "TJ");
        COUNTRY_NAME_TO_CODE.put("Thailand", "TH");
        COUNTRY_NAME_TO_CODE.put("Timor-Leste", "TL");
        COUNTRY_NAME_TO_CODE.put("Turkmenistan", "TM");
        COUNTRY_NAME_TO_CODE.put("United Arab Emirates", "AE");
        COUNTRY_NAME_TO_CODE.put("Uzbekistan", "UZ");
        COUNTRY_NAME_TO_CODE.put("Vietnam", "VN");
        COUNTRY_NAME_TO_CODE.put("Yemen", "YE");

        // Balkans
        COUNTRY_NAME_TO_CODE.put("Bosnia and Herzegovina", "BA");
        COUNTRY_NAME_TO_CODE.put("Montenegro", "ME");
        COUNTRY_NAME_TO_CODE.put("North Macedonia", "MK");
        COUNTRY_NAME_TO_CODE.put("Serbia", "RS");

        // North Africa
        COUNTRY_NAME_TO_CODE.put("Algeria", "DZ");
        COUNTRY_NAME_TO_CODE.put("Egypt", "EG");
        COUNTRY_NAME_TO_CODE.put("Libya", "LY");
        COUNTRY_NAME_TO_CODE.put("Morocco", "MA");
        COUNTRY_NAME_TO_CODE.put("Sudan", "SD");
        COUNTRY_NAME_TO_CODE.put("Tunisia", "TN");
        COUNTRY_NAME_TO_CODE.put("Mauritania", "MR");
        COUNTRY_NAME_TO_CODE.put("Western Sahara", "EH");
        COUNTRY_NAME_TO_CODE.put("Chad", "TD");
    }



    public static String getCountryCode(String countryName) {
        return COUNTRY_NAME_TO_CODE.getOrDefault(countryName, "");
    }

}
