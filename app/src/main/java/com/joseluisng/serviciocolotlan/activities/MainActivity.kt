package com.joseluisng.serviciocolotlan.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.joseluisng.serviciocolotlan.R
import com.joseluisng.serviciocolotlan.fragments.PerfilFragment
import com.joseluisng.serviciocolotlan.fragments.PublicaFragment
import com.joseluisng.serviciocolotlan.fragments.EmailFragment

class MainActivity : AppCompatActivity() {

    var drawerLayout: DrawerLayout? = null
    var navigationView: NavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigationView)


        //Agregando el toolbar que diseñamos
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        //Agregando el icono al toolba y habilitandolo
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_hamburguesa)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        //Condición para cuando rotas a Landscape el telefono no cambie de estado o de pantalla.
        //Hacemos condición de si al crear la activididad el estado de la instancia es nulo entra y selecciona el primerfragmen(PublicaFragment)
        //si no es nullo y ya esta la instancia en algun otro fragment no entra y al rotar el celular se queda en el fragment que esta seleccionado o en le fragment que se encontraba
        if (savedInstanceState == null){
            //Función que permite que la aplicación inicie en un framento en especifico
            cambiarFragmento(PublicaFragment(), navigationView!!.menu.getItem(0))
        }


        //Para ver el estado de cuando sierra o abre el drawer
        drawerLayout!!.addDrawerListener(object : DrawerLayout.DrawerListener{

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {

            }

            override fun onDrawerOpened(drawerView: View) {
                //Toast.makeText(this@MainActivity, "Open", Toast.LENGTH_SHORT).show()
            }

            override fun onDrawerClosed(drawerView: View) {
                //Toast.makeText(this@MainActivity, "Close", Toast.LENGTH_SHORT).show()
            }

            override fun onDrawerStateChanged(newState: Int) {

            }

        })

        //Cuando seleccionamos una opción en el Drawer
        navigationView!!.setNavigationItemSelectedListener {item ->

            var gestorDeFragmentos = false
            var fragment: Fragment? = null

            when(item.itemId){

                R.id.menu_publica_servicio ->{
                    fragment = PublicaFragment()
                    gestorDeFragmentos = true
                }
                R.id.menu_correo ->{
                    fragment = EmailFragment()
                    gestorDeFragmentos = true
                }
                R.id.menu_perfil ->{
                    fragment = PerfilFragment()
                    gestorDeFragmentos = true
                }
                R.id.menu_borrar ->{
                   Toast.makeText(this, "Borrar", Toast.LENGTH_SHORT).show()
                }
                R.id.menu_ayuda ->{
                    Toast.makeText(this, "Ayuda", Toast.LENGTH_SHORT).show()
                }
            }
            if(gestorDeFragmentos){
                cambiarFragmento(fragment, item)
                drawerLayout!!.closeDrawers()
            }
            true
        }
    }


    //Función para cambiar en la vista del fragmento, poner como seleccionado el icono que se selecciono y poner el titulo del fragmento en la barra
    fun cambiarFragmento(fragment: Fragment?, item: MenuItem){
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .addToBackStack(null)
                .detach(fragment)
                .attach(fragment)
                .commit()
        item.isChecked = true
        supportActionBar!!.title = item.title


    }

    // Función para cuando seleccionan el icono en el Toolbar abra el Drawer
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item!!.itemId){
            android.R.id.home ->{

                drawerLayout!!.openDrawer(GravityCompat.START)
                return true

            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (drawerLayout!!.isDrawerOpen(GravityCompat.START)){
            drawerLayout!!.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }

}
