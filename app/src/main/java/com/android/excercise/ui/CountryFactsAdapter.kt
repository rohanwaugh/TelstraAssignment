package com.android.excercise.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.excercise.R
import com.android.excercise.data.model.CountryFacts
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.country_facts_item.view.*

class CountryFactsAdapter:RecyclerView.Adapter<CountryFactsAdapter.CountryFactsViewHolder>() {

    var countryFactList: List<CountryFacts>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryFactsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.country_facts_item,parent,false)
        return CountryFactsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return countryFactList?.size ?:0
    }

    override fun onBindViewHolder(holder: CountryFactsViewHolder, position: Int) {
        val countryFact = countryFactList?.get(position)
        holder.bind(countryFact)
    }


    class CountryFactsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        fun bind(countryFacts: CountryFacts?){
            itemView.apply {
                title.text = countryFacts?.title
                description.text = countryFacts?.description
                countryFacts?.image?.let { imageurl ->
                    Picasso.get()
                        .load(imageurl)
                        .fit().centerInside()
                        .error(R.drawable.ic_launcher_background)
                        .into(factImage)
                }

            }
        }
    }
}