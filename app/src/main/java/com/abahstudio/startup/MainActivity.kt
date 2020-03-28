package com.abahstudio.startup

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.animal_ticket.view.*

class MainActivity : AppCompatActivity() {

    var listOfAnimals = ArrayList<Animal>()
    var adapter:AnimalAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // load animals
        listOfAnimals.add(Animal("Baboon", "Baboon live in big place with tree", R.drawable.baboon, false))
        listOfAnimals.add(Animal("Bulldog", "Buldog live in big place with tree", R.drawable.bulldog, false))
        listOfAnimals.add(Animal("Panda", "Panda live in big place with tree", R.drawable.panda, true))
        listOfAnimals.add(Animal("Swallow Bird", "Swallow bird live in big place with tree", R.drawable.swallow_bird, false))
        listOfAnimals.add(Animal("White tiger", "White tiger live in big place with tree", R.drawable.white_tiger, true))
        listOfAnimals.add(Animal("Zebra", "Zebra live in big place with tree", R.drawable.zebra, false))

        adapter= AnimalAdapter(this, listOfAnimals)
        tvListAnimal.adapter = adapter
    }

    fun delete(index:Int){
        listOfAnimals.removeAt(index)
        adapter!!.notifyDataSetChanged()
    }

    fun add(index: Int){
        listOfAnimals.add(listOfAnimals[index])
        adapter!!.notifyDataSetChanged()
    }

    inner  class AnimalAdapter: BaseAdapter{
        var listOfAnimals=ArrayList<Animal>()
        var context:Context?=null
        constructor(context: Context, listOfAnimals: ArrayList<Animal>):super(){
            this.listOfAnimals=listOfAnimals
            this.context=context
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val animal = listOfAnimals[position]
            if (animal.isKiller==true) {
                var inflator =
                    context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflator.inflate(R.layout.animal_killer_ticket, null)
                myView.tvName.text = animal.name!!
                myView.tvDes.text = animal.des!!
                myView.ivAnimalImage.setImageResource(animal.image!!)
                myView.ivAnimalImage.setOnClickListener {
                    val intent =Intent(context, AnimalInfo::class.java)
                    intent.putExtra("name",animal.name!!)
                    intent.putExtra("des",animal.des!!)
                    intent.putExtra("image",animal.image!!)
                    context!!.startActivity(intent)
                }
                myView.tvDup.setOnClickListener{
                    add(position)
                }
                myView.tvDel.setOnClickListener{
                    delete(position)
                }
                return myView
            }else{
                var inflator =
                    context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflator.inflate(R.layout.animal_ticket, null)
                myView.tvName.text = animal.name!!
                myView.tvDes.text = animal.des!!
                myView.ivAnimalImage.setImageResource(animal.image!!)
                myView.ivAnimalImage.setOnClickListener {
                    val intent =Intent(context, AnimalInfo::class.java)
                    intent.putExtra("name",animal.name!!)
                    intent.putExtra("des",animal.des!!)
                    intent.putExtra("image",animal.image!!)
                    context!!.startActivity(intent)
                }
                myView.tvDup.setOnClickListener{
                    add(position)
                }
                myView.tvDel.setOnClickListener{
                    delete(position)
                }
                return myView
            }
        }

        override fun getItem(position: Int): Any {
            return listOfAnimals[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listOfAnimals.size
        }

    }
}
