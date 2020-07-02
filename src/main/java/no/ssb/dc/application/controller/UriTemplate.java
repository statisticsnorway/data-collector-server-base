package no.ssb.dc.application.controller;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UriTemplate {

    private static final Pattern PATH_GROUPS_PATTERN = Pattern.compile("(?:[^}{/]+|\\{[^}{]+})+");

    private final String path;
    private final List<String> pathElements;
    private final Map<String, Integer> variableNamesAndElementPosition = new LinkedHashMap<>();

    public UriTemplate(String uri) {
        this.path = uri;
        pathElements = parse(uri);
    }

    private List<String> parse(String uri) {
        List<String> elements = new LinkedList<>();
        Matcher matcher = PATH_GROUPS_PATTERN.matcher(uri);
        int i = 0;
        while (matcher.find()) {
            String element = matcher.group();
            elements.add(element);
            if (element.startsWith("{") && element.endsWith("}")) {
                variableNamesAndElementPosition.put(element.substring(1, element.length() - 1), i);
            }
            i++;
        }
        return elements;
    }

    public String path() {
        return path;
    }

    public List<String> elements() {
        return pathElements;
    }

    public Set<String> variableNames() {
        return variableNamesAndElementPosition.keySet();
    }

    public Integer variableIndex(String name) {
        if (!variableNamesAndElementPosition.containsKey(name)) {
            return -1;
        }
        return variableNamesAndElementPosition.get(name);
    }

    public int size() {
        return pathElements.size();
    }
}