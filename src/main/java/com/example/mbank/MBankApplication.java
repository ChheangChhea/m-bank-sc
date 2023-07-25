package com.example.mbank;

import com.example.mbank.base.BaseJpaRepositoryImp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(repositoryBaseClass = BaseJpaRepositoryImp.class)
@SpringBootApplication
public class MBankApplication  {

    public static void main(String[] args) {
        SpringApplication.run(MBankApplication.class, args);
    }

}
