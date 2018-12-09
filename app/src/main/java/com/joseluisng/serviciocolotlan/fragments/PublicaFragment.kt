package com.joseluisng.serviciocolotlan.fragments


import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.*
import android.widget.SearchView
import android.widget.Toast

import com.joseluisng.serviciocolotlan.R
import com.joseluisng.serviciocolotlan.activities.AddActivity
import com.joseluisng.serviciocolotlan.adapter.PageAdapter


class PublicaFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val miVista = inflater.inflate(R.layout.fragment_publica, container, false)
        // Inflate the layout for this fragment


        //Agregando la vista del Tab y cuantos tabs van a ser
        val tabLayout : TabLayout = miVista.findViewById<View>(R.id.tabLayout) as TabLayout
        tabLayout.addTab(tabLayout.newTab().setText("publica"))
        tabLayout.addTab(tabLayout.newTab().setText("Sugerencia"))

        //Agregando la vista del deslizamiento de pantalla
        val vPager: ViewPager = miVista.findViewById<View>(R.id.viewPager) as ViewPager
        //Creando el objeto de el adaptador, para el cambio de los fragment
        val pAdapter = PageAdapter(childFragmentManager, tabLayout.tabCount)

        //Agregando el adaptador a el Viewpager
        vPager.adapter = pAdapter
        //la linea permite saber cuando se cambio de fragment
        vPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        //esta linea es para saber que tab se selecciono o en que tab se encuentra y en que posición esta el tab.
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab) {
                // Toast.makeText(context, "tab: "+ tab.text, Toast.LENGTH_SHORT).show()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                //Toast.makeText(context, "tab: "+ tab.text, Toast.LENGTH_SHORT).show()
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val posicion = tab!!.position
                vPager.currentItem = posicion
            }
        })

        setHasOptionsMenu(true)

        return miVista
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {

        inflater!!.inflate(R.menu.menu_add, menu)

        val buscar = menu!!.findItem(R.id.app_bar_search).actionView as SearchView
        val manejador = this.activity!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        buscar.setSearchableInfo(manejador.getSearchableInfo(this.activity!!.componentName))
        buscar.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0 : String?): Boolean {
                Toast.makeText(context, p0, Toast.LENGTH_SHORT).show()
                //cargarQuery("%" + p0 + "%")
                return false
            }

            override fun onQueryTextChange(p0 : String?): Boolean {
                return false
            }
        })


        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId){
            R.id.menuAgregar ->{
                val intent = Intent(context, AddActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
