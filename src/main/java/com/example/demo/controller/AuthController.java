@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;

    public AuthController() {
        this.jwtTokenProvider =
            new JwtTokenProvider("VerySecretKeyForJwtDemo1234567890");
    }

    @PostMapping("/login")
    public String login() {
        return "Login success (demo)";
    }
}
