package io.github.mindjet.oros

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.mindjet.oros.ext.load
import io.github.mindjet.oros.ext.toast
import io.github.mindjet.oros.model.HeroBrief
import kotlinx.android.synthetic.main.fragment_hero_brief.view.*

class HeroBriefBottomSheet : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(itemData: HeroBrief): HeroBriefBottomSheet {
            val instance = HeroBriefBottomSheet()
            val bundle = Bundle()
            bundle.putSerializable(Constant.ARG_HERO_BRIEF, itemData)
            instance.arguments = bundle
            return instance
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val heroBrief = arguments?.getSerializable(Constant.ARG_HERO_BRIEF) as HeroBrief
        toast(heroBrief)
        val view = inflater.inflate(R.layout.fragment_hero_brief, container, false)
        view.apply {
            iv_hero_avatar.load(heroBrief.avatar)
            tv_hero_name.text = heroBrief.realName.toString()
        }
        return view
    }

}