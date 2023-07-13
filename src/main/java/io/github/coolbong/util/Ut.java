package io.github.coolbong.util;

public class Ut {

    public String removeComment(String code) {
        return code.replaceAll("//.*|/\\*((.|\\n)(?!=*/))+\\*/", "");
    }

    public String removeDuplicateSpace(String code) {
        return code.replaceAll("\\s+", " ");
    }

}
