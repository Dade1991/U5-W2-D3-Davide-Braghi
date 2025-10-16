package davidebraghi.U5_W2_D4_Davide_Braghi.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(int id) {
        super(id + " not found.");
    }
}
