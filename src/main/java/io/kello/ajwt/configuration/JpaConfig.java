package io.kello.ajwt.configuration;

import javax.persistence.MappedSuperclass;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@MappedSuperclass
@EnableJpaAuditing
@Configuration
public class JpaConfig {
}
