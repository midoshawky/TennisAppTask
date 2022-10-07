package com.shawky.zimozitennisapptask.data.models

data class ResponseModel<T> (
    var status : Boolean,
    var message : String,
    var data : T,
)

