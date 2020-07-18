package com.huseyinaydin.springbootconfig;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
public class DataSource {
}
