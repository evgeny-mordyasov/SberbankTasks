package mordyasov;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

class SbersTest {

    private List<UserSber> users = new ArrayList<>();

    /**
     * Поле, отвечающее за то, какой утилитный метод в UserSber вызывать.
     * Если CHOICE == 1, то вызовется UserSber.findDuplicates(Collection, Collection).
     * Если CHOICE == 2, то вызовется UserSber.findDuplicates2(Collection, Collection).
     * Если CHOICE == любое другое число, то вызовется UserSber.findDuplicates3(Collection, Collection).
     * @see #method(Collection, Collection).
     */
    private final int CHOICE = 0;

    @BeforeEach
    void setUp() {
        IntStream.rangeClosed(0, 9).forEach(e -> users.add(new UserSber("Steve" + e, "ru")));
    }

    @Test
    void test() {
        assertEquals("[Steve2:ru]", method(users.subList(0, 3), users.subList(2, 5)).toString());
    }

    @Test
    void test1() {
        assertEquals("[]", method(users.subList(1, 5), users.subList(5, 9)).toString());
    }

    @Test
    void test2() {
        assertEquals("[Steve3:ru, Steve4:ru, Steve5:ru]", method(users.subList(0, 9), users.subList(3, 6)).toString());
    }

    @Test
    void test3() {
        users.add(new UserSber("Steve10", "ru"));
        users.add(new UserSber("Steve10", "ru"));

        assertEquals("[Steve10:ru]", method(users.subList(10, 12), users.subList(10, 12)).toString());
    }

    private List<UserSber> method(Collection<UserSber> collA, Collection<UserSber> collB) {
        return switch (CHOICE) {
            case 1 -> Sbers.findDuplicates(collA, collB);
            case 2 -> Sbers.findDuplicates2(collA, collB);
            default -> Sbers.findDuplicates3(collA, collB);
        };
    }
}
