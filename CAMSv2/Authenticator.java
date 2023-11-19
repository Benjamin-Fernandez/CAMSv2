package CAMSv2;

/**
 * Interface for implementing the specific types of Authentication
 */
public interface Authenticator<T> {
    public T authenticate(String userId, String password);
}