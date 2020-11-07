package com.kostya_zinoviev.kotlinfishermanhanbook

import android.app.LauncherActivity
import android.content.DialogInterface
import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.main_content.*

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    var drawerLayout: DrawerLayout? = null
    var navigationView:NavigationView? = null
    var adapter:Adapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigationView = findViewById(R.id.nav_view)
        drawerLayout = findViewById(R.id.drawer_layout)

        val list:ArrayList<Model> = ArrayList()

        list.addAll(fillArrays(resources.getStringArray(R.array.titleFish),resources.getStringArray(R.array.descFish),getImageId(R.array.imageFish)))
        navigationView?.setNavigationItemSelectedListener(this)

        adapter = Adapter(this,list)

        rView.hasFixedSize()
        rView.layoutManager = LinearLayoutManager(this)
        rView.adapter = adapter
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        val list:ArrayList<Model> = ArrayList()
        when(id){
            R.id.id_fish -> {
                Toast.makeText(this,"item fish",Toast.LENGTH_SHORT).show()
                list.addAll(fillArrays(resources.getStringArray(R.array.titleFish),resources.getStringArray(R.array.descFish),getImageId(R.array.imageFish)))
                adapter?.updateAdapter(list)
                drawerLayout?.closeDrawer(Gravity.LEFT)
            }
            R.id.id_na -> {
                list.addAll(fillArrays(resources.getStringArray(R.array.titleNa),resources.getStringArray(R.array.descNa),getImageId(R.array.imageNa)))
                adapter?.updateAdapter(list)
                drawerLayout?.closeDrawer(Gravity.LEFT)
            }
            R.id.id_sna -> {
                Toast.makeText(this,"item sna",Toast.LENGTH_SHORT).show()
                list.addAll(fillArrays(resources.getStringArray(R.array.titleFish),resources.getStringArray(R.array.descFish),getImageId(R.array.imageFish)))
                adapter?.updateAdapter(list)
                drawerLayout?.closeDrawer(Gravity.LEFT)
            }
            R.id.id_history -> {
                Toast.makeText(this,"item history",Toast.LENGTH_SHORT).show()
                list.addAll(fillArrays(resources.getStringArray(R.array.titleNa),resources.getStringArray(R.array.descNa),getImageId(R.array.imageNa)))
                adapter?.updateAdapter(list)
                drawerLayout?.closeDrawer(Gravity.LEFT)
            }

        }
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout?.isDrawerOpen(Gravity.LEFT)!!){
            drawerLayout?.closeDrawer(Gravity.LEFT)
        }
        //Выход диалог
            createSimpleDialog()
        //Выбор диалог
//        createSimpleMultiChoiceDialog()
    }

    fun fillArrays(titleArray:Array<String>,descArray:Array<String>,imageArray:IntArray):List<Model>{
        var listModelArray:ArrayList<Model> = ArrayList()
        for (i in 0..titleArray.size - 1){
            var listItem = Model(imageArray[i],titleArray[i],descArray[i])
            listModelArray.add(listItem)
        }
        return listModelArray
    }

    fun getImageId(imageArrayId:Int):IntArray{
        var tArray:TypedArray = resources.obtainTypedArray(imageArrayId)
        val count = tArray.length()
        val IDS = IntArray(count)

        for(i in IDS.indices){
            IDS[i] = tArray.getResourceId(i,0)
        }
        tArray.recycle()
        return IDS
    }

    fun createSimpleDialog(){
        val builderDialog = AlertDialog.Builder(this)
        builderDialog
            .setTitle("Вызод из приложения")
            .setMessage("Вы действительно хотите выйти из приложения?")
            .setPositiveButton("Да",DialogInterface.OnClickListener { dialog, which ->
                finish()

            })
            .setNegativeButton("Нет",DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            })
            .setNeutralButton("Отмена",DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            })
            .setCancelable(false)
            .show()
    }

    fun createSimpleMultiChoiceDialog(){
        val builderDialog = AlertDialog.Builder(this)
        builderDialog
            .setTitle("Выбор телефона")
            .setMultiChoiceItems(R.array.alertDialogArray,null){dialog,position,choice ->
                Log.d("tag","position $position и он $choice")
            }
            .setPositiveButton("Да",DialogInterface.OnClickListener { dialog, which ->
                finish()

            })
            .setNegativeButton("Нет",DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            })
            .setNeutralButton("Отмена",DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            })
            .setCancelable(false)
            .show()
    }
}
