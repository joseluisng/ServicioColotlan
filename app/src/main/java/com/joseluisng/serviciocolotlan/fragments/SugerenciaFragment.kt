package com.joseluisng.serviciocolotlan.fragments


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView

import com.joseluisng.serviciocolotlan.R
import com.joseluisng.serviciocolotlan.adapter.AdapterSugerencias
import com.joseluisng.serviciocolotlan.modelos.Recomendaciones
import kotlinx.android.synthetic.main.modelo_recomendacion.view.*


class SugerenciaFragment : Fragment() {



    var listaRecomendaciones = ArrayList<Recomendaciones>()

    var listV: ListView? = null
    private lateinit var adapter: AdapterSugerencias


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val miVista = inflater.inflate(R.layout.fragment_sugerencia, container, false)

        listV = miVista.findViewById(R.id.listViewSugerencia) as ListView

        listaRecomendaciones.add(Recomendaciones(R.drawable.cintos, "Talabarteria", "Bordado Fino de cintos, Monturas, etc.", "3311358433"))
        listaRecomendaciones.add(Recomendaciones(R.drawable.capintero, "Carpinteria", "Realizamos  tipo de mueble con madera ", "3311358433"))
        listaRecomendaciones.add(Recomendaciones(R.drawable.plomero, "Plomero", "Mantenimiento a tuberias", "3311358433"))
        listaRecomendaciones.add(Recomendaciones(R.drawable.jardinero, "Jardinero", "Podado de cesped y plantado de arboles", "3311358433"))
        listaRecomendaciones.add(Recomendaciones(R.drawable.mecanico, "Mecanico en general", "Mantenimiento y reparación de vehiculos", "3311358433"))
        listaRecomendaciones.add(Recomendaciones(R.drawable.pintor, "Pintor", "Pinturamos su casa con la mejor calidad de pintura", "3311358433"))
        listaRecomendaciones.add(Recomendaciones(R.drawable.servicio_colotlan, "Electricista", "Diseño de red electrica e instalación", "3311358433"))


        adapter = AdapterSugerencias(this.context!!, listaRecomendaciones)
        listV!!.adapter = adapter


        return miVista
    }
}
