package co.istad.mbank.api.auth1;

import co.istad.mbank.api.auth1.web.AuthDto;
import co.istad.mbank.api.auth1.web.LoginDto;
import co.istad.mbank.api.auth1.web.RefreshTokenDto;

public interface AuthService {

    AuthDto login(LoginDto loginDto);
    AuthDto refresh(RefreshTokenDto refreshTokenDto);

}
