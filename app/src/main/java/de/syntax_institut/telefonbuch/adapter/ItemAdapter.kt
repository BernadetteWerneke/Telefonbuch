package de.syntax_institut.telefonbuch.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import de.syntax_institut.telefonbuch.R
import de.syntax_institut.telefonbuch.data.model.Contact
import de.syntax_institut.telefonbuch.ui.HomeFragmentDirections

/**
 * Diese Klasse organisiert mithilfe der ViewHolder Klasse das Recycling
 */
class ItemAdapter(
    private val dataset: MutableList<Contact>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    /**
     * der ViewHolder umfasst die View uns stellt einen Listeneintrag dar
     */
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tvItemName)
        var tvNumber: TextView = itemView.findViewById(R.id.tvItemNumber)
        val itemCard = itemView.findViewById<MaterialCardView>(R.id.material_card)
        val deleteButton = itemView.findViewById<Button>(R.id.delete_button)

    }
    fun deleteItem(position: Int){
        dataset.removeAt(position)
        notifyItemRemoved(position)
    }
    /**
     * hier werden neue ViewHolder erstellt
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val itemLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(itemLayout)
    }

    /**
     * hier findet der Recyclingprozess statt
     * die vom ViewHolder bereitgestellten Parameter erhalten die Information des Listeneintrags
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val contact = dataset[position]
        holder.tvName.text = contact.name
        holder.tvNumber.text = contact.number

        holder.deleteButton.setOnClickListener {
        //Click auf Delete-Button löscht ItemElemente aus View
            dataset.removeAt(position)
            notifyItemRemoved(position)
        }

        //Click auf ItemCard leitet weiter zu DetailFragment
        holder.itemCard.setOnClickListener(){
            holder.itemCard.findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(contact))
        }

    }

    /**
     * damit der LayoutManager weiß, wie lang die Liste ist
     */
    override fun getItemCount(): Int {
        return dataset.size
    }

}
