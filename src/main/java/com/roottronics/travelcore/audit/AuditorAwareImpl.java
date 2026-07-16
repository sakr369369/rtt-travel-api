package com.roottronics.travelcore.audit;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

        @Override
        public Optional<String> getCurrentAuditor() {

            /**
             * Phase-1
             * Return SYSTEM.
             *
             * Later after JWT Authentication
             * we'll return logged-in username.
             */

            return Optional.of("SYSTEM");
        }

}
