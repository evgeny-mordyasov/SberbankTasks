package mordyasov;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Утилитный класс для класса UserSber.
 */
public final class Sbers {

    private Sbers() {
    }

    /**
     * Функция, находящая дубликатов пользователей, которые есть в обеих коллекциях.
     * @param collA - первая коллекция.
     * @param collB - вторая коллекция.
     * @returns список дубликатов пользователей, находящихся в обеих коллекциях.
     * @Throws NullPointerException, если collA или collB равны null.
     */
    public static List<UserSber> findDuplicates(Collection<UserSber> collA, Collection<UserSber> collB) {
        if (collA == null || collB == null) {
            throw new NullPointerException();
        }

        Set<UserSber> set = new HashSet<>(collA);
        Set<UserSber> resultSet = new HashSet<>();

        collB.forEach(e -> {
            if (!set.add(e)) {
                resultSet.add(e);
            }
        });

        return new ArrayList<>(resultSet);
    }

    public static List<UserSber> findDuplicates2(Collection<UserSber> collA, Collection<UserSber> collB) {
        if (collA == null || collB == null) {
            throw new NullPointerException();
        }

        Set<UserSber> set = new HashSet<>();
        Set<UserSber> setA = new HashSet<>(collA);
        Set<UserSber> setB = new HashSet<>(collB);

        set.addAll(setA);
        set.addAll(setB);

        set.removeAll(setA);
        setB.removeAll(set);

        return new ArrayList<>(setB);
    }

    public static List<UserSber> findDuplicates3(Collection<UserSber> collA, Collection<UserSber> collB) {
        if (collA == null || collB == null) {
            throw new NullPointerException();
        }

        return collA.parallelStream()
                .filter(collB::contains)
                .distinct()
                .collect(Collectors.toList());
    }
}

