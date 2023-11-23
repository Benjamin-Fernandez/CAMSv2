package CAMSv2;

/**
 * Interface for implementing the specific types of Authentication
 */
public interface Authenticator<T> {
    /**
     * Authenticates the user based on the provided user ID and password.
     *
     * @param userId   The user ID or username for authentication.
     * @param password The password associated with the user ID.
     * @return A generic type representing the authenticated user if successful, otherwise null.
     */
    public T authenticate(String userId, String password);
}