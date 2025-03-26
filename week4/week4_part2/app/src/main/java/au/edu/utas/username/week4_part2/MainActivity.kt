package au.edu.utas.username.week4_part2

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.edu.utas.username.week4_part2.databinding.ActivityMainBinding
import au.edu.utas.username.week4_part2.databinding.MyListItemBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var ui : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)

        val items = mutableListOf(
            Person(name = "Rick", studentID = 9001, smort = true),
            Person(name = "Morty", studentID = 9, smort = true),
            Person(name = "Beth", studentID = 42, smort = true),
            Person(name = "Summer", studentID = 43, smort = true),
            Person(name = "Jerry", studentID = -1, smort = false)
        )

        ui.myList.adapter = PersonAdapter(people = items)
        ui.myList.layoutManager = LinearLayoutManager(this)
    }

    inner class PersonHolder(var ui: MyListItemBinding) : RecyclerView.ViewHolder(ui.root) {}
    inner class PersonAdapter(private val people: MutableList<Person>) : RecyclerView.Adapter<PersonHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonHolder {
            val ui = MyListItemBinding.inflate(layoutInflater, parent, false)   //inflate a new row from the my_list_item.xml
            return PersonHolder(ui)                                             //wrap it in a ViewHolder
        }

        override fun onBindViewHolder(holder: MainActivity.PersonHolder, position: Int) {
            val person = people[position]   //get the data at the requested position
            holder.ui.txtName.text = person.name //set the TextView in the row we are recycling
            holder.ui.txtStudentID.text = person.studentID.toString()


            holder.itemView.setOnClickListener {
                val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setTitle("Are They Smort?")
                val message = if (person.smort) {
                    "${person.name} is Smort!"
                } else {
                    "${person.name} is not Smort!"
                }

                alertDialogBuilder.setMessage(message)

                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()
            }
        }

        override fun getItemCount(): Int {
            return people.size
        }
    }
}