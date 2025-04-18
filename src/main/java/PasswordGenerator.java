import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Ensure Spring Security dependency is added

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "password123"; // Sostituisci con la tua password
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Password crittografata: " + encodedPassword);
    }
}
