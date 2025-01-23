package worldcup.util.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Long id) {
        super("Could not find entity with id " + id);
    }
}
