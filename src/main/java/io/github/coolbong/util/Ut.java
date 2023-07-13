package io.github.coolbong.util;

public class Ut {

    public static String removeComment(String code) {
        return code.replaceAll("//.*|/\\*((.|\\n)(?!=*/))+\\*/", "");
    }

    public static String removeDuplicateSpace(String code) {
        return code.replaceAll("\\s+", " ");
    }

}
