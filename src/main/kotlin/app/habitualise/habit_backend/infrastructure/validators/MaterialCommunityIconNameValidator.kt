package app.habitualise.habit_backend.infrastructure.validators

import app.habitualise.habit_backend.domain.services.IconNameValidator
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate

class MaterialCommunityIconNameValidator : IconNameValidator {
    private val restTemplate = RestTemplate()
    private val url =
        "https://raw.githubusercontent.com/oblador/react-native-vector-icons/master/glyphmaps/MaterialCommunityIcons.json"
    private val responseType = object : ParameterizedTypeReference<Map<String, Int>>() {}
    private val responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, responseType)
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
