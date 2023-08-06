package co.istad.mbank.api.auth1;

import co.istad.mbank.api.auth1.web.AuthDto;
import co.istad.mbank.api.auth1.web.LoginDto;

public interface AuthService {

    AuthDto login(LoginDto loginDto);

}
