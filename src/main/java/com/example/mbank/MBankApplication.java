package com.example.mbank;

import com.example.mbank.base.BaseJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(repositoryBaseClass = BaseJpaRepositoryImpl.class)
@SpringBootApplication

public class MBankApplication  {

    public static void main(String[] args) {
        SpringApplication.run(MBankApplication.class, args);
    }

}
