package com.mindvalleytest.view.views.pinboardlist

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import com.mindvalleytest.R
import com.mindvalleytest.model.MasterDetails
import com.mindvalleytest.utility.AlertUtil
import com.mindvalleytest.utility.NetworkUtil
import com.mindvalleytest.view.views.common.DialogListener
import com.mindvalleytest.view.views.common.FragmentInstanceHandler
import com.mindvalleytest.view.views.common.ItemActionListener
import com.mindvalleytest.view.views.details.PinDetailsFragment

import java.util.ArrayList

/**
 * This Fragment is used to show the Users List.
 *
 *
 *
 * @author Salahuddin
 * @version 1.0
 */
class PinboardListFragment : Fragment(), PinboardListView, ItemActionListener, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, DialogListener {
    internal lateinit var fragmentInstanceHandler: FragmentInstanceHandler
    internal lateinit var listingPresenter: PinboardListPresenter
    internal lateinit var rcVwUsers: RecyclerView
    internal lateinit var swpLayUsersList: SwipeRefreshLayout
    internal lateinit var fabClearCache: FloatingActionButton

    internal lateinit var pbLoading: ProgressBar


    private val CLEAR_CACHE = 1

    internal lateinit var listingAdapter: PinboardListAdapter

    override fun onCreateView(inflater: LayoutInflater, vg: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_listing, vg, false)
        rcVwUsers = view.findViewById<RecyclerView>(R.id.xcVwUsers)
        swpLayUsersList = view.findViewById<SwipeRefreshLayout>(R.id.xswpLayUsersList)
        fabClearCache = view.findViewById<FloatingActionButton>(R.id.ximgvwClearCache)
        pbLoading = view.findViewById<ProgressBar>(R.id.pb_loading)
//        ButterKnife.bind(this, view)
        return view
    }

    override fun onStart() {
        super.onStart()
        initObjects()
    }

    /**
     * This function is used to initialise the objects that are going to be used in this fragment
     */
    fun initObjects() {
        listingPresenter = PinboardListPresenterImplementor(activity, this)
        swpLayUsersList.setOnRefreshListener(this)
        fabClearCache.setOnClickListener(this)
        swpLayUsersList.isEnabled = false
        pbLoading.visibility = View.VISIBLE
        listingPresenter.fetchUsers()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentInstanceHandler = activity as FragmentInstanceHandler
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        fragmentInstanceHandler = getActivity() as FragmentInstanceHandler
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun renderUserList(users: ArrayList<MasterDetails>) {

        listingAdapter = PinboardListAdapter(activity, ArrayList(), this)

        rcVwUsers!!.adapter = listingAdapter

        //        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL)


        rcVwUsers!!.layoutManager = staggeredGridLayoutManager

        for (detailsResponse in users) {
            listingAdapter.add(detailsResponse)
        }
        if (swpLayUsersList!!.isRefreshing)
            swpLayUsersList!!.isRefreshing = false
        swpLayUsersList!!.isEnabled = true
        pbLoading!!.visibility = View.GONE
    }

    override fun displayErrorMessage(errorMessage: String) {
        AlertUtil.showToast(activity, errorMessage)

        swpLayUsersList!!.isEnabled = true
    }

    override fun onItemClicked(currentObject: Any, position: Int) {

        val currentData = currentObject as MasterDetails

        if (NetworkUtil.isAirplaneModeWithNoWIFI(activity) || !NetworkUtil.isNetworkAvailable(activity)) {
            AlertUtil.showToast(activity, getString(R.string.network_error))
        } else {
            fragmentInstanceHandler.changeFragment(this@PinboardListFragment,
                    PinDetailsFragment.newInstance(currentData), true)
        }


    }

    override fun onRefresh() {
        //Pull to Refresh is called
        listingPresenter.fetchUsers()
    }

    override fun onClick(view: View) {
        if (view === fabClearCache) {
            AlertUtil.showAlertDialogMultipleOptions(activity, CLEAR_CACHE, this, resources.getString(R.string.clear_cache), resources.getString(R.string.clear_cache_description), resources.getString(R.string.yes), resources.getString(R.string.no))
        }
    }

    override fun onPositiveAction(dialogID: Int, updatedData: Any?) {
        listingPresenter.clearCache()
        listingPresenter.fetchUsers()
    }

    override fun onNegativeAction(dialogID: Int, updatedData: Any?) {

    }

    companion object {


        fun newInstance(): PinboardListFragment {

            val args = Bundle()

            val fragment = PinboardListFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
