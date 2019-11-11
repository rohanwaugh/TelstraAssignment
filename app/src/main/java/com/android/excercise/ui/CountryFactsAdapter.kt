package com.android.excercise.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.excercise.R
import com.android.excercise.data.model.CountryFacts
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.country_facts_item.view.*

/* This is Adapter class for Recyclerview. */
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
                if(countryFacts?.description.isNullOrEmpty()) {
                    description.text = context.resources.getString(R.string.default_description)
                }else{
                    description.text = countryFacts?.description
                }
                Picasso.get()
                    .load(countryFacts?.image)
                    .fit().centerInside()
                    .placeholder(R.drawable.default_image)
                    .error(R.drawable.default_image)
                    .into(image)

            }
        }
    }
}