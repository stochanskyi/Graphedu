package com.nulp.graphedu.presentation.fragments.handbook.container.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.nulp.graphedu.presentation.fragments.handbook.container.tab.HandbookTab
import com.nulp.graphedu.presentation.fragments.handbook.content.impl.HandbookContentFragment

class HandbookTabAdapter(
    fragment: Fragment,
    private val items: List<HandbookTab>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun createFragment(position: Int): Fragment {
        val content = items[position].provideContent()
        return HandbookContentFragment.newInstance(content)
    }

    fun resolveTabConfigurationStrategy() =
        TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            val item = items[position]
            tab.setIcon(item.provideIconRes())
                .setText(item.provideTitleRes())
        }
}