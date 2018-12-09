package com.joseluisng.serviciocolotlan.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.joseluisng.serviciocolotlan.fragments.*

class PageAdapter(fm : FragmentManager?, val numTabs: Int) : FragmentStatePagerAdapter(fm){


    override fun getItem(position: Int): Fragment? {
        return when (position){
            0 -> return AgregaContFragment()
            1 -> return SugerenciaFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return numTabs
    }

}