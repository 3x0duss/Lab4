package ex0;

import org.javatuples.Pair;
import java.util.HashSet;
import java.util.Set;

/**
 * Сущность подразделение
 */
public class Division {
    private static final Set<Pair<Integer, String>> names = new HashSet<>();
    private static int IDCount = 1;
    private final int ID;
    private String name;

    /**
     * Конструктор с параметром
     * @param name имя подразделения
     * @throws NullPointerException ссылка не указывает на строку
     */
    public Division(String name) {
        if (name == null) {
            throw new NullPointerException();
        }

        boolean isContained = false;
        int nextID = IDCount;

        for (Pair<Integer, String> pair: names) {
            if (name.equals(pair.getValue1())) {
                nextID = pair.getValue0();
                isContained = true;

                break;
            }
        }

        if (!isContained) {
            names.add(Pair.with(nextID, name));
            ++IDCount;
        }

        ID = nextID;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[ID: " + ID + " name: " + name + "]";
    }
}
