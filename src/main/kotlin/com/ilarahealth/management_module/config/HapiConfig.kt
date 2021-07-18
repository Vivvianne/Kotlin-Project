package com.ilarahealth.management_module.config

import com.ilarahealth.management_module.models.HapiClient
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

/**
 * Note that FhirContext is an expensive object to create, so you should try to keep an instance around for the lifetime
 * of your application. It is thread-safe so it can be passed as needed. Client instances, on the other hand, are very
 * inexpensive to create so you can create a new one for each request if needed (although there is no requirement to do
 * so, clients are reusable and thread-safe as well). -- https://hapifhir.io/hapi-fhir/docs/client/generic_client.html
 *
 *
 */
@Configuration
class HapiConfig {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    fun hapiClient(): HapiClient {
        return HapiClient("http://34.121.31.137:8080/fhir/")
    }
}