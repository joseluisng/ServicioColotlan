package com.joseluisng.serviciocolotlan.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.joseluisng.serviciocolotlan.R
import com.joseluisng.serviciocolotlan.modelos.Servicios
import com.joseluisng.serviciocolotlan.listeners.NotasListener
import kotlinx.android.synthetic.main.molde_notas.view.*

class NotasServicioAdapter(context: Context?, var listaDeServicios: ArrayList<Servicios>, private val listener: NotasListener) : BaseAdapter(){

    var context: Context? = context

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val notaServicio = listaDeServicios[p0]

        val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val miVista = inflater.inflate(R.layout.molde_notas, null)
        miVista.textViewTitulo.text = notaServicio.titulo
        miVista.textViewContenido.text = notaServicio.descripcion
        miVista.imageViewEditar.setOnClickListener { listener.onEdit(notaServicio, p0) }
        miVista.imageViewBorrar.setOnClickListener { listener.ondelete(notaServicio, p0) }

        /*miVista.imageViewBorrar.setOnClickListener {
            val dbManager = DBManager(this.context!!)
            val selectionArgs = arrayOf(notaServicio.servicioID.toString())

            dbManager.borrar("ID=?", selectionArgs)
            //cargarQuery("%")
        }*/

        return miVista
    }

    override fun getItem(p0: Int): Any {
        return listaDeServicios[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return listaDeServicios.size
    }


}