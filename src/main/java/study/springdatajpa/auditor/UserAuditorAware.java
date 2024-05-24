package study.springdatajpa.auditor;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.UUID;

public class UserAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // Spring Security의 SecurityContextHolder를 사용하여 현재 인증된 사용자의 세션 정보를 가져옵니다.
        // 여기서는 간단히 사용자 이름을 반환하도록 하겠습니다.
        String username = UUID.randomUUID().toString();
//                SecurityContextHolder.getContext().getAuthentication().getName();
        return Optional.of(username);
    }

}
