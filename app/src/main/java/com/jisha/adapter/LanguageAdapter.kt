package com.jisha.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.jisha.modelclass.LanguageData
import com.jisha.searchview.databinding.SearchItemBinding

class LanguageAdapter : RecyclerView.Adapter<LanguageAdapter.LanguageItemViewHolder>(),Filterable {

    private val languageItem = mutableListOf<LanguageData>()
    private val originalLanguageItem: MutableList<LanguageData> = mutableListOf()

    inner class LanguageItemViewHolder(val binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageItemViewHolder {
        return LanguageItemViewHolder(
            SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }


    override fun getItemCount(): Int {
        return languageItem.size
    }

    fun updateList(list: List<LanguageData>){
        languageItem.clear()
        languageItem.addAll(list)
        originalLanguageItem.clear()
        originalLanguageItem.addAll(list)
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: LanguageItemViewHolder, position: Int) {
        with(languageItem[position]) {
            holder.binding.titleTv.text = title
            holder.binding.logoIv.setImageResource(logo)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString().trim().lowercase()
                val resultList = mutableListOf<LanguageData>()

                if (charSearch.isEmpty()) {
                    resultList.addAll(originalLanguageItem) // Show the original list if the search query is empty
                } else {
                    for (row in originalLanguageItem) {
                        if (row.title.lowercase().contains(charSearch)) {
                            resultList.add(row)
                        }
                    }
                }

                val filterResults = FilterResults()
                filterResults.values = resultList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                languageItem.clear()
                languageItem.addAll(results?.values as? List<LanguageData> ?: emptyList())
                notifyDataSetChanged()
            }
        }
    }

}