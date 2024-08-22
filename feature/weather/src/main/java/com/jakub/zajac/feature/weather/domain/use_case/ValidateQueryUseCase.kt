package com.jakub.zajac.feature.weather.domain.use_case

import java.util.regex.Pattern
import javax.inject.Inject

class ValidateQueryUseCase @Inject constructor() {

    operator fun invoke(query: String): ValidationQueryStatus {
        val pattern = Pattern.compile("^[a-zA-Zążźćśęó ]*")
        return with(query) {
            when {
                contains("[0-9]".toRegex()) -> ValidationQueryStatus.QueryContainNumber
                contains("[!\"#$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex()) -> ValidationQueryStatus.QueryContainSpecialChar
                pattern.matcher(query).matches() -> ValidationQueryStatus.QueryCorrect
                else -> ValidationQueryStatus.QueryDefault
            }
        }
    }
}


sealed class ValidationQueryStatus {
    data object QueryCorrect : ValidationQueryStatus()
    data object QueryContainNumber : ValidationQueryStatus()
//    data object QueryEmpty : ValidationQueryStatus()
    data object QueryContainSpecialChar : ValidationQueryStatus()
    data object QueryDefault : ValidationQueryStatus()
}