////package com.dyes.bank.initializer;
////
////import org.slf4j.Logger;
////import org.slf4j.LoggerFactory;
////import org.springframework.boot.CommandLineRunner;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////
////import com.dyes.bank.user.User;
////import com.dyes.bank.repository.UserRepository;
////
////@Configuration
////public class LoadDatabase {
////
////    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
////
////    @Bean
////    CommandLineRunner initDatabase(UserRepository repository) {
////        return args -> {
////            log.info("Preloading " + repository.save(new User("Jane Doe", 2345)));
////            log.info("Preloading " + repository.save(new User("John Goggins", 1738)));
////        };
////    }
////}

