package org.slzdevsnp.restclients.common.utils;


import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UriBuilder {
    private String url;
    //args is a dictionary of ArrayLists
    private Map<String, List<Object>> args = new LinkedHashMap<>();
    private Boolean ignoreNullValues = true;
    private Map<Class, String> dateFormaters = new HashMap<>();

    private UriBuilder(String url) {
        this.url = url;
    }

    public static UriBuilder url(String url) {
        return new UriBuilder(url)
                .dateFormatter(ZonedDateTime.class, "yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    }

    public UriBuilder dateFormatter(Class clazz, String dateFormat) {
        this.dateFormaters.put(clazz, dateFormat);
        return this;
    }

    public UriBuilder ignoreNullValues(Boolean ignoreNullValues) {
        this.ignoreNullValues = ignoreNullValues;
        return this;
    }

    public UriBuilder param(String name, Object value) {
        List<Object> l = args.getOrDefault(name, new ArrayList<>());
        l.add(value);
        args.put(name, l);

        return this;
    }

    //used for formatting ZonedDateTime
    String format(Object o) {
        if (o == null) {
            return null;
        }
        String res = null;

        if (o instanceof ZonedDateTime && dateFormaters.containsKey(ZonedDateTime.class)) {
            ZonedDateTime z = (ZonedDateTime) o;
            res = z.format(DateTimeFormatter.ofPattern(dateFormaters.get(ZonedDateTime.class)));
        } else {
            res = String.valueOf(o);
        }

        return res;
    }

    public String build() {
        StringBuilder b = new StringBuilder();
        b.append(url);
        boolean first = true;
        if (!args.isEmpty()) {
            b.append("?");
            for (Map.Entry<String, List<Object>> stringListEntry : args.entrySet()) {
                StringBuilder p = new StringBuilder();
                boolean hasValues = false;
                for (Object o : stringListEntry.getValue()) {
                    if (ignoreNullValues == Boolean.TRUE && o == null) {
                        continue;
                    }
                    if (o instanceof Collection) {
                        Collection l = (Collection) o;
                        for (Object o1 : l) {
                            hasValues = true;
                            p.append("&").append(stringListEntry.getKey()).append("=").append(format(o1));
                        }
                    } else {
                        p.append("&").append(stringListEntry.getKey()).append("=").append(format(o));
                        hasValues = true;

                    }
                }
                if (hasValues) {
                    if (first) {
                        b.append(p.substring(1)); //strip off & char from the first agrument
                    } else {
                        b.append(p);
                    }
                }
                first = false;
            }
        }
        return b.toString();
    }
}
