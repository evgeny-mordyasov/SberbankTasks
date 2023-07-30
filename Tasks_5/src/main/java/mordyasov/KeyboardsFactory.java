package mordyasov;

/**
 * Интерфейс KeyboardsFactory, являющийся абстрактной фабрикой и содержащий в себе два метода создания проводных и беспроводных клаваитур.
 */
public interface KeyboardsFactory {

    Wired createWired();
    Wireless createWireless();
}
