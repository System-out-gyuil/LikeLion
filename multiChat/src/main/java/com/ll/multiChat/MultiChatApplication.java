package com.ll.multiChat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 활성화, 생성일 및 수정일 자동화
@SpringBootApplication
public class MultiChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiChatApplication.class, args);
	}

}