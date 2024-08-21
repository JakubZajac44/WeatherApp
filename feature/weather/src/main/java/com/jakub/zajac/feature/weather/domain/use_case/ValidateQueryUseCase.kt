package com.jakub.zajac.feature.weather.domain.use_case

import javax.inject.Inject

class ValidateQueryUseCase @Inject constructor(){

    operator fun invoke(oldQuery: String, newQuery: String): Boolean {
        return with(newQuery) {
            when {
                this == oldQuery -> false
                isEmpty() -> false
                else -> {
                    true
                }
            }
        }
    }
}