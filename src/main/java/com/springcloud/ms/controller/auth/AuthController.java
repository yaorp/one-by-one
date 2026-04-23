package com.springcloud.ms.controller.auth;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.springcloud.ms.controller.pattern.R;
import com.springcloud.ms.entity.AuthUser;
import com.springcloud.ms.mapper.AuthUserMapper;
import com.springcloud.ms.pojo.dto.LoginRequest;
import com.springcloud.ms.pojo.dto.LoginResponse;
import com.springcloud.ms.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthUserMapper authUserMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthUserMapper authUserMapper,
                          PasswordEncoder passwordEncoder,
                          JwtTokenProvider jwtTokenProvider) {
        this.authUserMapper = authUserMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<R<LoginResponse>> login(@RequestBody LoginRequest request) {
        if (request == null
                || !StringUtils.hasText(request.getUsername())
                || !StringUtils.hasText(request.getPassword())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(R.fail("400", "用户名和密码不能为空"));
        }
        AuthUser authUser = authUserMapper.selectOne(
                new LambdaQueryWrapper<AuthUser>().eq(AuthUser::getUsername, request.getUsername())
        );
        if (authUser == null || authUser.getPassword() == null
                || !passwordEncoder.matches(request.getPassword(), authUser.getPassword())) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(R.fail("401", "用户名或密码错误"));
        }
        String token = jwtTokenProvider.generateToken(authUser.getId(), authUser.getUsername());
        long expireAt = System.currentTimeMillis() + jwtTokenProvider.getExpireMillis();
        return ResponseEntity.ok(R.success(new LoginResponse(token, expireAt)));
    }
}
