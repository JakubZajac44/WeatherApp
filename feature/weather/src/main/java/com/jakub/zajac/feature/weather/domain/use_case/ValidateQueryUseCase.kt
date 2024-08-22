package com.jakub.zajac.feature.weather.domain.use_case

import java.util.regex.Pattern
import javax.inject.Inject

class ValidateQueryUseCase @Inject constructor() {

    operator fun invoke(query: String): ValidationQueryStatus {
        val pattern = Pattern.compile(CHAR_WITH_POLISH_SPECIAL_CHAR_PATTERN)
        return with(query) {
            when {
                contains(DIGIT_PATTERN.toRegex()) -> ValidationQueryStatus.QueryContainNumber
                contains(SPECIAL_CHAR_PATTERN.toRegex()) -> ValidationQueryStatus.QueryContainSpecialChar
                pattern.matcher(query).matches() -> ValidationQueryStatus.QueryCorrect
                else -> ValidationQueryStatus.QueryDefault
            }
        }
    }

    companion object {
        const val CHAR_WITH_POLISH_SPECIAL_CHAR_PATTERN = "^[a-zA-Zążźćśęó ]*"
        const val DIGIT_PATTERN = "[0-9]"
        const val SPECIAL_CHAR_PATTERN = "[!\"#$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]"

    }

}


sealed class ValidationQueryStatus {
    data object QueryCorrect : ValidationQueryStatus()
    data object QueryContainNumber : ValidationQueryStatus()
    data object QueryContainSpecialChar : ValidationQueryStatus()
    data object QueryDefault : ValidationQueryStatus()
}