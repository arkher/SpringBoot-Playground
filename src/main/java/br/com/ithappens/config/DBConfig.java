package br.com.ithappens.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("br.com.ithappens.mapper")
public class DBConfig {
}