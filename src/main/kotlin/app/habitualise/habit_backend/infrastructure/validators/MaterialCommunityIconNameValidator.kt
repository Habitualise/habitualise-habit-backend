package app.habitualise.habit_backend.infrastructure.validators

import app.habitualise.habit_backend.domain.services.IconNameValidator
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class MaterialCommunityIconNameValidator : IconNameValidator {
    private val restTemplate = RestTemplate()

    init {
        val converter = MappingJackson2HttpMessageConverter()
        converter.supportedMediaTypes = listOf(MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON)
        restTemplate.messageConverters.add(converter)
    }

    private val ICON_LIST_URL =
        "https://raw.githubusercontent.com/oblador/react-native-vector-icons/master/glyphmaps/MaterialCommunityIcons.json"
    private val responseType = object : ParameterizedTypeReference<Map<String, Int>>() {}
    private val responseEntity = restTemplate.exchange(ICON_LIST_URL, HttpMethod.GET, null, responseType)
    private val iconMap = responseEntity.body?.also {
        if (it.isEmpty()) {
//            log.warn("The response body is empty!") Add logging when we have a logger
            println("The response body is empty!")
        }
    } ?: run {
//        log.warn("The response body is null!") Add logging when we have a logger
        println("The response body is null!")
        emptyMap()
    }

    override fun isValid(iconName: String): Boolean {
        return iconMap.containsKey(iconName)
    }

}
