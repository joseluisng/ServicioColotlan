package com.joseluisng.serviciocolotlan.fragments


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.joseluisng.serviciocolotlan.DBManager

import com.joseluisng.serviciocolotlan.R
import com.joseluisng.serviciocolotlan.modelos.Servicios
import com.joseluisng.serviciocolotlan.activities.AddActivity
import com.joseluisng.serviciocolotlan.listeners.NotasListener
import kotlinx.android.synthetic.main.fragment_agrega_cont.view.*
import kotlinx.android.synthetic.main.molde_notas.view.*


class AgregaContFragment : Fragment() {

    var listaDeServicios = ArrayList<Servicios>()

    var listV: ListView? = null
    var imageViewBorrar: ImageView? = null
    private lateinit var adapter: NotasServiAdapter



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val miVista = inflater.inflate(R.layout.fragment_agrega_cont, container, false)

        listV = miVista.findViewById(R.id.listView) as ListView


        /* listaDeServicios.add(Servicios(1, "Plomero", "Reparación de tubería"))
         listaDeServicios.add(Servicios(2, "Carpintero", "Realización de una mesa"))
         listaDeServicios.add(Servicios(3, "Albañil", "Poner piso en un cuarto"))

         val adapter = NotasServicioAdapter(context, listaDeServicios)
         listV.adapter = adapter*/

        cargarQuery("%")

        adapter = NotasServiAdapter(context, listaDeServicios, object : NotasListener{
            override fun onEdit(servicio: Servicios, position: Int) {
                //Toast.makeText(context, "Editado", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, AddActivity::class.java)
                intent.putExtra("ID", servicio.servicioID)
                intent.putExtra("titulo", servicio.titulo)
                intent.putExtra("descrip", servicio.descripcion)
                startActivity(intent)
            }

            override fun ondelete(servicio: Servicios, position: Int) {

               /* val dbManager = DBManager(context!!)
                val selectionArgs = arrayOf(servicio.servicioID.toString())

                dbManager.borrar("ID=?", selectionArgs)
                adapter.notifyDataSetChanged()

                cargarQuery("%")*/

            }
        })
        listV!!.adapter = adapter

        miVista.fab.setOnClickListener {
            val intent = Intent(context, AddActivity::class.java)
            startActivity(intent)
        }


        return miVista
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
        cargarQuery("%")
    }




    // SELECT el ID, Titulo y Descripción FROM de la tabla (nombre de la tabla) WHERE nombre de titulo que le llega (Titulo like ?) y ORDER BY por Titulo
    fun cargarQuery(titulo: String){
        var baseDatos = DBManager(this.context!!)
        val columnas = arrayOf("ID", "Titulo", "Descripcion")
        val selectionArgs = arrayOf(titulo)
        val cursor = baseDatos.query(columnas, "Titulo like ?", selectionArgs, "Titulo")

        listaDeServicios.clear()

        if(cursor.moveToFirst()){
            do {
                val ID = cursor.getInt(cursor.getColumnIndex("ID"))
                val titulo = cursor.getString(cursor.getColumnIndex("Titulo"))
                val descripcion = cursor.getString(cursor.getColumnIndex("Descripcion"))

                listaDeServicios.add(Servicios(ID, titulo, descripcion))

            }while (cursor.moveToNext())
        }

    }

    // ADAPTADOR PARA LLENAR LA LISTA DE NOTAS DE SERVICIOS
    inner class NotasServiAdapter(context: Context?, var listaDeServicios: ArrayList<Servicios>, private val listener: NotasListener) : BaseAdapter(){

        var context: Context? = context

        override fun getView(p0: Int, view: View?, p2: ViewGroup?): View {
            //las siguientes lineas es para usar unas listas recicladas
            var convertView: View ? = view
            if (convertView == null){
                convertView = View.inflate(context, R.layout.molde_notas, null)
            }

            val notaServicio = listaDeServicios[p0]

            //Comentamos estalinea ya que vamos a usar unas listas recicladas
            /*val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val miVista = inflater.inflate(R.layout.molde_notas, null)*/
            val miVista = convertView!!
            miVista.textViewTitulo.text = notaServicio.titulo
            miVista.textViewContenido.text = notaServicio.descripcion
            miVista.imageViewEditar.setOnClickListener { listener.onEdit(notaServicio, p0) }

            miVista.imageViewBorrar.setOnClickListener {
                val dbManager = DBManager(context!!)
                val selectionArgs = arrayOf(notaServicio.servicioID.toString())

                dbManager.borrar("ID=?", selectionArgs)
                adapter.notifyDataSetChanged()

                cargarQuery("%")
            }


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
    
}
