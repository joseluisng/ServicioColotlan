package com.joseluisng.serviciocolotlan.modelos

class Servicios{

    var servicioID : Int? = null
    var titulo : String? = null
    var descripcion : String? = null

    constructor(servicioID : Int, titulo: String, descripcion: String){

        this.servicioID = servicioID
        this.titulo = titulo
        this.descripcion = descripcion
    }
}