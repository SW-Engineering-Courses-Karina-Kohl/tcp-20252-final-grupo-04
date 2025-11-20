package src.utils;

import java.util.Comparator;
import java.util.List;

public class BinarySearchUtils {
     public static <T> int lowerBound(List<T> list, T key, Comparator<? super T> cmp) {
        int low = 0;
        int high = list.size();

        while (low < high) {
            int mid = (low + high) >>> 1;
            if (cmp.compare(list.get(mid), key) < 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static <T> int upperBound(List<T> list, T key, Comparator<? super T> cmp) {
        int low = 0;
        int high = list.size();

        while (low < high) {
            int mid = (low + high) >>> 1;
            if (cmp.compare(list.get(mid), key) <= 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static <T> int lastIndexLE(List<T> list, T key, Comparator<? super T> cmp) {
        int ub = upperBound(list, key, cmp);
        return ub - 1;
    }
    
}
