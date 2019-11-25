package pl.edu.wat.bookthevisit;

public class EmailExistsException extends Throwable
{
    public EmailExistsException(String message) {
        super(message);
    }
}
