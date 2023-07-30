package mordyasov;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.stream.IntStream;

class SbersTestTime {

    private Collection<UserSber> collection1;
    private Collection<UserSber> collection2;
    long startTime;

    @BeforeEach
    void setUp() {
        int collectionType = 2;

        collection1 = getCollection(collectionType);
        collection2 = getCollection(collectionType);

        IntStream.rangeClosed(0, 99_999).forEach(e -> {
            UserSber userSber = new UserSber("Steve" + e, "ru");

            collection1.add(userSber);
            collection2.add(userSber);
        });

        startTime = System.currentTimeMillis();
    }

    @AfterEach
    void tearDown() {
        System.out.println(System.currentTimeMillis() - startTime + " ms");
    }

    @Test
    void checkingTimeWait1() {
        assertTimeoutPreemptively(Duration.ofMillis(200),
                () -> assertEquals(100_000, Sbers.findDuplicates(collection1, collection2).size()));
    }

    @Test
    void checkingTimeWait2() {
        assertTimeoutPreemptively(Duration.ofMillis(200),
                () -> assertEquals(100_000, Sbers.findDuplicates2(collection1, collection2).size()));
    }

    @Test
    void checkingTimeWait3() {
        assertTimeoutPreemptively(Duration.ofMillis(200),
                () -> assertEquals(100_000, Sbers.findDuplicates3(collection1, collection2).size()));
    }

    /**
     * Функция, возвращающая определенную реализацию Collection.
     * @param choice - выбор реализации.
     * @returns:
     * 1. Объект класса ArrayList, если choice равно 0.
     * 2. Объект класса LinkedList, если choice равно 1.
     * 3. Объект класса HashSet, если choice не равно 0 или 1.
     */
    private Collection<UserSber> getCollection(int choice) {
        return switch (choice) {
            case 0 -> new ArrayList<>();
            case 1 -> new LinkedList<>();
            default -> new HashSet<>();
        };
    }
}
