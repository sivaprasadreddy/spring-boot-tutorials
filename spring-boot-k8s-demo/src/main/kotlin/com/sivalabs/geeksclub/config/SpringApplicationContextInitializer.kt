package com.sivalabs.geeksclub.config

import org.slf4j.LoggerFactory
import org.springframework.cloud.Cloud
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.cloud.CloudException
import org.springframework.cloud.CloudFactory

class SpringApplicationContextInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {

    private val logger = LoggerFactory.getLogger(SpringApplicationContextInitializer::class.java)

    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        val cloud = getCloud()
        val appEnvironment = applicationContext.environment
        if (cloud != null) {
            appEnvironment.addActiveProfile("cloud")
            logger.info("Cloud profile active")
        }
    }

    private fun getCloud(): Cloud? {
        return try {
            val cloudFactory = CloudFactory()
            cloudFactory.cloud
        } catch (ce: CloudException) {
            null
        }
    }
}