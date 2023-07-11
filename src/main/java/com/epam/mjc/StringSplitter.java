package com.epam.mjc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        ArrayList<String> res = new ArrayList<>();
        res.add(source);
        for (String delimiter: delimiters) {
            ArrayList<String> list = new ArrayList<>();
            for (String r: res) {
                list.addAll(Arrays.asList(r.split(delimiter)));
            }
            res = list;
        }
        res.removeIf(a -> a.equals(""));
        return res;
    }
}
