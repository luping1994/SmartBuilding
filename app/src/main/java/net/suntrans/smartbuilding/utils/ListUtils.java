package net.suntrans.smartbuilding.utils;

import java.util.List;

/**
 * Created by Administrator on 2017/8/14.
 */

public class ListUtils {
    public static boolean isEmpty(List list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }
}
