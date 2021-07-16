package com.example.service.health;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.example.api.AuthController;
import com.example.vo.JwtToken;
import com.example.vo.NamePass;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Metrics;

@ConditionalOnProperty(prefix = "health.custom", name = "enabled", havingValue = "true")
@Service
public class AuthHealthIndicator implements HealthIndicator{

	@Autowired AuthController controller ;

	@Override
	public Health health() {
		Health.Builder builder = Health.unknown() ; //預設unknown
		//hardcode for demo, never do that!
		NamePass auth = new NamePass() ;
		auth.username = "admin" ;
		auth.password = "admin!" ;
		try {
			controller.auth(auth); //測試登入
			builder.up() ; //沒問題就設定成up
		}catch(Exception e) {
			builder.down();
			builder.withDetail("Exception", e.getMessage());
		}

		return builder.build() ;
	}

}
