import java.util.Map;

public class Laptop {
    Integer ram;
    Integer ssd;
    Integer os;
    Integer color;

    public boolean compareWith(Map<String, Integer> Criterias) {
        if (Criterias.containsKey("ram") == true) {
            if (ram != (int)Criterias.get("ram")) {
                return false;
            }
        }
        if (Criterias.containsKey("ssd") == true) {
            if (ssd != (int)Criterias.get("ssd")) {
                return false;
            }
        }
        if (Criterias.containsKey("os") == true) {
            if (os != (int)Criterias.get("os")) {
                return false;
            }
        }
        if (Criterias.containsKey("color") == true) {
            if (color != (int)Criterias.get("color")) {
                return false;
            }
        }
        return true;
    }
}