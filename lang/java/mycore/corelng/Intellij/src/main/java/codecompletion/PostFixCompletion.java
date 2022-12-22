package codecompletion;

import java.util.Collection;

public class PostFixCompletion {
    void method(Collection<String> s){
        if (s == null) {

        }
        if (s != null) {

        }
        synchronized (s) {

        }
        try {
            s = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String el : s) {

        }

    }
}
