package com.joseluisng.serviciocolotlan.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.joseluisng.serviciocolotlan.R
import com.joseluisng.serviciocolotlan.modelos.Recomendaciones
import kotlinx.android.synthetic.main.modelo_recomendacion.view.*

class AdapterSugerencias(contexto : Context, var listaDeRecomendaciones: ArrayList<Recomendaciones>): BaseAdapter(){

    var contexto: Context? = contexto

    override fun getView(p0: Int, view: View?, p2: ViewGroup?): View {

        //las siguientes lineas es para recyclar la vista
        var convertView: View ? = view
        if (convertView == null){
            convertView = View.inflate(contexto, R.layout.modelo_recomendacion, null)
        }

        val listRecomendaciones = listaDeRecomendaciones[p0]
        //Comentamos estalinea ya que vamos a usar unas listas recicladas
        //val inflater = contexto!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        //val miVista = inflater.inflate(R.layout.modelo_recomendacion, null)
        val miVista = convertView!!
        miVista.imagenView.setImageResource(listRecomendaciones.imagen!!)
        miVista.textViewTitulo.text = listRecomendaciones.titulo!!
        miVista.textViewDescripcion.text = listRecomendaciones.descripcion!!
        miVista.textViewTelefono.text = listRecomendaciones.telefono!!

        return miVista

    }

    override fun getItem(p0: Int) = listaDeRecomendaciones[p0]


    override fun getItemId(p0: Int) = p0.toLong()

    override fun getCount(): Int {
        return listaDeRecomendaciones.size
    }

}