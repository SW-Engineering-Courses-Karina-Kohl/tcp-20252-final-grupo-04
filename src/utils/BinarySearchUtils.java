package src.utils;

import java.util.Comparator;
import java.util.List;
import org.tinylog.Logger;

public class BinarySearchUtils {

    
    /** 
        * Retorna o menor índice i tal que list.get(i) >= key
        * @return índice do menor elemento maior ou igual a key
    */ 
    public static <T> int lowerBound(List<T> list, T key, Comparator<? super T> cmp) {
        if(list == null)
        {
            Logger.error("Erro em lowerBound: lista nula");
            throw new IllegalArgumentException("Lista não pode ser nula");
        }
        if(key == null)
        {
            Logger.error("Erro em lowerBound: chave nula");
            throw new IllegalArgumentException("Chave não pode ser nula");
        }
        if(cmp == null)
        {
            Logger.error("Erro em lowerBound: comparador nulo");
            throw new IllegalArgumentException("Comparador não pode ser nulo");
        }
            
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

    /** 
        * Retorna o menor índice i tal que list.get(i) > key
        * @return índice do menor elemento maior que key
    */
    public static <T> int upperBound(List<T> list, T key, Comparator<? super T> cmp) {
        if(list == null)
        {
            Logger.error("Erro em upperBound: lista nula");
            throw new IllegalArgumentException("Lista não pode ser nula");
        }
        if(key == null)
        {
            Logger.error("Erro em upperBound: chave nula");
            throw new IllegalArgumentException("Chave não pode ser nula");
        }
        if(cmp == null)
        {
            Logger.error("Erro em upperBound: comparador nulo");
            throw new IllegalArgumentException("Comparador não pode ser nulo");
        }
        
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

    /** 
        * Retorna o maior índice i tal que list.get(i) <= key
        * @return índice do maior elemento menor ou igual a key
    */
    public static <T> int lastIndexLE(List<T> list, T key, Comparator<? super T> cmp) {
        if(list == null)
        {
            Logger.error("Erro em lastIndexLE: lista nula");
            throw new IllegalArgumentException("Lista não pode ser nula");
        }
        if(key == null)
        {
            Logger.error("Erro em lastIndexLE: chave nula");
            throw new IllegalArgumentException("Chave não pode ser nula");
        }
        if(cmp == null)
        {
            Logger.error("Erro em lastIndexLE: comparador nulo");
            throw new IllegalArgumentException("Comparador não pode ser nulo");
        }

        int ub = upperBound(list, key, cmp);
        return ub - 1;
    }
    
}
