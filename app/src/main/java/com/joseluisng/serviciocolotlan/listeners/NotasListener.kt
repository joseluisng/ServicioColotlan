package com.joseluisng.serviciocolotlan.listeners

import com.joseluisng.serviciocolotlan.modelos.Servicios

interface NotasListener {

    fun onEdit(servicio : Servicios, position: Int)
    fun ondelete(servicio: Servicios, position: Int)

}