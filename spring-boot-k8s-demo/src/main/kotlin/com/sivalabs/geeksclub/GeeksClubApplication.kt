package com.sivalabs.geeksclub

import com.sivalabs.geeksclub.config.SpringApplicationContextInitializer
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.annotation.Bean

@SpringBootApplication
class GeeksClubApplication {

    @Bean
    fun commonTags(): MeterRegistryCustomizer<MeterRegistry> {
        return MeterRegistryCustomizer { registry ->
                registry.config().commonTags("application", "geeksclub")
        }

    }
}

fun main(args: Array<String>) {
    //runApplication<GeeksClubApplication>(*args)
    SpringApplicationBuilder(GeeksClubApplication::class.java)
            .initializers(SpringApplicationContextInitializer())
            .run( *args)
}
