package arc_checker;

public class EmptyDomainException extends Exception {
    public EmptyDomainException(String emptyDomain) {
        super(emptyDomain);
    }
}
