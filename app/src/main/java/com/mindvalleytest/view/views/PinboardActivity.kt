package com.mindvalleytest.view.views

import android.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.mindvalleytest.R
import com.mindvalleytest.view.views.common.FragmentInstanceHandler
import com.mindvalleytest.view.views.pinboardlist.PinboardListFragment

class PinboardActivity : AppCompatActivity(), FragmentInstanceHandler {

    /**
     * This function is used to get the current Top Fragment from the Back stack
     *
     * @return - The Fragment on the Top
     */
    private//Performs any previous pending operations in the queue for the fragments
    val currentTopFragment: Fragment
        get() {

            val fragmentManager = fragmentManager
            fragmentManager.executePendingTransactions()
            val fragmentTag = fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 1).name
            return fragmentManager.findFragmentByTag(fragmentTag)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pinboard)
        changeFragment(PinboardListFragment.newInstance(), PinboardListFragment.newInstance(), false)
    }

    override fun changeFragment(fromFragment: Fragment?, toFragment: Fragment, addToBackStack: Boolean) {
        val fm = fragmentManager
        val ft = fm.beginTransaction()
        // ft.setCustomAnimations(R.animator.custom_fade_in, R.animator.custom_fade_out, R.animator.custom_fade_in, R.animator.custom_fade_out);

        ft.replace(R.id.fragmentHolder, toFragment, toFragment.javaClass.getName())
        if (addToBackStack && fromFragment != null) {
            ft.addToBackStack(fromFragment.javaClass.getName()) // we are following standard practice of giving fromFrag class name as name for the back stack state.
        }

        ft.commitAllowingStateLoss()
    }
}