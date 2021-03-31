package xzz.library.util;

public class StringListUtils {

    public static String addOne(String originStr, String target){ // 已确认源串中不存在目标子串
        if (originStr.isEmpty()) // 如果添加对象是首位元素
            return target;
        else
            return originStr + "," + target; // 非空先增加逗号再加子串
    }

    public static String removeOne(String originStr, String target){ // 已确认源串中存在目标子串
        int i = originStr.indexOf(target);
        StringBuilder sb = new StringBuilder(originStr);
        if (target.length() == originStr.length()) // 如果是唯一一个元素
            return "";
        if (i + target.length() == originStr.length()) // 如果删除对象是末尾元素
            sb.delete(i, i + target.length());
        else
            sb.delete(i, i + target.length() + 1); // 否则多删一个逗号
        return sb.toString();
    }

    public static Integer count(String originStr){
        int result = 0;
        for (int i = 0; i < originStr.length(); i++) {
            if (originStr.charAt(i) == ',')
                result++;
        }
        return result;
    }
}
