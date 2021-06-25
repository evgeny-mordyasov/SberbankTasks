package mordyasov.exceptions;

/**
 * Класс AnnotationException, необходимый для сигналирования о том, что допущена ошибка в работе с аннотациями.
 */
public class AnnotationException extends RuntimeException {
    public AnnotationException(String message) {
        super(message);
    }
}
